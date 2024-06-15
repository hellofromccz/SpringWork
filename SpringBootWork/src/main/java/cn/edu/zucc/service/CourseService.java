package cn.edu.zucc.service;

import cn.edu.zucc.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {
    Optional<Course> getCourseById(Long courseId);
    List<Course> getAllCourses();
    Course createCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long courseId);

}