package cn.edu.zucc.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_select_course")
public class StudentSelectCourse {
    @Id
    private int Scourseid;
    @ManyToOne
    @JoinColumn(name = "Courseid")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "Studentid")
    private Student student;
}
