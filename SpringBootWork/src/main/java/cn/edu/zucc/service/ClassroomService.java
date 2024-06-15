package cn.edu.zucc.service;

import cn.edu.zucc.entity.Classroom;
import org.springframework.stereotype.Service;

@Service
public interface ClassroomService {
    Classroom createClassroom(Classroom classroom);
}
