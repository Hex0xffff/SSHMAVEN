package com.sshProject.controller;

import com.sshProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public void loginByUsername(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result="{\"result\":\"error\"}";
        if(userService.loginUsername(username, password))
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

    /*@RequestMapping("/email")
    public boolean loginEmail(String email, String password, HttpServletRequest request) {
        if(userService.loginEmail(email, password)) {
            return true;
        } else {
            return false;
        }
    }*/

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout() {
        return;
    }
}
