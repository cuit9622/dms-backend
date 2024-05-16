package cuit9622.dms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.common.entity.MenuTree;
import cuit9622.dms.sys.mapper.MenuMapper;
import cuit9622.dms.sys.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<MenuTree> getTreeMenu(Long userId) {
        List<Menu> menus = menuMapper.getMenuByUserId(userId);
        Map<Long, MenuTree> map = new HashMap<>();
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
        for (Map.Entry<Long, MenuTree> entry : map.entrySet()) {
            MenuTree value = entry.getValue();
            List<MenuTree> children;
            // 以父id为key如果能查到代表该节点有子节点
            Long parentId = value.getMenu().getParentId();
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
