package cuit9622.dms.gateway.vo;

import lombok.Data;

@Data
public class LoginReqVo {
    private String username;
    private String password;
    private String loginToken;
}
