package cn.edu.zucc.controller;

import cn.edu.zucc.dto.GradeReq1;
import cn.edu.zucc.dto.GradeReq2;
import cn.edu.zucc.dto.GradeRequest;
import cn.edu.zucc.entity.Course;
import cn.edu.zucc.entity.Grade;
import cn.edu.zucc.entity.Student;
import cn.edu.zucc.service.CourseService;
import cn.edu.zucc.service.GradeService;
import cn.edu.zucc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/grade")
public class GradeController {
    private final GradeService gradeService;
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public GradeController(GradeService gradeService, CourseService courseService, StudentService studentService) {
        this.gradeService = gradeService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @PostMapping("/get_all_grades")
    public ResponseEntity<?> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @PostMapping("/get_grade_by_id")
    public ResponseEntity<?> getGradeById(@RequestBody GradeReq1 gradeReq1) {
        Long id = gradeReq1.getGradeid();
        Grade grade = gradeService.getGradeById(id);
        List<Grade> grades = new ArrayList<>();
        grades.add(grade);
        if(grade == null){
            return ResponseEntity.status(500).body("Internal Server Error");
        }else{
            return ResponseEntity.ok(grades);
        }
    }

    @PostMapping("/add_grade")
    public ResponseEntity<?> addGrade(@RequestBody GradeRequest gradeRequest) {
        System.out.println(gradeRequest);

        Long courseid = gradeRequest.getCourseid();
        Long studentid = gradeRequest.getStudentid();

        Course course = courseService.getCourseById(courseid);
        Student student = studentService.getStudentById(studentid);

        if(course == null || student == null){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
        Grade grade = new Grade();
        grade.setGrade(gradeRequest.getScore());
        grade.setCourse(course);
        grade.setStudent(student);

        System.out.println(grade);
        Grade newGrade = gradeService.createGrade(grade);
        if(newGrade == null){
            return ResponseEntity.status(500).body("Internal Server Error");
        }else{
            return ResponseEntity.ok(newGrade);
        }
    }

    @PostMapping("/delete_grade")
    public ResponseEntity<?> deleteGrade(@RequestBody GradeReq1 gradeReq1) {
        Long gradeId = gradeReq1.getGradeid();
        System.out.println(gradeId);
        gradeService.deleteGrade(gradeId);
        return ResponseEntity.ok("Delete grade successfully");
    }

    @PostMapping("/update_grade")
    public ResponseEntity<?> updateGrade(@RequestBody GradeReq2 gradeReq2) {
        System.out.println(gradeReq2);

        Long courseid = gradeReq2.getCourseid();
        Long studentid = gradeReq2.getStudentid();

        Course course = courseService.getCourseById(courseid);
        Student student = studentService.getStudentById(studentid);

        Grade grade = new Grade();
        grade.setGradeid(gradeReq2.getGradeid());
        grade.setGrade(gradeReq2.getScore());
        grade.setCourse(course);
        grade.setStudent(student);

        System.out.println(grade);
        Grade newGrade = gradeService.updateGrade(grade);
        if(newGrade == null){
            return ResponseEntity.status(500).body("Internal Server Error");
        }else{
            return ResponseEntity.ok(newGrade);
        }
    }

    @PostMapping("/get_details")
    public ResponseEntity<?> getDetails(@RequestBody GradeReq1 gradeReq1) {
        Long id = gradeReq1.getGradeid();
        System.out.println(id);
        List<Grade> gradeList = gradeService.getDetailsById(id);
        if (gradeList == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(gradeList);
        }
    }
}
