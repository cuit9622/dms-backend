package cuit9622.dms.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_role")
public class UserRole {

    Long userRoleId;

    Long userId;

    Long roleId;

}
