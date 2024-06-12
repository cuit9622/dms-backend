package cuit9622.dms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.sys.service.MenuService;
import cuit9622.dms.sys.tools.MyEnum;
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
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public CommonResult<List<MenuTree>> getMenuTree() {
        return CommonResult.success(menuService.allTreeMenu());
    }

    /**
     * 得到所有的非按钮id
     *
     * @return
     */
    @GetMapping("/getContent")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public CommonResult<List<MenuTree>> getContent() {
        List<MenuTree> treeMenu = menuService.getTreeMenu(null);
        return CommonResult.success(treeMenu);
    }

    /**
     * 获取改角色的菜单的id用于回显菜单数据
     *
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public CommonResult<List<Long>> getMenuTreeById(@PathVariable Long id) {
        return CommonResult.success(menuService.getTreeMenuByRoleId(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public CommonResult<String> addMenus(@RequestBody Menu menu) {
        System.out.println(menu);
        boolean save = menuService.save(menu);
        if (!save) {
            return CommonResult.success("添加失败");
        }
        return CommonResult.success("添加成功");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public CommonResult<String> updateMenus(@RequestBody Menu menu, @PathVariable Long id) {
        System.out.println(menu);
        return CommonResult.success("修改成功");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public CommonResult<String> updateMenus(@PathVariable String id) {
        System.out.println(id);
        return CommonResult.success("删除成功");
    }
}
