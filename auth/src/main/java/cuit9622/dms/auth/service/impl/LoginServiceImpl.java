package cuit9622.dms.auth.service.impl;

import cuit9622.dms.auth.service.LoginService;
import cuit9622.dms.auth.service.client.SysClient;
import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.DigestsUtils;
import cuit9622.dms.common.util.JWTUtils;
import cuit9622.dms.common.util.JsonUtil;
import cuit9622.dms.common.util.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    SysClient sysClient;


    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Override
    public CommonResult<LoginRepVo> login(User query) {
        User user = sysClient.getUserByUserName(query.getUsername());
        // 用户名不存在抛出异常信息
        if (user == null) {
            throw new BizException("用户名或密码错误");
        }
        // 匹配密码是否正确
        if (!DigestsUtils.matches(query.getPassword(), user.getSalt(), user.getPassword())) {
            throw new BizException("用户名或密码错误");
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
}
