package cn.edu.zucc.dto;

import cn.edu.zucc.entity.Course;
import lombok.Data;

@Data
public class CourseModifyRequest {
    private String name;
    private double credit;
    private String courseTime;
    private Long classroomId;
    private Long teacherId;
    private int capacity;
    private boolean compulsory;
}
