package cuit9622.dms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.common.entity.Role;
import cuit9622.dms.sys.mapper.RoleMapper;
import cuit9622.dms.sys.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
}
