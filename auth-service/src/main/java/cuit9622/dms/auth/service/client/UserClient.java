package cuit9622.dms.auth.service.client;

import cuit9622.dms.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userService", path = "/user/controller")
public interface UserClient {
    @GetMapping("/user/{username}")
    User getUserByUserName(@PathVariable String username);
}
