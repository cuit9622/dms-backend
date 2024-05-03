package cuit9622.dms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuit9622.dms.common.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author lsh
 * @description 针对表【sys_menu】的数据库操作Mapper
 * @createDate 2024-04-13 16:56:49
 * @Entity org.lsh.entity.Menu
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select\n" +
            "\tsm.menu_id,\n" +
            "\tparent_id,\n" +
            "\ttitle,\n" +
            "\tcode,\n" +
            "\tname,\n" +
            "\tmenu_url,\n" +
            "\troute_path,\n" +
            "\tcomponent_path,\n" +
            "\t`type`,\n" +
            "\ticon,\n" +
            "\tparent_name,\n" +
            "\torder_num\n" +
            "from\n" +
            "\tsys_user_role sur\n" +
            "inner join sys_role_menu srm on\n" +
            "\tsrm.role_id = sur.role_id\n" +
            "inner join sys_menu sm on\n" +
            "\tsm.menu_id = srm.menu_id\n" +
            "where\n" +
            "\tsur.user_id = #{userId}")
    List<Menu> getMenuByUserId(Integer userId);
}




