package cuit9622.dms.student.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cuit9622.dms.common.entity.SchoolClass;
import cuit9622.dms.common.entity.SchoolCollege;
import cuit9622.dms.common.entity.SchoolMajor;
import cuit9622.dms.student.entity.Student;
import cuit9622.dms.student.service.StudentService;
import cuit9622.dms.student.service.excel.StudentExcel;
import cuit9622.dms.student.vo.StudentVo;

import java.util.ArrayList;
import java.util.List;

public class StudentReadListener implements ReadListener<StudentVo> {
    // 封装学生信息列表
    List<Student> list = new ArrayList<>();
    private StudentExcel studentExcel;

    private StudentService studentService;

    // 错误信息列表
    private List<String> info = new ArrayList<>();
    // 校验通过标识
    private boolean flag = true;

    public List<String> getInfo() {
        return this.info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public StudentReadListener(StudentExcel studentExcel, StudentService studentService) {
        this.studentExcel =  studentExcel;
        this.studentService = studentService;
    }
    @Override
    public void invoke(StudentVo studentVo, AnalysisContext analysisContext) {
        String error = studentVo.getName()+":";
        boolean singleFlag = true;

        // 检验姓名
        String name = studentVo.getName();
        if(name == null || name.equals("")) {
            flag = false;
            singleFlag = false;
            error += "  [未输入姓名]";
        }
        else {
            // 检验姓名格式
            if(!name.matches("^([\\u4e00-\\u9fa5]{2,4}|[a-zA-Z]{2,16})$")) {
                flag = false;
                singleFlag = false;
                error += "  [姓名(" + name+")格式错误]";
            }
        }

        // 检验性别
        String sexName = studentVo.getSexName();
        if(sexName == null || sexName.equals("")) {
            singleFlag = false;
            flag = false;
            error += "  [未输入性别]";
        }
        else {
            if(!sexName.equals("男") && !sexName.equals("女")) {
                singleFlag = false;
                flag = false;
                error += "  [性别(" + sexName+")格式错误]";
            }
        }

        // 校验学号
        String stuNum = studentVo.getStuNum();
        if(stuNum == null || stuNum.equals("")) {
            singleFlag = false;
            flag = false;
            error += "  [未输入学号]";
        }
        else {
            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Student::getStuNum,stuNum);
            Student student = studentService.getOne(wrapper);
            if(student != null) {
                singleFlag = false;
                flag = false;
                error += " [学号已存在]";
            }
        }

        // 校验学院

        SchoolCollege schoolCollege = new SchoolCollege();
        SchoolMajor schoolMajor = new SchoolMajor();
        SchoolClass schoolClass = new SchoolClass();
        String collegeName = studentVo.getCollegeName();

        if(collegeName == null || collegeName.equals("")) {
            singleFlag = false;
            flag = false;
            error += "  [未输入学院]";
        }
        else {
            schoolCollege = studentExcel.getByCollegeName(collegeName);
            if(schoolCollege == null) {
                singleFlag = false;
                flag = false;
                error += " [学院不存在]";
            }
            else {
                // 校验专业
                String majorName = studentVo.getMajorName();
                if(majorName == null || majorName.equals("")) {
                    singleFlag = false;
                    flag = false;
                    error += "  [未输入专业]";
                }
                else {
                    schoolMajor = studentExcel.getByMajorName(majorName);
                    if(schoolMajor == null) {
                        singleFlag = false;
                        flag = false;
                        error += " [专业不存在]";
                    }
                    else {
                        if(schoolMajor.getCollegeId() != schoolCollege.getCollegeId()) {
                            singleFlag = false;
                            flag = false;
                            error += " [学院专业不匹配]";
                        }
                        else {
                            // 校验班级
                            String className = studentVo.getClassName();
                            if(className == null || className.equals("")) {
                                singleFlag = false;
                                flag = false;
                                error += "  [未输入班级]";
                            }
                            else {
                                schoolClass = studentExcel.getByClassName(className);
                                if(schoolClass == null) {
                                    singleFlag = false;
                                    flag = false;
                                    error += " [班级不存在]";
                                }
                                else {
                                    if(schoolClass.getMajorId() != schoolMajor.getMajorId()) {
                                        singleFlag = false;
                                        flag = false;
                                        error += " [专业班级不匹配]";
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        // 检验电话
        String tel = studentVo.getTel();
        if(tel == null || tel.equals("")) {
            flag = false;
            singleFlag = false;
            error += "  [未输入电话]";
        }


        // 如果校验不成功，将错误信息添加
        if(!singleFlag) {
            info.add(error);
        }

        // 如果校验成功增加学生至列表
        else {
            Student student = new Student();
            student.setName(name);
            int sex = sexName.equals("男")?0:1;
            student.setSex(sex);
            student.setClassNumber(schoolClass.getClassId());
            student.setStuNum(stuNum);
            student.setMajor(schoolMajor.getMajorId());
            student.setCollege(schoolCollege.getCollegeId());

            student.setTel(tel);
            list.add(student);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 入库
        if(flag) {
            for (Student student:list) {
                studentService.save(student);
            }
        }
    }
}