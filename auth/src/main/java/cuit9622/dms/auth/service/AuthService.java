package cuit9622.dms.auth.service;

import cuit9622.dms.auth.vo.*;
import cuit9622.dms.common.model.CommonResult;

public interface AuthService {
    CommonResult<LoginRepVo> login(LoginReqVo query);

    CommonResult<TokenRepVo> token();

    CommonResult<?> logout();

    CommonResult<?> changePassword(ChangePasswordVo body);

    CommonResult<?> changeInfo(ChangeInfoVo body);
}
