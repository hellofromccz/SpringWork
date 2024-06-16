package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM student WHERE UserID = ?1", nativeQuery = true)
    Student findByUserId(Long userId);
}
