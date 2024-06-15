package cn.edu.zucc.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_select_course")
public class StudentSelectCourse {
    @Id
    private Long Scourseid;
    @ManyToOne
    @JoinColumn(name = "Courseid", referencedColumnName = "Courseid")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "Studentid", referencedColumnName = "Studentid")
    private Student student;
}
