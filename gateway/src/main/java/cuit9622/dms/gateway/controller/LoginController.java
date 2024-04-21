package cuit9622.dms.gateway.controller;

import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.common.util.TokenUtil;
import cuit9622.dms.gateway.eneity.TokenStore;
import cuit9622.dms.gateway.repository.TokenStoreRepository;
import cuit9622.dms.gateway.vo.LoginReqVo;
import cuit9622.dms.gateway.vo.LoginRespVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Resource
    private TokenStoreRepository tokenStoreRepository;

    @PostMapping("/login")
    public CommonResult<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        // TODO 实现具体的登录验证
        long userId = 0L;
        LoginRespVo token = generateToken(userId);
        TokenStore tokenStore = tokenStoreRepository.findByUserId(userId);
        if (tokenStore == null) {
            tokenStore = new TokenStore();
            tokenStore.setUserId(userId);
        }
        tokenStore.setRefreshToken(token.getRefreshToken());
        tokenStoreRepository.save(tokenStore);
        return CommonResult.success(token);
    }

    @GetMapping("/refresh")
    public CommonResult<LoginRespVo> refresh(@RequestHeader("token") String refreshToken) {
        //TODO 实现更具体的逻辑
        Long userID = TokenUtil.decodeRefreshToken(refreshToken);
        TokenStore user = tokenStoreRepository.findByUserId(userID);
        if (user != null && user.getRefreshToken().equals(refreshToken)) {
            LoginRespVo token = generateToken(userID);
            return CommonResult.success(token);
        }
        return CommonResult.error(ErrorCodes.FORBIDDEN);
    }

    private LoginRespVo generateToken(Long userID) {
        //TODO
        String refreshToken = TokenUtil.createRefreshToken(userID);
        DMSUserDetail dmsUserDetail = new DMSUserDetail();
        dmsUserDetail.setID(userID);
        String accessToken = TokenUtil.createAccessToken(dmsUserDetail);
        LoginRespVo token = new LoginRespVo();
        token.setRefreshToken(refreshToken);
        token.setAccessToken(accessToken);
        return token;
    }
}
