package cn.edu.zucc.dto;

import lombok.Data;

@Data
public class CourseReq1 {
    private Long courseId;
    private String name;
    private double credit;
    private String courseTime;
    private Long classroomId;
    private Long teacherId;
    private int capacity;
    private boolean compulsory;
}
