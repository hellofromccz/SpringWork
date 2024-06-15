package cn.edu.zucc.repository;

import cn.edu.zucc.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT * FROM course WHERE Coursename = ?1", nativeQuery = true)
    Course findByName(String name);
}
