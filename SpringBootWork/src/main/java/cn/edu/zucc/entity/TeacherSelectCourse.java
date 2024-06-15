package cn.edu.zucc.entity;

import javax.persistence.*;

@Entity
@Table(name = "teacher_select_course")
public class TeacherSelectCourse {
    @Id
    private int Tcourseid;
    @ManyToOne
    @JoinColumn(name = "Courseid")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "Teacherid")
    private Teacher teacher;
}
