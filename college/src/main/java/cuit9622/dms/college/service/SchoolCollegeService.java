package cuit9622.dms.college.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.college.entity.SchoolCollege;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface SchoolCollegeService extends IService<SchoolCollege> {

    /**
     *
     * @param page
     * @param pageSize
     * @param collegeName
     * @return 返回分页好的学院信息
     */
    Page<SchoolCollege> selectCollege(Integer page, Integer pageSize, String collegeName);

    /**
     * 查询该学院名称是否已经存在
     * @param collegeName
     * @return
     */
    int getByCollegeName(String collegeName);

    /**
     * 删除时查询该学院下是否存在专业
     * @param collegeId
     * @return
     */
    int getMajorByCollegeId(Long collegeId);
}
