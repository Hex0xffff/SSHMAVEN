package com.sshProject.controller;

import com.sshProject.entity.User;
import com.sshProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pwd", method = RequestMethod.POST)
    public void updatePwd(int userId, String oldVal, String newVal, HttpServletResponse response) {

        String result="{\"result\":\"error\"}";
        if(userService.updatePassword(userId, oldVal, newVal))
        {
            result="{\"result\":\"success\"}";
        }

        response.setContentType("application/json");

        try
        {
            PrintWriter out = response.getWriter();
            out.write(result);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request) {
        return "";
    }

/*
    @RequestMapping("checkAuthorization")
    public String checkAuthorization(String Username, HttpServletRequest request) {
        return "";
    }
*/
}
