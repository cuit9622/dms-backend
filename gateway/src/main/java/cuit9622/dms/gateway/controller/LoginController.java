package cuit9622.dms.gateway.controller;

import cuit9622.dms.gateway.vo.LoginReqVo;
import cuit9622.dms.gateway.vo.LoginRespVo;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.common.util.TokenUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping("/login")
    public CommonResult<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        // TODO实现具体的登录验证
        LoginRespVo token = generateToken(0L);
        return CommonResult.success(token);
    }

    @GetMapping("/refresh")
    public CommonResult<LoginRespVo> refresh(@RequestHeader("token") String refreshToken) {
        //TODO实现更具体的逻辑
        Long userID=TokenUtil.decodeRefreshToken(refreshToken);
        LoginRespVo token = generateToken(userID);
        return CommonResult.success(token);
    }

    private LoginRespVo generateToken(Long userID) {
        //TODO
        String refreshToken = TokenUtil.createRefreshToken(userID);
        DMSUserDetail dmsUserDetail = new DMSUserDetail();
        dmsUserDetail.setID(userID);
        dmsUserDetail.setUserName("9622");
        String accessToken = TokenUtil.createAccessToken(dmsUserDetail);
        LoginRespVo token = new LoginRespVo();
        token.setRefreshToken(refreshToken);
        token.setAccessToken(accessToken);
        return token;
    }
}
