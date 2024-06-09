package cuit9622.dms.college.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("major")
public class SchoolMajor {

    @TableId(type = IdType.AUTO)
    private Long majorId;

    private Long collegeId;

    private String majorName;

    private Integer orderNum;
}
