package cn.edu.zucc.controller;

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
import java.util.Optional;
import cn.edu.zucc.types.Privilege;
import cn.edu.zucc.dto.LoginResponse;
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
    // 根据id获取个人信息
    // @PostMapping("/get_info")
    // public ResponseEntity<?> login(@RequestBody LoginRequest loginUser) {
    // // 假设UserService的login方法会验证用户并返回一个包含code和privilege的对象
    // LoginResponse loginResponse = userService.login(loginUser);
    // if (loginResponse.getCode() == 1) {
    // // 登录成功，根据privilege重定向
    // return ResponseEntity.ok(loginResponse);
    // } else if (loginResponse.getCode() == -2) {
    // // 密码错误或其他登录失败情况
    // return ResponseEntity.badRequest().body(loginResponse);
    // } else {
    // // 其他未知错误
    // return ResponseEntity.status(500).body("Internal Server Error");
    // }
    // }

}
