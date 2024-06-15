package cn.edu.zucc.service;

import cn.edu.zucc.entity.Classroom;

import java.util.List;

public interface ClassroomService {
    List<Classroom> findByName(String name);
    List<Classroom> findAll();
    Classroom addClassroom(Classroom classroom);
    Classroom deleteById(int id);
    Classroom updateClassroom(Classroom classroom);
}
