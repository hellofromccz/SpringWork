package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "teacher")
public class Teacher{
    @Id
    private int TeacherID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;
}
