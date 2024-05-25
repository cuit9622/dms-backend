package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.sys.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserApiController {

    @Resource
    UserMapper userMapper;

    @GetMapping("/user")
    public User getUserByUserName(String username) {
        return userMapper.getUserByUsername(username);
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
    }

    @GetMapping("/authorities")
    public List<String> getAuthoritiesByUserId(Long userId) {
        return userMapper.getAuthorities(userId);
    }

    @GetMapping("/test")
    public String test() {
        throw new BizException(114, "我要玩原神");
    }
}
