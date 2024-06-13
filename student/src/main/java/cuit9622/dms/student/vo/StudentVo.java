package cuit9622.dms.student.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import cuit9622.dms.student.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ExcelIgnoreUnannotated
@EqualsAndHashCode(callSuper = true)
public class StudentVo extends Student {
    @ExcelProperty(value="学院",index = 5)
    @ColumnWidth(15)
    private String collegeName;
    @ExcelProperty(value="专业",index = 4)
    @ColumnWidth(15)
    private String majorName;
    @ExcelProperty(value="班级",index = 2)
    private String className;
    @ExcelProperty(value="性别",index = 1)
    private String sexName;
}
