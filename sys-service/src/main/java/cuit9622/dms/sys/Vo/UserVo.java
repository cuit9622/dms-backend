package cuit9622.dms.sys.Vo;

import cuit9622.dms.common.entity.User;
import lombok.Data;

@Data
public class UserVo extends User {
    private Long roleId;
}
