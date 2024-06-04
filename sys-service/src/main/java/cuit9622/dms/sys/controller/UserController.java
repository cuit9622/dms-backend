package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.sys.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 分页获取用户信息
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:index')")
    public CommonResult<Page<User>> getUsers(@RequestParam int page, @RequestParam int pageSize) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getUserId, User::getNickName, User::getPhone, User::getUsername);
        Page<User> result = userService.page(new Page<>(page, pageSize), wrapper);
        return CommonResult.success(result);
    }

    /*
    根据username获取用户
     */
    @GetMapping("/username/{username}/{isEdit}")
    @PreAuthorize("hasAuthority('sys:user:index')")
    public CommonResult<Boolean> checkUsername(@PathVariable String username, @PathVariable boolean isEdit) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(username != null, User::getUsername, username);
        User result = userService.getOne(wrapper);
        // 判断当前登录的用户是否和查到的相同
        if (isEdit && result != null) {
            Long userId = result.getUserId();
            Long id = ((DMSUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getID();
            if (userId == id) {
                return CommonResult.success(true);
            }
        }
        return CommonResult.success(Objects.isNull(result));
    }
}
