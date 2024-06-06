package cuit9622.dms.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.common.entity.Role;
import cuit9622.dms.sys.entity.MenuRole;
import cuit9622.dms.sys.entity.UserRole;
import cuit9622.dms.sys.mapper.MenuMapper;
import cuit9622.dms.sys.mapper.RoleMapper;
import cuit9622.dms.sys.mapper.UserRoleMapper;
import cuit9622.dms.sys.service.MenuRoleService;
import cuit9622.dms.sys.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    MenuRoleService menuRoleService;

    @Resource
    MenuMapper menuMapper;

    @Override
    public boolean allotPermissions(List<Long> permissions, Long roleId) {
        // 删除当前用户的所有权限
        LambdaUpdateWrapper<MenuRole> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MenuRole::getRoleId, roleId);
        menuRoleService.remove(wrapper);

        // 添加当前的用户权限
        // 查询当前权限的父id
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Menu::getParentId)
                .in(Menu::getMenuId, permissions);
        List<Long> collect = menuMapper.selectList(queryWrapper).stream().map(Menu::getParentId).toList();

        // 将父id放到permissions中
        Set<Long> per = new HashSet<>(permissions);
        Set<Long> parent = new HashSet<>(collect);
        for (Long item : parent) {
            if (!per.contains(item)) {
                permissions.add(item);
            }
        }
        // 添加权限
        List<MenuRole> menuRoles = new ArrayList<>();
        for (Long menId : permissions) {
            MenuRole menuRole = new MenuRole();
            menuRole.setRoleId(roleId);
            menuRole.setMenuId(menId);
            menuRoles.add(menuRole);
        }
        return menuRoleService.saveBatch(menuRoles);
    }
}
