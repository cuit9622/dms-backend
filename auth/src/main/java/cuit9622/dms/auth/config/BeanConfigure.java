package cuit9622.dms.auth.config;

import cuit9622.dms.auth.vo.CloudflareRepVo;
import cuit9622.dms.auth.vo.CloudflareReqVo;
import cuit9622.dms.common.util.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {
    @Bean
    public RestClient<CloudflareReqVo, CloudflareRepVo> restClient() {
        return new RestClient<>();
    }
}
