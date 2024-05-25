package cuit9622.dms.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_user
 */
@TableName("sys_user")
@Data
public class User implements Serializable {

    private Long userId;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String nickName;

    private Integer sex;

    private String avatar;

    private Date createTime;

    private Date updateTime;

}