package cn.edu.zucc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Courseid")
    private int courseid;

    @Column(name = "Coursename")
    private String coursename;

    @Column(name = "Credit")
    private double credit;

//    @Column(name = "Classroomid")
//    private int classroomid;

    @Column(name = "Courseteacherid")
    private int courseteacherid;

    @Column(name = "Coursecapacity")
    private int coursecapacity;

    @Column(name = "Number_of_Student_taking_Course")
    private int numberOfStudentTakingCourse;

    @Column(name = "Compulsory_or_not")
    private int compulsoryOrNot;

    @Column(name = "Coursetime")
    private String coursetime;

    @ManyToOne
    @JoinColumn(name = "Classroomid", referencedColumnName = "ClassroomID", insertable = false, updatable = false)
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
            name = "student_select_course",
            joinColumns = @JoinColumn(name = "CourseID"),
            inverseJoinColumns = @JoinColumn(name = "StudentID")
    )
    private Collection<Student> students;

    @ManyToMany(mappedBy = "coursesTaught")
    private Set<Teacher> teachers;
}