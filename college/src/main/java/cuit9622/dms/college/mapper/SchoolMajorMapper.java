package cuit9622.dms.college.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.common.entity.SchoolMajor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SchoolMajorMapper extends BaseMapper<SchoolMajor> {

    Page<SchoolMajorVo> page(@Param("page")Page<SchoolMajorVo> page, @Param("name")String majorName);

    int selectByName( @Param("name") String majorName, @Param("id") Long majorId);

    int selectClass(@Param("id") Long majorId);
}
