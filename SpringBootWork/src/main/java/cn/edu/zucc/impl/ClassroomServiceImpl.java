package cn.edu.zucc.impl;

import cn.edu.zucc.entity.Classroom;
import cn.edu.zucc.repository.ClassroomRepository;
import cn.edu.zucc.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }
}
