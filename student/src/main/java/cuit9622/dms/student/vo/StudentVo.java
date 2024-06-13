package cuit9622.dms.student.vo;

import cuit9622.dms.student.entity.Student;
import lombok.Data;

@Data
public class StudentVo extends Student {
    private String collegeName;
    private String majorName;
    private String className;
}
