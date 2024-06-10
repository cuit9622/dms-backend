package cuit9622.dms.college.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.college.entity.SchoolMajor;

public interface SchoolMajorService extends IService<SchoolMajor> {

    Page<SchoolMajorVo> selectMajor(Integer page, Integer pageSize, String majorName);

    int getByMajorName(String majorName, Long majorId);
}
