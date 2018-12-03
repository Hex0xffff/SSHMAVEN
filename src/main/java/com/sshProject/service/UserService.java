package com.sshProject.service;

import com.sshProject.entity.User;

public interface UserService {
    boolean loginUsername(String username,String password);

    boolean loginEmail(String email,String password);

    boolean updatePassword(int userId,String oldVal,String newVal);

    boolean deleteUser(int userId);

    boolean checkAuthorization(String username);
}
