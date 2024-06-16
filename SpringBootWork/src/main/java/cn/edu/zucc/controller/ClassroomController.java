package cn.edu.zucc.controller;

import cn.edu.zucc.dto.ClassRoomRequest;
import cn.edu.zucc.dto.UserInfoRequest;
import cn.edu.zucc.entity.Classroom;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/name/{name}")
    public List<Classroom> findByName(@PathVariable String name) {
        return classroomService.findByName(name);
    }

    // 根据名字获取教室信息
    @PostMapping("/get_info_by_name")
    public ResponseEntity<?> getClassRoomByName(@RequestBody ClassRoomRequest classRoomRequest) {
        String name = classRoomRequest.getName();
        System.out.println(name);
        List<Classroom> ClassList = classroomService.findByName(name);
        if (ClassList != null) {
            return ResponseEntity.ok(ClassList);
        } else {
            return ResponseEntity.status(500).body("Internal Server Error");
        }

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
