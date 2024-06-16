package cn.edu.zucc.controller;

import cn.edu.zucc.dto.CourseDetailRequest;
import cn.edu.zucc.dto.CourseModifyRequest;
import cn.edu.zucc.dto.CourseNameRequest;
import cn.edu.zucc.dto.CourseReq1;
import cn.edu.zucc.entity.*;
import cn.edu.zucc.service.ClassroomService;
import cn.edu.zucc.service.CourseService;
import cn.edu.zucc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final ClassroomService classroomService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(CourseService courseService, ClassroomService classroomService, TeacherService teacherService) {
        this.courseService = courseService;
        this.classroomService = classroomService;
        this.teacherService = teacherService;
    }

    @PostMapping("/add_course")
    public ResponseEntity<?> addCourse(@RequestBody CourseModifyRequest courseModifyRequest) {
        System.out.println(courseModifyRequest);
        Teacher teacher = teacherService.getTeacherById(courseModifyRequest.getTeacherId());
        Classroom classroom = classroomService.getClassroomById(courseModifyRequest.getClassroomId());

        Course course = new Course();
        course.setCourseName(courseModifyRequest.getName());
        course.setCredit(courseModifyRequest.getCredit());
        course.setClassroom(classroom);
        course.setCourseTeacher(teacher);
        course.setCourseCapacity(courseModifyRequest.getCapacity());
        course.setCompulsory(courseModifyRequest.isCompulsory());
        course.setCourseTime(courseModifyRequest.getCourseTime());

        System.out.println(course);
        Course newCourse = courseService.createCourse(course);
        if (newCourse == null) {
            return ResponseEntity.status(500).body("Internal Server Error");
        } else {
            return ResponseEntity.ok(newCourse);
        }
    }

    @PostMapping("/delete_course")
    public ResponseEntity<?> deleteCourse(@RequestBody CourseDetailRequest courseDetailRequest) {
        Long courseId = courseDetailRequest.getCourseId();
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

    @PostMapping("/update_course")
    public ResponseEntity<?> updateCourse(@RequestBody CourseReq1 courseModifyRequest) {
        System.out.println(courseModifyRequest);
        Teacher teacher = teacherService.getTeacherById(courseModifyRequest.getTeacherId());
        Classroom classroom = classroomService.getClassroomById(courseModifyRequest.getClassroomId());

        Course course = new Course();
        course.setCourseId(courseModifyRequest.getCourseId());
        course.setCourseName(courseModifyRequest.getName());
        course.setCredit(courseModifyRequest.getCredit());
        course.setClassroom(classroom);
        course.setCourseTeacher(teacher);
        course.setCourseCapacity(courseModifyRequest.getCapacity());
        course.setCompulsory(courseModifyRequest.isCompulsory());
        course.setCourseTime(courseModifyRequest.getCourseTime());

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
