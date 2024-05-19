package cuit9622.dms.security.config;

import cuit9622.dms.security.filter.TokenFilter;
import cuit9622.dms.security.handler.SecurityExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@AutoConfiguration
public class SecurityBeanConfiguration {
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    @Order(-1) //提升优先级
    public SecurityExceptionHandler securityExceptionHandler() {
        return new SecurityExceptionHandler();
    }
}
