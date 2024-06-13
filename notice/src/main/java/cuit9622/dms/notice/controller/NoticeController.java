package cuit9622.dms.notice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.notice.entity.Notice;
import cuit9622.dms.notice.mapper.NoticeMapper;
import cuit9622.dms.notice.service.NoticeService;
import cuit9622.dms.notice.vo.NoticeVo;
import cuit9622.dms.security.util.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NoticeController {
    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private NoticeService noticeService;

    /**
     * 分页查询公告信息
     * @param page 当前页数
     * @param pageSize 页面大小
     * @param title 公告标题
     * @return 公告分页信息
     */
    @GetMapping("/list")
    public CommonResult<Page<NoticeVo>> getNotices(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String title) {
        Page<NoticeVo> result = new Page<>(page,pageSize);
        result = noticeMapper.page(result,title);
        return CommonResult.success(result);
    }

    /**
     * 新增公告信息
     * @param notice 公告信息
     * @return 新增信息
     */
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody Notice notice) {
        notice.setCreator(SecurityUtil.getUserID());
        notice.setUpdateTime(new Date());
        notice.setCreateTime(new Date());
        noticeService.save(notice);
        return CommonResult.success("新增公告成功");
    }

    /**
     * 获取公告信息
     * @param noticeId 公告id
     * @return 公告信息
     */
    @GetMapping("/getOne/{noticeId}")
    public CommonResult<Notice> getOne(@PathVariable Long noticeId) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getNoticeId,noticeId);
        return CommonResult.success(noticeService.getOne(wrapper));
    }

    /**
     * 修改公告信息
     * @param notice 公告信息
     * @return 修改信息
     */
    @PutMapping("/edit")
    public CommonResult<String> update(@RequestBody Notice notice) {
        notice.setUpdateTime(new Date());
        boolean save = noticeService.updateById(notice);
        if(save) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.error(500,"修改失败");
    }

    /**
     * 删除一条公告信息
     * @param noticeId 公告id
     * @return 删除信息
     */
    @DeleteMapping("/delete/{noticeId}")
    public CommonResult<String> delete(@PathVariable Long noticeId) {
        boolean delete = noticeService.removeById(noticeId);
        if(delete) {
            return CommonResult.success("删除成功");
        }
        return  CommonResult.error(500,"删除失败");
    }

    /**
     * 批量删除公告
     * @param ids 公告id列表
     * @return 批量删除信息
     */
    @DeleteMapping("/delete")
    public CommonResult<String> deleteByids(@RequestBody List<Long> ids) {
        boolean delete = noticeService.removeBatchByIds(ids);
        if(delete) {
            return CommonResult.success("批量删除成功");
        }
        return CommonResult.error(500,"批量删除失败");
    }

}
