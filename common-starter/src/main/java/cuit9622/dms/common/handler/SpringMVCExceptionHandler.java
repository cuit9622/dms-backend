package cuit9622.dms.common.handler;

import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class SpringMVCExceptionHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public CommonResult<?> NoResourceFoundException(NoResourceFoundException ex) {

        log.error("[NoResourceFoundException] {}\n", ex.getResourcePath(), ex);
        return CommonResult.error(ErrorCodes.NOT_FOUND);
    }
}
