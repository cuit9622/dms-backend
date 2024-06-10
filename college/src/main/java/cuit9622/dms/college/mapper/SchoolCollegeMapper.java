package cuit9622.dms.college.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.college.entity.SchoolCollege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchoolCollegeMapper extends BaseMapper<SchoolCollege> {

    Page<SchoolCollege> page(@Param("page")Page<SchoolCollege> page, @Param("name")String collegeName);

    int selectByName(@Param("name")String collegeName);

    int selectMajor(@Param("id")Long collegeId);

    List<SchoolCollege> getAll();
}
