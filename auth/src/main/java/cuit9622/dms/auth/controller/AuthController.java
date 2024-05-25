package cuit9622.dms.auth.controller;

import cuit9622.dms.auth.service.AuthService;
import cuit9622.dms.auth.service.client.SysClient;
import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.auth.vo.LoginReqVo;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.CommonResult;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Resource
    public AuthService authService;
    @Resource
    private SysClient sysClient;

    @PermitAll
    @PostMapping("/login")
    public CommonResult<LoginRepVo> login(@RequestBody LoginReqVo loginReqVo) {
        return authService.login(loginReqVo);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/token")
    public CommonResult<User> token(@RequestHeader("token") String token) {
        return authService.token();
    }

    @PermitAll
    @GetMapping("/logout")
    public CommonResult<?> logout() {
        return authService.logout();
    }

    @PreAuthorize("hasAuthority('sys:menu:add')")
    @GetMapping("/test1")
    public String test1() {
        return "ok";
    }

    @PermitAll
    @GetMapping("/test2")
    public String test2() {
        throw new BizException(114514, "原神玩少了");
    }

    @PermitAll
    @GetMapping("/test3")
    public String test3() {
        return sysClient.test();
    }
}
