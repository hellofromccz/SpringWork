package cn.edu.zucc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassroomID", columnDefinition = "int(9) UNSIGNED ZEROFILL")
    private Long classroomID;

    @JsonProperty("Classroomname")
    @Column(name = "Classroomname")
    private String classroomName;

    @JsonProperty("Campusinformation")
    @Column(name = "Campusinformation")
    private String campusInformation;

    @JsonProperty("Classroomcapacity")
    @Column(name = "Classroomcapacity")
    private int classroomCapacity;

    @JsonProperty("Special_Conditions_of_Classrooms")
    @Column(name = "Special_Conditions_of_Classrooms")
    @Enumerated(EnumType.STRING)
    private SpecialConditions specialConditions;

    public enum SpecialConditions {
        无设备, 有多媒体设备, 数字化教室
    }

}