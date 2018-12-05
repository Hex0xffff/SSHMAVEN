package com.sshProject.service.impl;

import com.sshProject.dao.UserDao;
import com.sshProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public boolean loginUsername(String username, String password) {
        return userDao.loginUsername(username, password);
    }

    public boolean loginEmail(String email, String password) {
        return userDao.loginEmail(email, password);
    }

    public boolean updatePassword(int userId, String oldVal, String newVal) {
        return userDao.updatePassword(userId, oldVal, newVal);
    }

    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

//    public boolean checkAuthorization(String username) {
//        return userDao.checkAuthorization(username);
//    }
}
