package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "classroom")
public class Classroom {
    @Id
    @Column(name = "ClassroomID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomID;

    @Column(name = "ClassroomName")
    private String classroomName;

    @Column(name = "CampusInformation")
    private String campusInformation;

    @Column(name = "ClassroomCapacity")
    private int classroomCapacity;

    @Column(name = "Special_Conditions_of_Classrooms")
    @Enumerated(EnumType.STRING)
    private SpecialConditions specialConditions;

    public enum SpecialConditions {
        无设备, 有投影仪, 有电脑, 有投影仪和电脑
    }
    // 省略getter和setter方法
}