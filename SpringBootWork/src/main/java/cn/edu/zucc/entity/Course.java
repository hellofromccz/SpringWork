package cn.edu.zucc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "CourseID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;

    @Column(name = "CourseName")
    private String courseName;

    @Column(name = "Credit")
    private double credit;

    @Lob
    @Column(name = "CourseTime")
    private String courseTime; // 这里假设CourseTime是字符串格式，实际可能需要转换为相应的对象

    @Column(name = "ClassroomID")
    private int classroomID;

    @Column(name = "CourseTeacherID")
    private int courseTeacherID;

    @Column(name = "CourseCapacity")
    private int courseCapacity;

    @Column(name = "Number_of_Student_taking_Course")
    private int numberOfStudentTakingCourse;

    @Column(name = "Compulsory_or_not")
    private int compulsoryOrNot;

    @Column(name = "AssessmentMethod")
    private String assessmentMethod;

    // 定义关联
    @ManyToOne
    @JoinColumn(name = "ClassroomID", referencedColumnName = "ClassroomID", insertable = false, updatable = false)
    @JsonIgnore
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "CourseTeacherID", referencedColumnName = "UserID", insertable = false, updatable = false)
    @JsonIgnore
    private User courseTeacher;

    // 省略getter和setter方法
}