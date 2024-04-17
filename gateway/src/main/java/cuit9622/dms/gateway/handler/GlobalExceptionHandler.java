package cuit9622.dms.gateway.handler;


import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.JsonUtil;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Order(-1) // 保证优先级高于默认的 Spring Cloud Gateway 的 ErrorWebExceptionHandler 实现
@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Nonnull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, @Nonnull Throwable ex) {
        // 已经 commit，则直接返回异常
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // 转换成 CommonResult
        CommonResult<?> result;
        if (ex instanceof ResponseStatusException) {
            result = responseStatusExceptionHandler(exchange, (ResponseStatusException) ex);
        } else {
            result = defaultExceptionHandler(exchange, ex);
        }

        // 返回给前端
        return writeJSON(exchange, result);
    }

    /**
     * 处理 Spring Cloud Gateway 默认抛出的 ResponseStatusException 异常
     */
    private CommonResult<?> responseStatusExceptionHandler(ServerWebExchange exchange,
                                                           ResponseStatusException ex) {
        ServerHttpRequest request = exchange.getRequest();
        log.error("[responseStatusExceptionHandler][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
        return CommonResult.error(ex.getStatusCode().value(), ex.getReason());
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> defaultExceptionHandler(ServerWebExchange exchange,
                                                   Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        log.error("[defaultExceptionHandler][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
        return CommonResult.error(ErrorCodes.INTERNAL_SERVER_ERROR.getCode(), ErrorCodes.INTERNAL_SERVER_ERROR.getMsg());
    }

    @SuppressWarnings("deprecation")
    private Mono<Void> writeJSON(ServerWebExchange exchange, Object object) {
        // 设置 header
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 设置 body
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(JsonUtil.toJsonString(object).getBytes(StandardCharsets.UTF_8));
            } catch (Exception ex) {
                ServerHttpRequest request = exchange.getRequest();
                log.error("[writeJSON][uri({}/{}) 发生异常]", request.getURI(), request.getMethod(), ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}