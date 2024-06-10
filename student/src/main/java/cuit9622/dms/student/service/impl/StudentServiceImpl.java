package cuit9622.dms.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.mapper.StudentMapper;
import cuit9622.dms.student.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
