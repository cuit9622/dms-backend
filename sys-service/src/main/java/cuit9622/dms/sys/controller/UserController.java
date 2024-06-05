package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.common.util.DigestsUtils;
import cuit9622.dms.sys.Vo.UserVo;
import cuit9622.dms.sys.entity.UserRole;
import cuit9622.dms.sys.mapper.RoleMapper;
import cuit9622.dms.sys.mapper.UserRoleMapper;
import cuit9622.dms.sys.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    RoleMapper roleMapper;

    @Resource
    UserRoleMapper userRoleMapper;

    /**
     * 搜索
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:index')")
    public CommonResult<Page<User>> search(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String nickname) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getUserId, User::getNickName, User::getPhone, User::getUsername, User::getSex)
                .like(nickname != null, User::getNickName, nickname);
        Page<User> result = userService.page(new Page<>(page, pageSize), wrapper);
        return CommonResult.success(result);
    }

    /*
    根据username获取用户
     */
    @GetMapping("/{username}/{isEdit}")
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

    /**
     * 添加用户
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @Transactional
    public CommonResult<String> add(@RequestBody UserVo user) {
        System.out.println(user);
        // 修改时间
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // 创建密码
        Map<String, String> map = DigestsUtils.getPassword();
        String salt = map.get(DigestsUtils.SALT);
        String pwd = map.get(DigestsUtils.PASSWORD);
        user.setSalt(salt);
        user.setPassword(pwd);
        userService.save(user);

        // 添加角色信息
        UserRole userRole = new UserRole();
        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getUserId());
        userRoleMapper.insert(userRole);
        return CommonResult.success("添加成功");
    }

    /**
     * 编辑用户
     */
    @PutMapping("/edit")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public CommonResult<String> update(@RequestBody User user) {
        System.out.println(user);
        return CommonResult.success("修改成功");
    }
}
