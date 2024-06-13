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

import java.util.List;
import java.util.Objects;


@Slf4j(topic = "StudentController")
@RestController
public class StudentController {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentService studentService;

    /**
     *
     * @param page 当前页
     * @param pageSize 页面大小
     * @param name 搜索姓名
     * @return 学生分页信息
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
     * 通过stuId获取学生信息
     * @param stuId 学生id
     * @return 学生信息
     */
    @GetMapping("/getOne/{stuId}")
    public CommonResult<Student> getOne(@PathVariable Long stuId) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getStuId, stuId);
        return CommonResult.success(studentService.getOne(wrapper));
    }

    /**
     * 新增学生信息
     * @param student 学生实体
     * @return 添加信息
     */
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody Student student) {
        boolean save = studentService.save(student);
        if(save) {
            return CommonResult.success("添加成功");
        }
        else return CommonResult.error(500,"添加失败");
    }

    /**
     * 检查学号是否重复
     * @param stuNum 学号
     * @return 检查结果
     */
    @GetMapping("/check/{stuNum}")
    public CommonResult<Boolean> checkUsername(@PathVariable String stuNum) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(stuNum != null, Student::getStuNum,stuNum);
        Student result = studentService.getOne(wrapper);
        return CommonResult.success(Objects.isNull(result));
    }

    /**
     * 修改学生信息
     * @param student 学生实体
     * @return 修改信息
     */
    @PutMapping("/edit")
    public CommonResult<String> update(@RequestBody Student student) {
        boolean save = studentService.updateById(student);
        if(save) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.error(500,"修改失败");
    }

    /**
     * 通过stuId删除学生
     * @param stuId 学生id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{stuId}")
    public CommonResult<String> delete(@PathVariable Long stuId) {
        boolean delete = studentService.removeById(stuId);
        if(delete) {
            return CommonResult.success("删除成功");
        }
        return  CommonResult.error(500,"删除失败");
    }

    /**
     * 批量删除学生信息
     * @param ids 学生id列表
     * @return 批量删除信息
     */
    @DeleteMapping("/delete")
    public CommonResult<String> deleteByids(@RequestBody List<Long> ids) {
        boolean delete = studentService.removeBatchByIds(ids);
        if(delete) {
            return CommonResult.success("批量删除成功");
        }
        return CommonResult.error(500,"批量删除失败");
    }
}
