package cuit9622.dms.student.service.excel;

import cuit9622.dms.common.entity.SchoolClass;
import cuit9622.dms.common.entity.SchoolCollege;
import cuit9622.dms.common.entity.SchoolMajor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="college")
public interface StudentExcel {
    @GetMapping("/class/className")
    SchoolClass getByClassName(@RequestParam String className);

    @GetMapping("/college/collegeName")
    SchoolCollege getByCollegeName(@RequestParam String collegeName);

    @GetMapping("/major/majorName")
    SchoolMajor getByMajorName(@RequestParam String majorName);
}
