package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuit9622.dms.common.entity.Role;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.sys.mapper.RoleMapper;
import cuit9622.dms.sys.mapper.UserMapper;
import cuit9622.dms.sys.service.RoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询所有角色
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:index')")
    public CommonResult<List<Role>> getRoles() {
        return CommonResult.success(roleService.list());
    }

    /**
     * 查询当前用户是什么角色
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:role:index')")
    public CommonResult<Role> getRoleById(@PathVariable("id") Long id) {
        Role role = roleMapper.getRoleByUserID(id);
        return CommonResult.success(role);
    }
}
