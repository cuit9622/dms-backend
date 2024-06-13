package cuit9622.dms.dormRepair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.dormRepair.entity.DormRepair;

public interface DormRepairService extends IService<DormRepair> {
    /**
     * 获取当前用户权限
     * @param userId
     * @return
     */
    int getRoleId(Long userId);

    /**
     * 管理员的报修页面的分页查询
     * @param page
     * @param pageSize
     * @param s
     * @return
     */
    Page<DormRepair> selectRepair(Integer page, Integer pageSize, String s);
}
