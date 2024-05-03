package cuit9622.dms.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.common.entity.MenuTree;

import java.util.List;

/**
 * @author lsh
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2024-04-13 16:56:49
 */
public interface MenuService extends IService<Menu> {

    List<MenuTree> getTreeMenu(Integer userId);
}
