package cuit9622.dms.college.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.college.Vo.SchoolClassVo;
import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.college.entity.SchoolClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SchoolClassMapper extends BaseMapper<SchoolClass> {

    Page<SchoolClassVo> page(@Param("page")Page<SchoolClassVo> pageInfo, @Param("name") String className);
}
