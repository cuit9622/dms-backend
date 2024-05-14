package cuit9622.dms.security.handler;

import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class SecurityExceptionHandler {
    /**
     * 处理 Spring Security 权限不足的异常
     * <p>
     * 来源是，使用 @PreAuthorize 注解，AOP 进行权限拦截
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException ex) {
        String token = req.getHeader("token");
        log.warn("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", JWTUtils.getUserId(token),
                req.getRequestURL(), ex);
        return CommonResult.error(ErrorCodes.FORBIDDEN);
    }
}
