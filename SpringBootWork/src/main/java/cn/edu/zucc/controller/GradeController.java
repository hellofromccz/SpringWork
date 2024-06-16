package cn.edu.zucc.controller;

import cn.edu.zucc.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/grade")
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/get_all_grades")
    public ResponseEntity<?> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @PostMapping("/get_grade_by_id")
    public ResponseEntity<?> getGradeById(@RequestBody Long gradeId) {
        return ResponseEntity.ok(gradeService.getGradeById(gradeId));
    }
}
