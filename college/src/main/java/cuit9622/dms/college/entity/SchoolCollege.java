package cuit9622.dms.college.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("college")
public class SchoolCollege {

    @TableId(type = IdType.AUTO)
    private Long collegeId;

    private String collegeName;

    private Integer orderNum;

    private Date createTime;
}
