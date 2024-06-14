package cuit9622.dms.college.Vo;

import cuit9622.dms.common.entity.SchoolClass;
import lombok.Data;

@Data
public class SchoolClassVo extends SchoolClass {
    private String collegeName;
    private Long collegeId;
    private String majorName;
}
