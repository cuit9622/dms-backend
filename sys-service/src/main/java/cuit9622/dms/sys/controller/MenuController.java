package cuit9622.dms.sys.controller;

import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.sys.service.MenuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;

    /**
     * 查询当前用户的权限信息并返回
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:index')")
    public CommonResult<List<MenuTree>> getMenuTree() {
        return CommonResult.success(menuService.allTreeMenu());
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:menu:index')")
    public CommonResult<List<MenuTree>> getMenuTreeById(@PathVariable Long id) {
        return CommonResult.success(menuService.getTreeMenuByRoleId(id));
    }
}
