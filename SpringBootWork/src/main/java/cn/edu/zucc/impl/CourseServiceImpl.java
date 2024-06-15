package cn.edu.zucc.impl;

import cn.edu.zucc.entity.Course;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.repository.CourseRepository;
import cn.edu.zucc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Course> getDetailsById(Long courseId) {
        List<Course> courseList = courseRepository.getDetailsById(courseId);
        if (courseList == null) {
            return null;
        } else {
            return courseList;
        }
    }
}
