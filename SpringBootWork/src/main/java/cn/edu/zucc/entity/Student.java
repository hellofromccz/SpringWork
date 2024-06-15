package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "student")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Studentid;

    @OneToOne
    @JoinColumn(name = "Userid")
    private User User;

    @ManyToMany
    // 映射和属性定义...
    private Set<Course> courses;
}
