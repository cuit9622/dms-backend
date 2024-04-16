package cuit9622.dms.gateway.vo;

import lombok.Data;

@Data
public class LoginRespVo {
    private String refreshToken;
    private String accessToken;
}
