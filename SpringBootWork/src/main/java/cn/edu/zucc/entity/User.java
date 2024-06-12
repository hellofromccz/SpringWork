package cn.edu.zucc.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
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

    @Column(name = "Password", nullable = false, length = 255, columnDefinition = "varchar(255) default '00000000'")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false, length = 1)
    private Gender gender;

    @Column(name = "Age", nullable = false)
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

    // Getter methods
    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Setter methods
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
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