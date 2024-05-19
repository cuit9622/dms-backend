package cuit9622.dms.auth.service.client;

import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "sys-service")
public interface SysClient {
    @GetMapping("/user")
    User getUserByUserName(@RequestParam String username);

    @GetMapping("/authorities")
    List<String> getAuthoritiesByUserId(@RequestParam Long userId);

    @GetMapping("/tree/{userId}")
    List<MenuTree> getMenuTree(@PathVariable Long userId);

    @GetMapping("/test")
    String test();
}
