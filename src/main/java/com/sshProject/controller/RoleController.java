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
        roleService.addModule(employeeIndex, moduleIndex);
        return "redirect:/index";
    }

    @RequestMapping(value = "/removeModule", method = RequestMethod.DELETE)
    public String removeModule(int employeeIndex, int moduleIndex, HttpServletResponse response) {
        String result = "";
        return "/index";
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(Employee employee, HttpServletRequest request) {
        roleService.addEmployee(employee);
        return "redirect:/index";
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
        return "/index";
    }

    @RequestMapping(value = "/info/all", method = RequestMethod.GET)
    public String getEmployees(HttpServletResponse response) {
        ArrayList<Employee> employees = roleService.getEmployees();
        //json对象
        StringBuilder array = new StringBuilder();
        for(Employee e : employees) {
            String arr = "{\"employeeId\":\"" + e.getEmployeeIndex() + "\", \"identicalNumber\":\"" + e.getIdenticalNumber() + "\", \"RealName\":\"" + e.getRealName() + "\", \"Address\":\"" + e.getAddress() + "\"},";
            array.append(arr);
        }
        //json数组
        String result = "{\"employees\": [" + array.substring(0, array.length() - 1) + "] }";

        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        };
        return "/index";
    }
}
