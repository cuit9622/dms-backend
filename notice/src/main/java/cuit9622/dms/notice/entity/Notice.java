package cuit9622.dms.notice.entity;


import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


@Data
public class Notice implements Serializable {

    /**
    * 主键
    */
    private Long noticeId;
    /**
    * 公告标题
    */
    private String title;
    /**
    * 公告内容
    */
    private String content;
    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date createTime;
    /**
    * 修改时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date updateTime;
    /**
    * 发布人
    */
    private Long creator;

}
