package cn.edu.zucc.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyID;

    @Column
    private String replyContent;

    @ManyToOne
    @JoinColumn(name = "opinionID", nullable = false)
    private Opinion opinion;

    @ManyToOne
    @JoinColumn(name = "whoReply")
    private User user;
}
