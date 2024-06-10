package cuit9622.dms.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.college.entity.SchoolCollege;
import cuit9622.dms.college.mapper.SchoolCollegeMapper;
import cuit9622.dms.college.service.SchoolCollegeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolCollegeServiceImpl extends ServiceImpl<SchoolCollegeMapper, SchoolCollege> implements SchoolCollegeService {

    @Resource
    private SchoolCollegeMapper schoolCollegeMapper;

    @Override
    public Page<SchoolCollege> selectCollege(Integer page, Integer pageSize, String collegeName) {
        Page<SchoolCollege> pageInfo = new Page<>(page, pageSize);
        pageInfo = schoolCollegeMapper.page(pageInfo, collegeName);
        return pageInfo;
    }

    @Override
    public int getByCollegeName(String collegeName) {
        int count = schoolCollegeMapper.selectByName(collegeName);
        return count;
    }

    @Override
    public int getMajorByCollegeId(Long collegeId) {
        int count = schoolCollegeMapper.selectMajor(collegeId);
        return count;
    }

    @Override
    public List<SchoolCollege> getAll() {
        List<SchoolCollege> list = schoolCollegeMapper.getAll();
        return list;
    }
}
