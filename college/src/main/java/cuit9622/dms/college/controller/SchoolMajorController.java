package cuit9622.dms.college.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cuit9622.dms.college.Vo.SchoolMajorVo;
import cuit9622.dms.college.entity.SchoolMajor;
import cuit9622.dms.college.service.SchoolMajorService;
import cuit9622.dms.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/major")
public class SchoolMajorController {

    @Autowired
    private SchoolMajorService schoolMajorService;

    //新增
    @PostMapping("/add")
    public CommonResult add(@RequestBody SchoolMajor schoolMajor){

        //查找该专业名称是否已存在
        int count = schoolMajorService.getByMajorName(schoolMajor.getMajorName(), -1L);
        //该专业已存在
        if(count == 1){
            return CommonResult.error(500,"该专业已经存在");
        }

        boolean save = schoolMajorService.save(schoolMajor);
        if(save){
            return CommonResult.success("新增专业成功!");
        }
        return CommonResult.error(500, "新增失败!");
    }

    //编辑
    @PutMapping("/edit")
    public CommonResult edit(@RequestBody SchoolMajor schoolMajor){

        //查找该专业名称是否已存在
        int count = schoolMajorService.getByMajorName(schoolMajor.getMajorName(), schoolMajor.getMajorId());
        //该专业已存在
        if(count == 1){
            return CommonResult.error(500,"该专业已经存在");
        }
        boolean save = schoolMajorService.updateById(schoolMajor);
        if(save){
            return CommonResult.success("编辑成功!");
        }
        return CommonResult.error(500,"编辑失败!");
    }

    //删除
    @DeleteMapping("/{majorId}")
    public CommonResult delete(@PathVariable("majorId") Long majorId){
        boolean b = schoolMajorService.removeById(majorId);
        if(b){
            return CommonResult.success("删除成功!");
        }
        return CommonResult.error(500, "删除失败!");
    }

    /**分页查询
     *
     * @param page
     * @param pageSize
     * @param majorName（可进行模糊查询）
     * @return 分页查询结果
     */
    @GetMapping("/list")
    public CommonResult<Page<SchoolMajorVo>> getList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "majorName", required = false) String majorName){
        Page<SchoolMajorVo> info = schoolMajorService.selectMajor(page, pageSize, (majorName == null ? "" : majorName));
        return CommonResult.success(info);
    }

    /**
     * 用于在编辑专业信息时回显表单信息
     * @param majorId
     * @return
     */
    @GetMapping("/getOne/{majorId}")
    public CommonResult<SchoolMajor> getOne(@PathVariable("majorId") Long majorId){
        SchoolMajor major = schoolMajorService.getById(majorId);
        return CommonResult.success(major);
    }

}
