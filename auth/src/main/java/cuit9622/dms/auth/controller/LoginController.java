package cuit9622.dms.auth.controller;

import cuit9622.dms.auth.service.UserService;
import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.CommonResult;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Resource
    public UserService userService;

    @PermitAll
    @PostMapping("/login")
    public CommonResult<LoginRepVo> login(@RequestBody User user) {
        return userService.login(user);
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
}
