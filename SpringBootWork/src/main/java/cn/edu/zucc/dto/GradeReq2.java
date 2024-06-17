package cn.edu.zucc.dto;

import lombok.Data;

@Data
public class GradeReq2 {
    private Long gradeid;
    private Long courseid;
    private Long studentid;
    private String score;
}
