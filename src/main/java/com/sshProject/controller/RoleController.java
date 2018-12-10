package com.sshProject.controller;

import  com.sshProject.entity.User;
import  com.sshProject.entity.Employee;
import  com.sshProject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/addModule")
    public boolean addModule(@RequestParam int employeeIndex, @RequestParam int moduleIndex)
    {
        return roleService.addModule(employeeIndex,moduleIndex);
    }
    @PostMapping("/deleteModule")
    public boolean removeModule(@RequestParam int employeeIndex,@RequestParam int moduleIndex)
    {
        return roleService.removeModule(employeeIndex,moduleIndex);
    }
    @PostMapping("/addEmployee")
    public boolean addEmployee(@RequestBody Employee employee)
    {
        return roleService.addEmployee(employee);
    }
    @PostMapping("/deleteEmployee")
    public boolean deleteEmpolyee(@RequestParam int employeeIndex)
    {
        return roleService.deleteEmployee(employeeIndex);
    }
    @PostMapping("/getEmployee")
    public ArrayList<Employee> getEmployee()
    {
        return roleService.getEmployees();
    }

}
