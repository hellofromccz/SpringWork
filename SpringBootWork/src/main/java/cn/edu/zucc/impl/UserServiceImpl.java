package cn.edu.zucc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zucc.dto.LoginRequest;
import cn.edu.zucc.dto.LoginResponse;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.repository.UserRepository;
import cn.edu.zucc.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public LoginResponse login(LoginRequest loginUser) {
        // TODO Auto-generated method stub
        User user = userRepository.findByUserIdAndPassword(loginUser.getId(), loginUser.getPassword());
        if (user != null) {
            // 模拟登录成功的处理逻辑
            return new LoginResponse(1, user); // 假设登录成功，权限为"USER"
        } else {
            return new LoginResponse(-2, null); // 登录失败
        }
    }
}
