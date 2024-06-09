package cuit9622.dms.notice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.notice.mapper.NoticeMapper;
import cuit9622.dms.notice.vo.NoticeVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @Resource
    private NoticeMapper noticeMapper;

    @GetMapping("/list")
    public CommonResult<Page<NoticeVo>> getNotices(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(required = false) String title) {
        Page<NoticeVo> result = new Page<>(page,pageSize);
        result = noticeMapper.page(result,title);
        return CommonResult.success(result);
    }
}
