package cuit9622.dms.student.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("student")
public class Student implements Serializable {

    /**
     * 学生id
     */
    @TableId(type = IdType.AUTO)
    private Long stuId;
    /**
     * 学生姓名
     */


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

    private String tel;
}
