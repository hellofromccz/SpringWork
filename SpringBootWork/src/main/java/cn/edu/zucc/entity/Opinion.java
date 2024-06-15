package cn.edu.zucc.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "opinion")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opinionID;

    @Column
    private String opinionContent;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(mappedBy = "opinion")
    private Set<Reply> replies;
}
