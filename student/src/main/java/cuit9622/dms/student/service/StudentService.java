package cuit9622.dms.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.listener.StudentReadListener;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService extends IService<Student> {
    void importExcel(MultipartFile file, StudentReadListener listener) throws IOException;
}
