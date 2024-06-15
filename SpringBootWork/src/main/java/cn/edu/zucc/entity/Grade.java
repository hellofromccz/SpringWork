package cn.edu.zucc.entity;

import javax.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    private Long gradeId;

    @ManyToOne
    @JoinColumn(name = "studentid", referencedColumnName = "Studentid")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "Courseid")
    private Course course;

    @Column(name = "grade")
    private String grade;
}
