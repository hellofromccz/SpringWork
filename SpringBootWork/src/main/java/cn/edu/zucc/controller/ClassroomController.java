package cn.edu.zucc.controller;

import cn.edu.zucc.entity.Classroom;
import cn.edu.zucc.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/name/{name}")
    public List<Classroom> findByName(@PathVariable String name) {
        return classroomService.findByName(name);
    }

    @GetMapping
    public List<Classroom> findAll() {
        return classroomService.findAll();
    }

    @PostMapping("/addClassroom")
    public Classroom addClassroom(@RequestBody Classroom classroom) {
        return classroomService.addClassroom(classroom);
    }
    @DeleteMapping("/{id}")
    public Classroom deleteById(@PathVariable Long id) {
        return classroomService.deleteById(id);
    }

    @PutMapping
    public Classroom updateClassroom(@RequestBody Classroom classroom) {
        return classroomService.updateClassroom(classroom);
    }
}
