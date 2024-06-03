package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.sys.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Resource
    UserService userService;

    /**
     * 分页获取用户信息
     */
    @GetMapping("/users")
    public CommonResult<Page<User>> getUsers(@RequestParam int page, @RequestParam int pageSize) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getUserId, User::getNickName, User::getPhone, User::getUsername);
        Page<User> result = userService.page(new Page<>(page, pageSize));
        return CommonResult.success(result);
    }

}
