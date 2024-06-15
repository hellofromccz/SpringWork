package cn.edu.zucc.controller;

import cn.edu.zucc.entity.Classroom;
import cn.edu.zucc.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("/add_classroom")
    public ResponseEntity<?> addClassroom(@RequestBody Classroom classroom) {
        System.out.println(classroom);
        Classroom newClassroom = classroomService.createClassroom(classroom);
        if (newClassroom == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(newClassroom);
        }
    }
}
