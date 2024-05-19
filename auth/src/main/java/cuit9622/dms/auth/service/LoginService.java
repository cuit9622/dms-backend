package cuit9622.dms.auth.service;

import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.CommonResult;

public interface LoginService {
    CommonResult<LoginRepVo> login(User query);
}
