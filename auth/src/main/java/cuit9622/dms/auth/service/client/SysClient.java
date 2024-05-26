package cuit9622.dms.auth.service.client;

import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.ChangePasswordModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sys-service")
public interface SysClient {
    @GetMapping("/user")
    User getUserByUserName(@RequestParam String username);

    @GetMapping("/user/{userId}")
    User getUserById(@PathVariable Long userId);

    @GetMapping("/authorities")
    List<String> getAuthoritiesByUserId(@RequestParam Long userId);

    @GetMapping("/tree/{userId}")
    List<MenuTree> getMenuTree(@PathVariable Long userId);

    @PutMapping("/password")
    Integer changePassword(@RequestBody ChangePasswordModel data);

    @GetMapping("/test")
    String test();
}
