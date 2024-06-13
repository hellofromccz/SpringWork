package cn.edu.zucc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.edu.zucc.dto.LoginRequest;
import cn.edu.zucc.dto.LoginResponse;
import cn.edu.zucc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 可以在此处添加自定义查询方法
    /* 根据ID获取密码 */
    @Query(value = "SELECT Password FROM user WHERE UserID = ?1", nativeQuery = true)
    String getPasswordById(Long id);

    /* 根据ID获取用户 */
    @Query(value = "SELECT * FROM user WHERE UserID = ?1", nativeQuery = true)
    User getUserById(Long id);

    /* 根据账号密码验证登录 */
    @Query(value = "SELECT * FROM user WHERE UserID = :userId AND Password = :password", nativeQuery = true)
    User findByUserIdAndPassword(@Param("userId") Long userId, @Param("password") String password);

    /* 根据姓名获取用户 */
    @Query(value = "SELECT * FROM user WHERE Name = ?1", nativeQuery = true)
    List<User> getUserByName(String getName);

}
