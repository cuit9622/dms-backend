package cuit9622.dms.dormRepair.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.dormRepair.entity.DormRepair;
import cuit9622.dms.dormRepair.service.DormRepairService;
import cuit9622.dms.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/repair")
public class DormRepairController {
    @Autowired
    private DormRepairService dormRepairService;

    //新增
    @PostMapping("/add")
    public CommonResult add(@RequestBody DormRepair dormRepair){
        //获取当前的用户id
        Long userId = SecurityUtil.getUserID();
        System.out.println(userId);
        //设置报修时间
        dormRepair.setUserId(userId);
        dormRepair.setRepairTime(new Date());
        boolean save = dormRepairService.save(dormRepair);
        if(save){
            return CommonResult.success("报修添加成功!");
        }
        return CommonResult.error(500,"报修添加失败!");
    }

    //编辑、报修
    @PutMapping("edit")
    public CommonResult edit(@RequestBody DormRepair dormRepair){
        //获取当前的用户id
        Long userId = SecurityUtil.getUserID();
        //查询当前用户角色
        int roleId = dormRepairService.getRoleId(userId);
        String msg1 = ""; //成功信息
        String msg2 = ""; //失败信息
        if(roleId == 1){
            msg1 = "处理成功";
            msg2 = "处理失败";
        }else{
            msg1 = "编辑成功";
            msg2 = "编辑失败";
        }

        boolean save = dormRepairService.updateById(dormRepair);
        if(roleId == 1){
            if(save){
                return CommonResult.success(msg1);
            }
            return CommonResult.error(500, msg1);
        }else if(roleId == 2){
            if(save){
                return CommonResult.success(msg2);
            }
            return CommonResult.error(500, msg2);
        }
        return CommonResult.error(500, "未知用户信息");
    }

    //删除
    @DeleteMapping("/{repairId}")
    public CommonResult delete(@PathVariable("repairId") Long repairId){
        boolean b = dormRepairService.removeById(repairId);
        if(b){
            return CommonResult.success("删除成功!");
        }
        return CommonResult.error(500, "编辑失败!");
    }

    //分页查询
    @GetMapping("/list")
    public CommonResult<Page<DormRepair>> getList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "dormName", required = false) String dormName) {
        //获取当前的用户id
        Long userId = SecurityUtil.getUserID();
        //查询当前用户角色
        int roleId = dormRepairService.getRoleId(userId);
        // roleId 1是管理员 2是学生
        if (roleId == 1) {
            Page<DormRepair> info = dormRepairService.selectRepair(page, pageSize, (dormName == null ? "" : dormName));
            return CommonResult.success(info);
        } else if (roleId == 2) {
            Page<DormRepair> info = dormRepairService.selectPersonalRepair(page, pageSize, userId);
            return CommonResult.success(info);
        }
        return CommonResult.error(500,"查询失败");
    }

    /**
     * 获取未报修的数量 用于前台展示
     * @return
     */
    @GetMapping("/getNum")
    public CommonResult<Long> getCount(){
        Long count = dormRepairService.getCount();
        return CommonResult.success(count);
    }
}
