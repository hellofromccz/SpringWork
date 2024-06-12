package cn.edu.zucc.dto;

import cn.edu.zucc.entity.*;

//import com.example.demo2.types.Privilege;

public class LoginResponse {
    private int code = 0;
    private User usr;
    // private Privilege privilege = Privilege.NOT_SET;

    // constructors
    public LoginResponse() {
    }

    public LoginResponse(int code, User usr) {
        this.code = code;
        this.usr = usr;
    }

    // for debug
    public String toString() {
        return "code:" + code + "usr" + usr;
    }

    // get
    public int getCode() {
        return code;
    }

    // get
    public User getUsr() {
        return usr;
    }
}
