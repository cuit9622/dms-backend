package cuit9622.dms.college.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.college.entity.SchoolMajor;

public interface SchoolMajorService extends IService<SchoolMajor> {

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param majorName
     * @return
     */
    Page<SchoolMajorVo> selectMajor(Integer page, Integer pageSize, String majorName);

    /**通过专业名称看是否和其他专业名一致
     * @param majorName
     * @param majorId
     * @return
     */
    int getByMajorName(String majorName, Long majorId);

    /**
     * 删除时查询该专业下是否含有在读班级
     * @param majorId
     * @return
     */
    int getClassByMajorId(Long majorId);
}
