package cn.edu.zucc.impl;

import cn.edu.zucc.repository.GradeRepository;
import cn.edu.zucc.service.GradeService;
import cn.edu.zucc.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Grade getGradeById(Long gradeId) {
        return gradeRepository.findById(gradeId).orElse(null);
    }
}
