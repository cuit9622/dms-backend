package cuit9622.dms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuit9622.dms.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<String> getAuthorities(Long userId);

    @Select("select * from sys_user where username = #{username}")
    User getUserByUsername(String username);
}




