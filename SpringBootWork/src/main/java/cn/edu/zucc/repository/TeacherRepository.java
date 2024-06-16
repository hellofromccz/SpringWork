package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "SELECT * FROM teacher WHERE UserID = ?1", nativeQuery = true)
    Teacher findByUserId(Long userId);
}
