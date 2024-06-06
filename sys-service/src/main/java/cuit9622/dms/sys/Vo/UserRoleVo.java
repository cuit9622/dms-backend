package cuit9622.dms.sys.Vo;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleVo {

    private Long menuId;

    private Long parentId;

    private List<Long> permissions;
}
