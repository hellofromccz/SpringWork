package cn.edu.zucc.controller;

import cn.edu.zucc.dto.GetInfoRequest;
//import cn.edu.zucc.dto.GetNameRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.service.UserService;

import java.util.List;
import java.util.Optional;
import cn.edu.zucc.types.Privilege;
import cn.edu.zucc.dto.LoginResponse;
import cn.edu.zucc.dto.UserInfoRequest;
import cn.edu.zucc.dto.LoginRequest;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/usr")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(userId));
        return optionalUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 登录
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginUser) {
        // 假设UserService的login方法会验证用户并返回一个包含code和privilege的对象
        LoginResponse loginResponse = userService.login(loginUser);
        if (loginResponse.getCode() == 1) {
            // 登录成功，根据privilege重定向
            return ResponseEntity.ok(loginResponse);
        } else if (loginResponse.getCode() == -2) {
            // 密码错误或其他登录失败情况
            return ResponseEntity.badRequest().body(loginResponse);
        } else {
            // 其他未知错误
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    // 根据名字获取个人信息
    @PostMapping("/get_info_by_name")
    public ResponseEntity<?> getInfoByName(@RequestBody UserInfoRequest userInfoRequest) {
        String name = userInfoRequest.getName();
        System.out.println(name);
        List<User> UserList = userService.getInfoByName(name);
        if (UserList != null) {
            return ResponseEntity.ok(UserList);
        } else {
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }

    // 添加用户
    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        System.out.println(user);
        User newUser = userService.createUser(user);
        if (newUser != null) {
            return ResponseEntity.ok(newUser);
        } else {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    // 根据id获取个人信息
    @PostMapping("/get_info")
    public ResponseEntity<?> getInfo(@RequestBody GetInfoRequest getInfoRequest) {
        Long id = getInfoRequest.getUserId();
        System.out.println(id);
        List<User> UserList = userService.getInfoById(id);
        if (UserList != null) {
            return ResponseEntity.ok(UserList);
        } else {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    // 获取全部用户信息
    @PostMapping("/get_all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
