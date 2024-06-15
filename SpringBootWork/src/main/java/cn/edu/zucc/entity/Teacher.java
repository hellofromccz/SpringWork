package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "teacher")
public class Teacher{
    @Id
    private Long TeacherID;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

//    @ManyToMany
//    @JoinTable(
//            name = "teacher_select_course",
//            joinColumns = @JoinColumn(name = "Teacherid"),
//            inverseJoinColumns = @JoinColumn(name = "Courseid")
//    )
//    private Set<Course> coursesTaught = new HashSet<>();
}
