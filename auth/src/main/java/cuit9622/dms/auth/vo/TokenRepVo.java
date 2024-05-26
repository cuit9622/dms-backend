package cuit9622.dms.auth.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TokenRepVo {
    private Long userId;

    private String username;

    private String phone;

    private String nickName;

    private Integer sex;

    private String avatar;

    private Date createTime;

    private Date updateTime;
}
