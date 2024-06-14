package cuit9622.dms.college.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.common.entity.SchoolMajor;
import cuit9622.dms.college.mapper.SchoolMajorMapper;
import cuit9622.dms.college.service.SchoolMajorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SchoolMajorServiceImpl extends ServiceImpl<SchoolMajorMapper, SchoolMajor> implements SchoolMajorService {
    @Resource
    private SchoolMajorMapper schoolMajorMapper;

    @Override
    public Page<SchoolMajorVo> selectMajor(Integer page, Integer pageSize, String majorName) {
        Page<SchoolMajorVo> pageInfo = new Page<>(page, pageSize);
        pageInfo = schoolMajorMapper.page(pageInfo, majorName);
        return pageInfo;
    }

    @Override
    public int getByMajorName(String majorName, Long majorId) {
        int count = schoolMajorMapper.selectByName(majorName, majorId);
        return count;
    }

    @Override
    public int getClassByMajorId(Long majorId) {
        int count = schoolMajorMapper.selectClass(majorId);
        return count;
    }
}
