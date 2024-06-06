package cuit9622.dms.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.sys.entity.MenuRole;
import cuit9622.dms.sys.mapper.MenRoleMapper;
import cuit9622.dms.sys.service.MenuRoleService;
import org.springframework.stereotype.Service;

@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenRoleMapper, MenuRole>
        implements MenuRoleService {
}
