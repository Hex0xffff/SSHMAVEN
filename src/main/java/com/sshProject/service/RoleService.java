package com.sshProject.service;

import com.sshProject.entity.Employee;

import java.util.ArrayList;

public interface RoleService {
    boolean addModule(int employeeIndex,int moduleIndex);

    boolean removeModule(int employeeIndex,int moduleIndex);

    boolean addEmployee(Employee employee);

    boolean deleteEmployee(int employeeIndex);

    ArrayList<Employee> getEmployees();
}
