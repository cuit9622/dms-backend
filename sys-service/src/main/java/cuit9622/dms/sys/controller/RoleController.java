package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.entity.Role;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.sys.Vo.UserVo;
import cuit9622.dms.sys.mapper.RoleMapper;
import cuit9622.dms.sys.mapper.UserMapper;
import cuit9622.dms.sys.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {


    @Resource
    RoleService roleService;

    @Resource
    RoleMapper roleMapper;

    /**
     * 查询当前用户是什么角色
     *
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:role:index')")
    public CommonResult<Role> getRoleById(@PathVariable("id") Long id) {
        Role role = roleMapper.getRoleByUserID(id);
        return CommonResult.success(role);
    }

    /**
     * 搜索查询所有角色
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:index')")
    public CommonResult<Page<Role>> search(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String roleName) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(roleName != null, Role::getRoleName, roleName);
        Page<Role> result = roleService.page(new Page<>(page, pageSize), wrapper);
        return CommonResult.success(result);
    }

}
