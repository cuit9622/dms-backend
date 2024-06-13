package cuit9622.dms.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    Page<StudentVo> page(@Param("page") Page<StudentVo> page, @Param("name")String name);

    List<StudentVo> getStudentVo();
}
