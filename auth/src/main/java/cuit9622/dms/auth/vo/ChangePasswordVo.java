package cuit9622.dms.auth.vo;

import lombok.Data;

@Data
public class ChangePasswordVo {
    private String oldPassword;
    private String newPassword;
}
