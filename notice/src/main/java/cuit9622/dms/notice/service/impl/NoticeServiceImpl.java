package cuit9622.dms.notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuit9622.dms.notice.entity.Notice;
import cuit9622.dms.notice.mapper.NoticeMapper;
import cuit9622.dms.notice.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
}
