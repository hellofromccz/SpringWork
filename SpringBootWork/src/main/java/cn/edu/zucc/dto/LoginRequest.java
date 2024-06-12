package cn.edu.zucc.dto;

public class LoginRequest {
    private Long id = 0L;
    private String password;

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
