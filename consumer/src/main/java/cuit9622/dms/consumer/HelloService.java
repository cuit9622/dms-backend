package cuit9622.dms.consumer;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider")
public interface HelloService {
    @GetMapping("/hello")
    String hello(@RequestParam String name);
}
