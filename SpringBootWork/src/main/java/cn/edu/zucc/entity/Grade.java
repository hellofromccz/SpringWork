package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @Column(name = "gradeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeid;

    @ManyToOne
    @JoinColumn(name = "studentid", referencedColumnName = "Studentid")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseid", referencedColumnName = "Courseid")
    private Course course;

    @Column(name = "grade")
    private String grade;
}
