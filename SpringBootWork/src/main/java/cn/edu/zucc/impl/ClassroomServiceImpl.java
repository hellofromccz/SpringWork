package cn.edu.zucc.impl;


import cn.edu.zucc.entity.Classroom;
import cn.edu.zucc.repository.ClassroomRepository;
import cn.edu.zucc.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> findByName(String name) {
        return classroomRepository.findByClassroomName(name);
    }

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom addClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom deleteById(Long id) {
        Classroom classroom = classroomRepository.findById(id).orElse(null);
        if (classroom != null) {
            classroomRepository.deleteById(id);
        }
        return classroom;
    }

    @Override
    public Classroom updateClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomById(Long classroomId) {
        return classroomRepository.findById(classroomId).orElse(null);
    }
}
