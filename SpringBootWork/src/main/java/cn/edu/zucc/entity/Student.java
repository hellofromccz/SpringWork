package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "student")
public class Student{
    @Id
    private int Studentid;
    @ManyToOne
    @JoinColumn(name = "Userid")
    private User User;
}
