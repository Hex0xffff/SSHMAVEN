package com.sshProject.controller;

import com.sshProject.entity.Employee;
import com.sshProject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/Role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addModule", method = RequestMethod.POST)
    public String addModule(int employeeIndex, int moduleIndex, HttpServletRequest request) {
        return "";
    }

    @RequestMapping("/removeModule")
    public String removeModule(int employeeIndex, int moduleIndex, HttpServletRequest request) {
        return "";
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(Employee employee, HttpServletRequest request) {
        roleService.addEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(int employeeIndex, HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";
        if(roleService.deleteEmployee(employeeIndex)) {
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/";
    }

    @RequestMapping(value = "/info/all", method = RequestMethod.GET)
    public String getEmployees(HttpServletResponse response) {
        ArrayList<Employee> employees = roleService.getEmployees();

        String result = "{\"employeeId\":\"xxx\", \"identicalNumber\":\"xxx\", \"RealName\":\"xxx\", \"Address\":\"xxx\"}";
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        };

        return "/";
    }
}
