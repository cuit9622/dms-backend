package cuit9622.dms.common.consumer.gateway.controller;

import cuit9622.dms.common.consumer.gateway.vo.LoginVo;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.common.util.TokenUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginVo loginVo) {
        // TODO实现具体的登录验证
        String refreshToken = TokenUtil.createRefreshToken(0L);
        return CommonResult.success(refreshToken);
    }

    @GetMapping("/refresh")
    public CommonResult<String> refresh(@RequestHeader("token") String token) {
        //TODO实现更具体的逻辑
        Long userID = TokenUtil.decodeRefreshToken(token);
        DMSUserDetail dmsUserDetail = new DMSUserDetail();
        dmsUserDetail.setID(userID);
        dmsUserDetail.setUserName("9622");
        String accessToken = TokenUtil.createAccessToken(dmsUserDetail);
        return CommonResult.success(accessToken);
    }
}
