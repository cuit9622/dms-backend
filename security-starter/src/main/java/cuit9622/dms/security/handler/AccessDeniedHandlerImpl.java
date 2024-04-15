package cuit9622.dms.security.handler;

import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

public class AccessDeniedHandlerImpl implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        CommonResult<?> commonResult = CommonResult.error(ErrorCodes.FORBIDDEN);
        ServletUtil.writeJSON(response, commonResult);
    }
}
