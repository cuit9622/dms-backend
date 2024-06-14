package cuit9622.dms.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("class")
public class SchoolClass {

    @TableId(type = IdType.AUTO)
    private Long classId;

    private Long majorId;

    private String className;

    private String classYear;

    private Integer orderNum;
}
