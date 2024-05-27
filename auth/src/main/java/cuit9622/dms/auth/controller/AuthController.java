package cuit9622.dms.auth.controller;

import cuit9622.dms.auth.service.AuthService;
import cuit9622.dms.auth.service.client.SysClient;
import cuit9622.dms.auth.vo.*;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.CommonResult;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    public CommonResult<TokenRepVo> token() {
        return authService.token();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public CommonResult<?> logout() {
        return authService.logout();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    public CommonResult<?> changePassword(@RequestBody ChangePasswordVo body) {
        return authService.changePassword(body);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/img/upload")
    public CommonResult<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 原始文件名字
        String originalFilename = file.getOriginalFilename();

        // 获取文件后缀
        String suffix = null;
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 使用UUID来生成文件名，防止文件名重复
        String fileName = UUID.randomUUID() + suffix;

        // 判断当前目录是否存在，如果不存在就创建
        String basePath = "c:/static/img/";
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            // 将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return CommonResult.success(fileName);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/info")
    public CommonResult<?> changeInfo(@RequestBody ChangeInfoVo body) {
        return authService.changeInfo(body);
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
