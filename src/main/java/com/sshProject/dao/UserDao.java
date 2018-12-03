package com.sshProject.dao;
import com.sshProject.entity.User;

public interface UserDao {
    boolean loginUsername(String username,String password);

    boolean loginEmail(String email,String password);

    boolean updatePassword(int userId,String oldVal,String newVal);

    boolean deleteUser(int userId);

    boolean checkAuthorization(String username);
}
