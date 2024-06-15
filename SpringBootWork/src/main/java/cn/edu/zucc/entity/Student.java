package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "student")
public class Student extends User{
    @Column(name = "Course_selection_Tendency")
    private String courseSelectionTendency;

    @Column(name = "Training_plan", columnDefinition = "longtext")
    private String trainingPlan;

    @ManyToMany(mappedBy = "students")
    private Collection<Course> selectedCourses;
}
