package com.sshProject.service.impl;

import com.sshProject.dao.RoleDao;
import com.sshProject.entity.Employee;
import com.sshProject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public boolean addModule(int employeeIndex, int moduleIndex) {
        return roleDao.addModule(employeeIndex, moduleIndex);
    }

    public boolean removeModule(int employeeIndex, int moduleIndex) {
        return roleDao.removeModule(employeeIndex, moduleIndex);
    }

    public boolean addEmployee(Employee employee) {
        return roleDao.addEmployee(employee);
    }

    public boolean deleteEmployee(int employeeIndex) {
        return roleDao.deleteEmployee(employeeIndex);
    }

    public ArrayList<Employee> getEmployees() {
        return roleDao.getEmployees();
    }
}
