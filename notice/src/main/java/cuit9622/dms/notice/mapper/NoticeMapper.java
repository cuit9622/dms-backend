package cuit9622.dms.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cuit9622.dms.notice.entity.Notice;
import cuit9622.dms.notice.vo.NoticeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    Page<NoticeVo> page(@Param("page") Page<NoticeVo> page, @Param("title") String title);
}
