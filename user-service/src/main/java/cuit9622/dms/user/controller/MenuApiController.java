package cuit9622.dms.user.controller;

import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.user.service.MenuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MenuApiController {

    @Resource
    MenuService menuService;

    @GetMapping("/tree/{userId}")
    public List<MenuTree> getMenuTree(@PathVariable Long userId) {
        return menuService.getTreeMenu(userId);
    }
}
