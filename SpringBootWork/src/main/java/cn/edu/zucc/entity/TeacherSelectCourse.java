package cn.edu.zucc.entity;

import javax.persistence.*;

@Entity
@Table(name = "teacher_select_course")
public class TeacherSelectCourse {
    @Id
    @Column(name = "Tcourseid", columnDefinition = "int(10) unsigned zerofill")
    private Long tcourseid;

    @ManyToOne
    @JoinColumn(name = "Courseid", referencedColumnName = "Courseid")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "Teacherid", referencedColumnName = "TeacherID")
    private Teacher teacher;
}
