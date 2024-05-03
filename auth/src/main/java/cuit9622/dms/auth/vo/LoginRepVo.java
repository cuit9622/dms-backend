package cuit9622.dms.auth.vo;

import cuit9622.dms.common.entity.MenuTree;
import lombok.Data;

import java.util.List;

@Data
public class LoginRepVo {
    List<MenuTree> menuTree;
    String token;
}
