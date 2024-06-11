package cuit9622.dms.college.controller;


import cuit9622.dms.college.entity.SchoolClass;
import cuit9622.dms.college.service.SchoolClassService;
import cuit9622.dms.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SchoolClassController {
    @Autowired
    private SchoolClassService schoolClassService;

    //新增
    @PostMapping
    public CommonResult add(@RequestBody SchoolClass schoolClass){

        boolean save = schoolClassService.save(schoolClass);
        if(save){
            return CommonResult.success("新增班级成功!");
        }
        return CommonResult.error(500,"新增班级失败!");
    }

    //编辑
    @PutMapping
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
            return CommonResult.success("删除学院成功!");
        }
        return CommonResult.error(500, "编辑学院失败!");
    }

    //分页查询
    @GetMapping("/list")
    public CommonResult getList(){

        return CommonResult.error(500, "分页失败!");
    }
}
