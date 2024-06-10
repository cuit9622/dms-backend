package cuit9622.dms.notice.controller;

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
     * @return 公告信息
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
     * @return 成功信息
     */
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody Notice notice) {
        notice.setCreator(SecurityUtil.getUserID());
        notice.setUpdateTime(new Date());
        notice.setCreateTime(new Date());
        noticeService.save(notice);
        return CommonResult.success("新增公告成功");
    }
}
