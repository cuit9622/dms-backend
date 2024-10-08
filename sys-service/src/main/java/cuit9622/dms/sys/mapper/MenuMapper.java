package cuit9622.dms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuit9622.dms.common.entity.Menu;
import cuit9622.dms.sys.Vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenuByUserId(@Param("userId") Long userId);

    List<Menu> getMenus();

    List<UserRoleVo> getMenusByRoleId(@Param("roleId") Long roleId);
}