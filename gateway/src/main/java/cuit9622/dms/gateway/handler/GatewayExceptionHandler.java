package cuit9622.dms.gateway.handler;


import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Slf4j
@Order(-1) //提升优先级
public class GatewayExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public CommonResult<?> noResourceFoundExceptionHandler(ResponseStatusException ex) {
        log.warn("NoResourceFoundException", ex.getReason());
        return CommonResult.error(ErrorCodes.NOT_FOUND);
    }
}