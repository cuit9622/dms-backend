package cuit9622.dms.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SysService {
    public static void main(String[] args) {
        SpringApplication.run(SysService.class, args);
    }
}