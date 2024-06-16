package cn.edu.zucc.service;

import cn.edu.zucc.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService{
    Student createStudent(Student student);

    Student getStudentById(Long studentId);

    Student updateStudent(Student student);

    void deleteStudent(Long studentId);

    Student getStudentByUserID(Long userId);
}
