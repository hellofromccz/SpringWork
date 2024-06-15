package cn.edu.zucc.service;

import cn.edu.zucc.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    Teacher createTeacher(Teacher teacher);

    Teacher getTeacherById(Long teacherId);

    Teacher updateTeacher(Teacher teacher);

    void deleteTeacher(Long teacherId);
}
