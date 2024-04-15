package cuit9622.dms.common.consumer;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Resource
    private HelloService helloService;

    @GetMapping("/test")
    String test(String name) {
        return helloService.hello(name);
    }
}
