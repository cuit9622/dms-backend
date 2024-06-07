package cuit9622.dms.student.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.student.entity.SelectModel;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.mapper.StudentMapper;
import cuit9622.dms.student.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public Page<Student> selectStudents(SelectModel model) {
        Page<Student> pageInfo = new Page<>(model.getPage(),model.getPageSize());
        pageInfo = studentMapper.selectStudent(pageInfo,model.getName());
        return pageInfo;
    }
}
