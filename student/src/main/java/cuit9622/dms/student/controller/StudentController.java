package cuit9622.dms.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.mapper.StudentMapper;
import cuit9622.dms.student.service.StudentService;
import cuit9622.dms.student.vo.StudentVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j(topic = "StudentController")
@RestController
public class StudentController {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentService studentService;

    /**
     * 获取学生分页信息
     */
    @GetMapping("/list")
    public CommonResult<Page<StudentVo>> getStudents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String name) {
        Page<StudentVo> result = new Page<>(page, pageSize);
        result = studentMapper.page(result, name);
        return CommonResult.success(result);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Long id) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStuId, id);
        return studentMapper.selectOne(wrapper);
    }

    /**
     * 新增学生信息
     */
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody Student student) {
        boolean flag = studentService.save(student);
        if(flag) {
            return CommonResult.success("添加成功");
        }
        else return CommonResult.error(500,"添加失败");
    }

    /**
     * 检查学号是否重复
     */
    @GetMapping("/check/{stuNum}")
    public CommonResult<Boolean> checkUsername(@PathVariable String stuNum) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(stuNum != null, Student::getStuNum,stuNum);
        Student result = studentService.getOne(wrapper);
        return CommonResult.success(Objects.isNull(result));
    }
}
