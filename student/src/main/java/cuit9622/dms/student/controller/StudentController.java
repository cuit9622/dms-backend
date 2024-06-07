package cuit9622.dms.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.service.StudentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j(topic="StudentController")
@RestController
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping("/students")
    public CommonResult<Page<Student>> getStudents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String name) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Student::getName,name);
        Page<Student> result = studentService.page(new Page<>(page, pageSize), wrapper);
        return CommonResult.success(result);
    }

}
