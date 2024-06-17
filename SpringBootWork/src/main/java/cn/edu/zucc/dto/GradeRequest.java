package cn.edu.zucc.dto;

import lombok.Data;

@Data
public class GradeRequest {
    private Long courseid;
    private Long studentid;
    private String score;
}
