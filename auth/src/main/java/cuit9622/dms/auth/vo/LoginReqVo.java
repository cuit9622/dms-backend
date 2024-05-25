package cuit9622.dms.auth.vo;

import lombok.Data;

@Data
public class LoginReqVo {
    private String username;
    private String password;
    private String token;
}
