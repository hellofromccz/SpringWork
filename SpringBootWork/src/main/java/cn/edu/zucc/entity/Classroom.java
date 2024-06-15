package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassroomID")
    private int classroomID;

    @Column(name = "Classroomname")
    private String classroomName;

    @Column(name = "Campusinformation")
    private String campusInformation;

    @Column(name = "Classroomcapacity")
    private int classroomCapacity;

    @Column(name = "Special_Conditions_of_Classrooms")
    @Enumerated(EnumType.STRING)
    private SpecialConditions specialConditions;

    public enum SpecialConditions {
        无设备, 有多媒体设备, 数字化教室
    }
}