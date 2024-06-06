package cuit9622.dms.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.common.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {


    /**
     * 分配权限
     * @param permissions
     * @return
     */
    boolean allotPermissions(List<Long> permissions, Long roleId);

}
