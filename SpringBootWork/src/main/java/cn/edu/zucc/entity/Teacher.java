package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "teacher")
public class Teacher{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TeacherID;

    @OneToOne
    @JoinColumn(name = "UserID")
    private User User;

    @ManyToMany
    // 映射和属性定义...
    private Set<Course> courses;
}
