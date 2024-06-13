package cuit9622.dms.student.entity;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@TableName("student")
@ExcelIgnoreUnannotated
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {

    /**
     * 学生id
     */
    @TableId(type = IdType.AUTO)
    private Long stuId;
    /**
     * 学生姓名
     */

    @ExcelProperty(value="姓名",index = 0)
    private String name;
    /**
     * 性别
     */

    private Integer sex;
    /**
     * 班级
     */

    private Long classNumber;
    /**
     * 学号
     */

    @ExcelProperty(value="学号",index = 3)
    @ColumnWidth(15)
    private String stuNum;
    /**
     * 学院
     */

    private Long college;
    /**
     * 专业
     */

    private Long major;
    /**
     * 电话号码
     */

    @ExcelProperty(value="电话号码",index = 6)
    @ColumnWidth(15)
    private String tel;
}
