package cn.edu.zucc.service;

import cn.edu.zucc.entity.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {
    List<Classroom> findByName(String name);

    List<Classroom> findAll();

    Classroom addClassroom(Classroom classroom);

    Classroom deleteById(Long id);

    Classroom updateClassroom(Classroom classroom);

    Classroom getClassroomById(Long classroomId);
}
