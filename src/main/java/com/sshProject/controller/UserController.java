package com.sshProject.controller;

import com.sshProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginUsername")
    public boolean loginUsername(String username, String password, HttpServletRequest request) {
        if(userService.loginUsername(username, password)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/loginEmail")
    public boolean loginEmail(String email, String password, HttpServletRequest request) {
        if(userService.loginEmail(email, password)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(int userId, String oldVal, String newVal, HttpServletRequest request) {
        if(userService.updatePassword(userId, oldVal, newVal)) {

            return  "redirect:/";
        } else {
            return "/error";
        }
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(int userId, HttpServletRequest request) {
        return "";
    }

    @RequestMapping("checkAuthorization")
    public String checkAuthorization(String Username, HttpServletRequest request) {
        return "";
    }
}
