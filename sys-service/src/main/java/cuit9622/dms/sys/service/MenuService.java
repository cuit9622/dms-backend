package cuit9622.dms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.common.entity.MenuTree;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<MenuTree> getTreeMenu(Long userId);
}
