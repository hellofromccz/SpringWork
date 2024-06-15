package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends User {
    @Column(name = "AvailableTime", columnDefinition = "json")
    private String availableTime;

    @ManyToMany
    @JoinTable(
            name = "teacher_teach_course",
            joinColumns = @JoinColumn(name = "TeacherID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    private Set<Course> coursesTaught;
}
