package cuit9622.dms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuit9622.dms.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lsh
 * @description 针对表【sys_user】的数据库操作Mapper
 * @createDate 2024-04-12 21:56:12
 * @Entity org.lsh.entity.SysUser
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> getAuthorities(Integer userId);

    @Select("select * from sys_user where username = #{username}")
    User getUserByUsername(String username);
}




