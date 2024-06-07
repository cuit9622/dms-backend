package cuit9622.dms.student.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.student.entity.SelectModel;
import cuit9622.dms.student.entity.Student;
import org.springframework.stereotype.Service;

public interface StudentService extends IService<Student> {
    Page<Student> selectStudents(SelectModel model);
}
