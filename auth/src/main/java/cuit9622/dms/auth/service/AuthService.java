package cuit9622.dms.auth.service;

import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.auth.vo.LoginReqVo;
import cuit9622.dms.auth.vo.TokenRepVo;
import cuit9622.dms.common.model.CommonResult;

public interface AuthService {
    CommonResult<LoginRepVo> login(LoginReqVo query);

    CommonResult<TokenRepVo> token();

    CommonResult<?> logout();
}
