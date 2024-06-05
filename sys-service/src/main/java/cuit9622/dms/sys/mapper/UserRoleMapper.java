package cuit9622.dms.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuit9622.dms.sys.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Update("update sys_user_role set role_id = #{roleId} where user_id = #{userId}")
    int updateRole(Long userId, Long roleId);
}
