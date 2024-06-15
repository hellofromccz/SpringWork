package cn.edu.zucc.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "opinion")
public class Opinion {
    @Id
    private Long opinionId;

    @Column(name = "opinioncontent")
    private String opinionContent;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "UserID")
    private User user;

    @Column(name = "isreply")
    private String isReply;
}
