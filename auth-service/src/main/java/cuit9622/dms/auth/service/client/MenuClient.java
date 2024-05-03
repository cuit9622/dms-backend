package cuit9622.dms.auth.service.client;

import cuit9622.dms.common.entity.MenuTree;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "menuService", path = "/menu/controller")
public interface MenuClient {

    @GetMapping("/tree/{userId}")
    List<MenuTree> getMenuTree(@PathVariable Integer userId);
}
