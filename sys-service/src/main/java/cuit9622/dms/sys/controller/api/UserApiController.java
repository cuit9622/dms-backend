package cuit9622.dms.sys.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.exception.BizException;
import cuit9622.dms.common.model.ChangeInfoModel;
import cuit9622.dms.common.model.ChangePasswordModel;
import cuit9622.dms.common.util.DigestsUtils;
import cuit9622.dms.sys.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
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

    @PutMapping("/password")
    Integer changePassword(@RequestBody ChangePasswordModel data) {
        Map<String, String> map = DigestsUtils.encrypt(data.getNewPassword());
        return userMapper.update(new LambdaUpdateWrapper<User>()
                .set(User::getPassword, map.get("password"))
                .set(User::getSalt, map.get("salt"))
                .eq(User::getUserId, data.getUserId())
        );
    }

    @PutMapping("/info")
    Integer changeInfo(@RequestBody ChangeInfoModel data) {
        return userMapper.update(new LambdaUpdateWrapper<User>()
                .set(User::getPhone, data.getPhone())
                .set(User::getAvatar, data.getAvatar())
                .eq(User::getUserId, data.getUserId()));
    }

    @GetMapping("/test")
    public String test() {
        throw new BizException(114, "我要玩原神");
    }
}
