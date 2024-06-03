package cuit9622.dms.auth.service.client;

import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.common.entity.User;
import cuit9622.dms.common.model.ChangeInfoModel;
import cuit9622.dms.common.model.ChangePasswordModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sys-service")
public interface SysClient {
    @GetMapping("/api/user")
    User getUserByUserName(@RequestParam String username);

    @GetMapping("/api/user/{userId}")
    User getUserById(@PathVariable Long userId);

    @GetMapping("/api/authorities")
    List<String> getAuthoritiesByUserId(@RequestParam Long userId);

    @GetMapping("/api/tree/{userId}")
    List<MenuTree> getMenuTree(@PathVariable Long userId);

    @PutMapping("/api/password")
    Integer changePassword(@RequestBody ChangePasswordModel data);

    @PutMapping("/api/info")
    Integer changeInfo(@RequestBody ChangeInfoModel data);

    @GetMapping("/api/test")
    String test();
}
