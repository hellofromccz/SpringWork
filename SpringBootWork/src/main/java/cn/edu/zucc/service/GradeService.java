package cn.edu.zucc.service;

import cn.edu.zucc.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {
    Grade createGrade(Grade grade);

    List<Grade> getAllGrades();

    Grade getGradeById(Long gradeId);
}
