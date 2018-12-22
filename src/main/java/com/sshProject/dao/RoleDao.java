package com.sshProject.dao;
import com.sshProject.entity.Employee;
import java.util.ArrayList;
public interface RoleDao {
    boolean addModule(int employeeIndex,int moudleIndex);z

    boolean removeModule(int employeeIndex,int moduleIndex);

    boolean addEmployee(Employee employee);

    boolean deleteEmployee(int employeeIndex);

    ArrayList<Employee> getEmployees();
}
