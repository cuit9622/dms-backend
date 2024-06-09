package cuit9622.dms.notice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.notice.entity.Notice;
import cuit9622.dms.notice.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @GetMapping("/list")
    public CommonResult<Page<Notice>> getNotices(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String title) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(title != null, Notice::getTitle,title);
        Page<Notice> result = noticeService.page(new Page<>(page,pageSize),wrapper);
        return CommonResult.success(result);
    }
}
