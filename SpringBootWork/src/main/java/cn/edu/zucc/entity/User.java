package cn.edu.zucc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private Status status;

    @Column(name = "Password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    @Column(name = "Age", nullable = false)
    private int age;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Address", nullable = false)
    private String address;

    // 枚举类型定义
    public enum Status {
        SA, A, T, S
    }

    public enum Gender {
        男, 女, 其它
    }

    // toString method
    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("password='" + password + "'")
                .add("gender=" + gender)
                .add("age=" + age)
                .add("phone='" + phone + "'")
                .add("email='" + email + "'")
                .add("address='" + address + "'")
                .toString();
    }
}