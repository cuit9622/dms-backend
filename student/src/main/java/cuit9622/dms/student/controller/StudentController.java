package cuit9622.dms.student.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.student.mapper.StudentMapper;
import cuit9622.dms.student.vo.StudentVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j(topic="StudentController")
@RestController
public class StudentController {
    @Resource
    private StudentMapper studentMapper;

    @GetMapping("/list")
    public CommonResult<Page<StudentVo>> getStudents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String name) {
        Page<StudentVo> result = new Page<>(page,pageSize);
        result = studentMapper.page(result,name);
        return CommonResult.success(result);
    }

}
