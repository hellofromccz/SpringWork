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
    @Column(name = "Courseid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @Column(name = "Coursename")
    private String courseName;

    @Column(name = "Credit")
    private double credit;

    @ManyToOne
    @JoinColumn(name = "Classroomid", referencedColumnName = "ClassroomID")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "Courseteacherid", referencedColumnName = "TeacherID")
    private Teacher courseTeacher;

    @Column(name = "Coursecapacity")
    private int courseCapacity;

    @Column(name = "Number_of_Student_taking_Course")
    private int numberOfStudents;

    @Column(name = "Compulsory_or_not")
    private boolean compulsory;

    @Column(name = "Coursetime")
    private String courseTime;

}