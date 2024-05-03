package cuit9622.dms.auth.service.impl;

import cuit9622.dms.auth.service.UserService;
import cuit9622.dms.auth.service.client.MenuClient;
import cuit9622.dms.auth.service.client.UserClient;
import cuit9622.dms.auth.vo.LoginRepVo;
import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.util.DigestsUtils;
import cuit9622.dms.common.util.JWTUtils;
import cuit9622.dms.common.util.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserClient userClient;

    @Resource
    MenuClient menuClient;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public CommonResult<LoginRepVo> login(User query) {
        User user = userClient.getUserByUserName(query.getUsername());
        // 用户名不存在抛出异常信息
        if (user == null) {
            throw new BizException("用户名或密码错误");
        }
        // 匹配密码是否正确
        if (!DigestsUtils.matches(query.getPassword(), user.getSalt(), user.getPassword())) {
            throw new BizException("用户名或密码错误");
        }
        // 拿到权限信息和token存到redis中
        String token = JWTUtils.creatToken(user.getUsername());
        List<MenuTree> menuTree = menuClient.getMenuTree(user.getUserId());
        redisTemplate.opsForValue().set(RedisUtils.TOKEN_KEY + user.getUserId(),
                token, JWTUtils.EXPIRE_TIME, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RedisUtils.PERMISSIONS_KEY + user.getUserId(),
                menuTree, JWTUtils.EXPIRE_TIME, TimeUnit.DAYS);

        LoginRepVo loginRepVo = new LoginRepVo();
        loginRepVo.setMenuTree(menuTree);
        loginRepVo.setToken(token);
        return CommonResult.success(loginRepVo);
    }
}
