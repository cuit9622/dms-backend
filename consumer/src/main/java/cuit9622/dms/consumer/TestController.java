package cuit9622.dms.consumer;

import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.security.util.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Resource
    private HelloService helloService;

    @GetMapping("/test")
    String test(String name) {
        DMSUserDetail userDetail = SecurityUtil.getUserDetail();
        return helloService.hello(name);
    }
}
