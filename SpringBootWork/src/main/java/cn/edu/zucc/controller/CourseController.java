package cn.edu.zucc.controller;

import cn.edu.zucc.entity.Course;
import cn.edu.zucc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add_course")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        System.out.println(course);
        Course newCourse = courseService.createCourse(course);
        if (newCourse == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(newCourse);
        }
    }

    @PostMapping("/get_all_courses")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
