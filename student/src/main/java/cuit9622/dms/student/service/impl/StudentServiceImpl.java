package cuit9622.dms.student.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.listener.StudentReadListener;
import cuit9622.dms.student.mapper.StudentMapper;
import cuit9622.dms.student.service.StudentService;
import cuit9622.dms.student.vo.StudentVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    public void importExcel(MultipartFile file, StudentReadListener listener) throws IOException {
        // 读取文件的流
        InputStream is = file.getInputStream();
        EasyExcel.read(is,
                        StudentVo.class,
                        listener).sheet(0) // 读第几个工作表
                .headRowNumber(1) // 列头占几行
                .doRead();
    }
}
