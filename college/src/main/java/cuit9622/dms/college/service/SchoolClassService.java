package cuit9622.dms.college.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.college.Vo.SchoolClassVo;
import cuit9622.dms.common.entity.SchoolClass;


public interface SchoolClassService extends IService<SchoolClass> {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param className
     * @return
     */
    Page<SchoolClassVo> selectClass(Integer page, Integer pageSize, String className);
}
