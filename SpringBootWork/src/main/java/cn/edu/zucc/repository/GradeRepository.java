package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query(value = "select * from grade where gradeid = ?1", nativeQuery = true)
    List<Grade> getDetailsById(Long gradeId);
}
