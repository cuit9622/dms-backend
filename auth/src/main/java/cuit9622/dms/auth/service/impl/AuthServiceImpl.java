package cuit9622.dms.auth.service.impl;

import cuit9622.dms.auth.service.AuthService;
import cuit9622.dms.auth.service.client.SysClient;
import cuit9622.dms.auth.vo.CloudflareRepVo;
import cuit9622.dms.auth.vo.CloudflareReqVo;
import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.auth.vo.LoginReqVo;
import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.*;
import cuit9622.dms.security.util.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    SysClient sysClient;

    @Resource
    RestClient<CloudflareReqVo, CloudflareRepVo> restClient;

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Override
    public CommonResult<LoginRepVo> login(LoginReqVo query) {
        User user = sysClient.getUserByUserName(query.getUsername());
        // 用户名不存在抛出异常信息
        if (user == null) {
            throw new BizException("用户名或密码错误");
        }
        // 匹配密码是否正确
        if (!DigestsUtils.matches(query.getPassword(), user.getSalt(), user.getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        CloudflareReqVo cloudflareReqVo = new CloudflareReqVo("0x4AAAAAAAGHnUIytvWD2FxF5TiLTX0kVWg", query.getToken());
        CloudflareRepVo result = restClient.post("https://challenges.cloudflare.com/turnstile/v0/siteverify", cloudflareReqVo, CloudflareRepVo.class);
        if (!result.getSuccess()) {
            throw new BizException(401, "未通过人机验证");
        }

        // 拿到权限信息和token存到redis中
        String token = JWTUtils.creatToken(user.getUserId());
        List<MenuTree> menuTree = sysClient.getMenuTree(user.getUserId());
        List<String> authorities = sysClient.getAuthoritiesByUserId(user.getUserId());
        redisTemplate.opsForValue().set(RedisUtils.TOKEN_KEY + user.getUserId(),
                token, JWTUtils.EXPIRE_TIME, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RedisUtils.PERMISSIONS_KEY + user.getUserId(),
                JsonUtil.toJsonString(authorities), JWTUtils.EXPIRE_TIME, TimeUnit.DAYS);

        LoginRepVo loginRepVo = new LoginRepVo();
        loginRepVo.setMenuTree(menuTree);
        loginRepVo.setToken(token);
        return CommonResult.success(loginRepVo);
    }

    @Override
    public CommonResult<User> token() {
        Long userID = SecurityUtil.getUserID();
        return CommonResult.success(sysClient.getUserById(userID));
    }

    @Override
    public CommonResult<?> logout() {
        Long userID = SecurityUtil.getUserID();
        redisTemplate.delete(RedisUtils.TOKEN_KEY + userID);
        redisTemplate.delete(RedisUtils.PERMISSIONS_KEY + userID);
        return CommonResult.success("");
    }
}
