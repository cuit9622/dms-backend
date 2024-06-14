package cuit9622.dms.college.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.college.Vo.SchoolClassVo;
import cuit9622.dms.college.entity.SchoolClass;
import cuit9622.dms.college.service.SchoolClassService;
import cuit9622.dms.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/class")
public class SchoolClassController {
    @Autowired
    private SchoolClassService schoolClassService;

    //新增
    @PostMapping("add")
    public CommonResult add(@RequestBody SchoolClassVo schoolClass){

        //设置创建时间
        Date now = new Date(); // 获取当前日期
        Calendar calendar = Calendar.getInstance(); // 创建一个Calendar实例
        calendar.setTime(now); // 将当前日期设置为Calendar的时间
        int yearInt = calendar.get(Calendar.YEAR); // 获取年份（int类型）
        String yearString = Integer.toString(yearInt); // 将年份转换为字符串
        schoolClass.setClassYear(yearString);

        boolean save = schoolClassService.save(schoolClass);
        if(save){
            return CommonResult.success("新增班级成功!");
        }
        return CommonResult.error(500,"新增班级失败!");
    }

    //编辑
    @PutMapping("/edit")
    public CommonResult edit(@RequestBody SchoolClass schoolClass){

        boolean save = schoolClassService.updateById(schoolClass);
        if(save){
            return CommonResult.success("编辑班级成功!");
        }
        return CommonResult.error(500,"编辑班级失败!");
    }

    //删除
    @DeleteMapping("/{classId}")
    public CommonResult delete(@PathVariable("classId") Long classId){
        boolean b = schoolClassService.removeById(classId);
        if(b){
            return CommonResult.success("删除班级成功!");
        }
        return CommonResult.error(500, "编辑班级失败!");
    }

    //分页查询
    @GetMapping("/list")
    public CommonResult<Page<SchoolClassVo>> getList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "className", required = false) String className){
        Page<SchoolClassVo> info = schoolClassService.selectClass(page, pageSize, (className == null ? "" : className));
        return CommonResult.success(info);
    }

    @GetMapping("/getOne/{classId}")
    public CommonResult<SchoolClass> getOne(@PathVariable("classId") Long classId){
        SchoolClass info = schoolClassService.getById(classId);
        return CommonResult.success(info);
    }

    /**
     * 获取指定专业下的所有班级
     * @param majorId 专业名称
     * @return
     */
    @GetMapping("/getAll/{majorId}")
    public CommonResult<List<SchoolClass>> getAll(@PathVariable("majorId") Long majorId ){
        LambdaQueryWrapper<SchoolClass> wrapper = new LambdaQueryWrapper<>();
        wrapper = wrapper.eq(SchoolClass::getMajorId,majorId);
        return CommonResult.success(schoolClassService.list(wrapper));
    }

    @GetMapping("/className")
    public SchoolClass getByClassName(@RequestParam String className) {
        LambdaQueryWrapper<SchoolClass> wrapper = new LambdaQueryWrapper<>();
        wrapper = wrapper.eq(SchoolClass::getClassName,className);
        return schoolClassService.getOne(wrapper);
    }


}
