package cuit9622.engineerproject4.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @GetMapping("/hello")
    String hello(String name) {
        return "Hello " + name;
    }
}