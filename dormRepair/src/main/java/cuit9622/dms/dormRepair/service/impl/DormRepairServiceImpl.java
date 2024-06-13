package cuit9622.dms.dormRepair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.dormRepair.service.DormRepairService;
import cuit9622.dms.dormRepair.entity.DormRepair;
import cuit9622.dms.dormRepair.mapper.DormRepairMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DormRepairServiceImpl extends ServiceImpl<DormRepairMapper, DormRepair> implements DormRepairService {

    @Resource
    private DormRepairMapper dormRepairMapper;

    @Override
    public int getRoleId(Long userId) {
        int roleId = dormRepairMapper.getRoleId(userId);
        return roleId;
    }

    @Override
    public Page<DormRepair> selectRepair(Integer page, Integer pageSize, String dormName) {
        Page<DormRepair> pageInfo = new Page<>(page, pageSize);
        pageInfo = dormRepairMapper.page(pageInfo, dormName);
        return pageInfo;
    }
}