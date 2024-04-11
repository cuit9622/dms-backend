package cuit9622.engineerproject4.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/test")
    String test(String name) {
        return helloService.hello(name);
    }
}
