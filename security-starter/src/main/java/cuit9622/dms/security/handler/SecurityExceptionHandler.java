package cuit9622.dms.security.handler;

import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class SecurityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException e) {
        String token = req.getHeader("token");
        if (token == null) {
            log.warn("[authenticationExceptionHandler][ 无法访问 url({})]",
                    req.getRequestURL(), e);
            return CommonResult.error(ErrorCodes.UNAUTHORIZED);
        }
        log.warn("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", JWTUtils.getUserId(token),
                req.getRequestURL(), e);
        return CommonResult.error(ErrorCodes.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public CommonResult<?> authenticationExceptionHandler(HttpServletRequest req, org.springframework.security.core.AuthenticationException e) {
        log.warn("[authenticationExceptionHandler][ 无法访问 url({})]",
                req.getRequestURL(), e);
        return CommonResult.error(ErrorCodes.UNAUTHORIZED);
    }
}
