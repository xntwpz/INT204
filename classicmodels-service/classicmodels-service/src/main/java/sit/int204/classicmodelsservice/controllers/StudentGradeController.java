package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.models.Student;
import sit.int204.classicmodelsservice.services.StudentGradeService;

@RestController
@RequestMapping("/api/student-grades")
public class StudentGradeController {

    @Autowired // ขอobjectของservice โดยไม่ต้องสร้างใหม่
    private StudentGradeService service;// read  + requestBody from client
    @GetMapping("")
    public Student getGrade(@RequestBody Student student){
        return service.getGrade(student);
    }
}
