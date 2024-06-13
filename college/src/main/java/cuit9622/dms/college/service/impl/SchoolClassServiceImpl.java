package cuit9622.dms.college.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cuit9622.dms.college.Vo.SchoolClassVo;
import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.college.entity.SchoolClass;
import cuit9622.dms.college.mapper.SchoolClassMapper;
import cuit9622.dms.college.service.SchoolClassService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceImpl extends ServiceImpl<SchoolClassMapper, SchoolClass> implements SchoolClassService {

    @Resource
    private SchoolClassMapper schoolClassMapper;
    @Override
    public Page<SchoolClassVo> selectClass(Integer page, Integer pageSize, String className) {
        Page<SchoolClassVo> pageInfo = new Page<>(page, pageSize);
        pageInfo = schoolClassMapper.page(pageInfo, className);
        return pageInfo;
    }
}
