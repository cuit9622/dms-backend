package cuit9622.dms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.user.mapper.MenuMapper;
import cuit9622.dms.user.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lsh
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2024-04-13 16:56:49
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<MenuTree> getTreeMenu(Integer userId) {
        List<Menu> menus = menuMapper.getMenuByUserId(userId);
        Map<Integer, MenuTree> map = new HashMap<>();
        List<MenuTree> list = new ArrayList<>();
        // 将数据全部放在map集合中
        for (Menu menu : menus) {
            MenuTree menuTree = new MenuTree();
            menuTree.setMenu(menu);
            if (menu.getParentId() == 0) {
                list.add(menuTree);
            }

            // 排除掉按钮
            if (menu.getType() != 2) {
                map.put(menu.getMenuId(), menuTree);
            }
        }
        for (Map.Entry<Integer, MenuTree> entry : map.entrySet()) {
            MenuTree value = entry.getValue();
            List<MenuTree> children;
            // 以父id为key如果能查到代表该节点有子节点
            Integer parentId = value.getMenu().getParentId();
            MenuTree parent = map.get(parentId);
            if (parent != null) {
                children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(value);
            }
        }
        return list;
    }
}




