package cuit9622.dms.gateway.config;

import cuit9622.dms.gateway.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfigure {
    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }
}
