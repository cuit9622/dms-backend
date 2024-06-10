package cuit9622.dms.college.controller;

import cuit9622.dms.college.entity.SchoolCollege;
import cuit9622.dms.college.service.SchoolCollegeService;
import cuit9622.dms.common.model.CommonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/college")
public class SchoolCollegeController {

    @Resource
    private SchoolCollegeService schoolCollegeService;

    /**
     * 新增学院信息
     * @param schoolCollege
     * @return
     */
    @PostMapping("/add")
    public CommonResult add(@RequestBody SchoolCollege schoolCollege){
        //设置创建时间
        schoolCollege.setCreateTime(new Date());

        //查找该学院名称是否已存在
        int count = schoolCollegeService.getByCollegeName(schoolCollege.getCollegeName());
        //该学院已存在
        if(count == 1){
            return CommonResult.error(500,"该学院已经存在");
        }
        boolean save = schoolCollegeService.save(schoolCollege);
        if(save){
            return CommonResult.success("新增学院成功!");
        }
        return CommonResult.error(500,"新增学院失败!");
    }

    /**
     * 编辑学院信息
     * @param schoolCollege
     * @return
     */
    @PutMapping("/edit")
    public CommonResult edit(@RequestBody SchoolCollege schoolCollege){

        //查找该学院名称是否已存在
        int count = schoolCollegeService.getByCollegeName(schoolCollege.getCollegeName());
        //该学院已存在
        if(count == 1){
            return CommonResult.error(500,"该学院已经存在");
        }

        boolean save = schoolCollegeService.updateById(schoolCollege);

        if(save){
            return CommonResult.success("编辑学院成功!");
        }
        return CommonResult.error(500,"编辑学院失败!");
    }

    /**
     * 删除学院信息
     * @param collegeId
     * @return
     */
    @DeleteMapping("/{collegeId}")
    public CommonResult delete(@PathVariable("collegeId") Long collegeId){

        int count = schoolCollegeService.getMajorByCollegeId(collegeId);
        //该学院下有专业，不能删除
        if(count > 0){
            return CommonResult.error(500, "该学院下含有专业，删除失败!");
        }

        boolean b = schoolCollegeService.removeById(collegeId);

        if(b){
            return CommonResult.success("删除学院成功!");
        }
        return CommonResult.error(500, "删除学院失败!");
    }

    /**分页查询
     *
     * @param page
     * @param pageSize
     * @param collegeName（可进行模糊查询）
     * @return 分页查询结果
     */
    @GetMapping("/list")
    public CommonResult<Page<SchoolCollege>> getList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "collegeName", required = false) String collegeName){
        Page<SchoolCollege> info = schoolCollegeService.selectCollege(page, pageSize, (collegeName == null ? "" : collegeName));
        return CommonResult.success(info);
    }
    @GetMapping("/getAll")
    public CommonResult<List<SchoolCollege>> getAll(){
        List<SchoolCollege> list = schoolCollegeService.list();
        return CommonResult.success(list);
    }
    /**
     * 用于在编辑学院信息时回显表单信息
     * @param collegeId
     * @return
     */
    @GetMapping("/getOne/{collegeId}")
    public CommonResult<SchoolCollege> getOne( @PathVariable("collegeId") Long collegeId){
        SchoolCollege college = schoolCollegeService.getById(collegeId);
        return CommonResult.success(college);
    }
}
