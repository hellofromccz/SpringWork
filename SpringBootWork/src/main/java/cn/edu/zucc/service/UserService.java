package cn.edu.zucc.service;

import cn.edu.zucc.dto.GetInfoRequest;
//import cn.edu.zucc.dto.GetNameRequest;

import org.springframework.stereotype.Service;

import java.util.List;

import cn.edu.zucc.dto.LoginResponse;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.dto.LoginRequest;

@Service
public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);

    LoginResponse login(LoginRequest loginUser);

    LoginResponse getinfo(GetInfoRequest getInfoRequest);

    List<User> getInfoByName(String name);

    List<User> getInfoById(Long id);

}