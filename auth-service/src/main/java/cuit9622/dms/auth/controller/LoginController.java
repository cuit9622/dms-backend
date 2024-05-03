package cuit9622.dms.auth.controller;

import cuit9622.dms.auth.service.UserService;
import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.CommonResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Resource
    public UserService userService;

    @PostMapping("/login")
    public CommonResult<LoginRepVo> login(@RequestBody User user) {
        return userService.login(user);
    }
}
