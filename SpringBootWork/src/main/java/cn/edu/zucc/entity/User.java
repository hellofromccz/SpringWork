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
    @Column(name = "UserID", columnDefinition = "int(9) UNSIGNED ZEROFILL")
    private Long userId;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false, length = 1)
    private Status status;

    @Column(name = "Password", nullable = true, length = 255, columnDefinition = "varchar(255) default '00000000'")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false, length = 1)
    private Gender gender;

    @Column(name = "Age", nullable = true)
    private Integer age;

    @Column(name = "Phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "Email", nullable = false, length = 255)
    private String email;

    @Column(name = "Address", nullable = false, length = 255)
    private String address;

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