package cn.edu.zucc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.edu.zucc.entity.Classroom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByClassroomName(String name);
}
