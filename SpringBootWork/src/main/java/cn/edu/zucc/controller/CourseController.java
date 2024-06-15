package cn.edu.zucc.controller;

import cn.edu.zucc.dto.CourseDetailRequest;
import cn.edu.zucc.dto.CourseNameRequest;
import cn.edu.zucc.entity.Course;
import cn.edu.zucc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // TODO:添加课程
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

    // TODO:单个页面进去后可以删除课程
    @PostMapping("/delete_course")
    public ResponseEntity<?> deleteCourse(@RequestBody Long courseId) {
        System.out.println(courseId);
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Delete course successfully");
    }

    @PostMapping("/get_all_courses")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/get_details")
    public ResponseEntity<?> getDetails(@RequestBody CourseDetailRequest courseDetailRequest) {
        Long id = courseDetailRequest.getCourseId();
        System.out.println(id);
        List<Course> courseList = courseService.getDetailsById(id);
        if (courseList == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(courseList);
        }
    }

    //TODO:修改课程信息
    @PostMapping("/update_course")
    public ResponseEntity<?> updateCourse(@RequestBody Course course) {
        System.out.println(course);
        Course newCourse = courseService.updateCourse(course);
        if (newCourse == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(newCourse);
        }
    }

    @PostMapping("/get_info_by_name")
    public ResponseEntity<?> getInfoByName(@RequestBody CourseNameRequest courseNameRequest) {
        String name = courseNameRequest.getName();
        System.out.println(name);
        List<Course> courseList = courseService.getInfoByName(name);
        if (courseList == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(courseList);
        }
    }
}
