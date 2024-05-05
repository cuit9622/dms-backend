package cuit9622.dms.common.config;

import cuit9622.dms.common.handler.CommonExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class CommonConfigure {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public CommonExceptionHandler commonExceptionHandler() {
        return new CommonExceptionHandler(applicationName);
    }
}
