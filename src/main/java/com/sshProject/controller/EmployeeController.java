package com.sshProject.controller;
import  com.sshProject.entity.Employee;
import  com.sshProject.service.RoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping(path="/api/employee")
public class EmployeeController {
    @Autowired
    private  RoleService roleService;
    @PostMapping("/add")
    public void addEmployee(HttpServletRequest request,HttpServletResponse response)
    {
        Employee employee =new Employee();
        String jsonData = request.getParameter("epInfo");
        JSONObject jsonObj= JSONObject.fromObject(jsonData);
        employee.setAddress(jsonObj.getString("address"));
        employee.setEmployeeIndex(jsonObj.getInt("employeeIndex"));
        employee.setIdenticalNumber(jsonObj.getString("identicalNumber"));
        employee.setName(jsonObj.getString("realName"));
        employee.setUserId(jsonObj.getInt("userId"));
        String result="{\"result\":\"error\"}";
        if(roleService.addEmployee(employee))
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
    @DeleteMapping("/delete")
    public void deleteEmpolyee(HttpServletRequest request,HttpServletResponse response)
    {
        int employeeIndex;
        String jsonData = request.getParameter("epId");
        JSONObject jsonObj= JSONObject.fromObject(jsonData);
        employeeIndex = jsonObj.getInt("epId");
        String result="{\"result\":\"error\"}";
        if(roleService.deleteEmployee(employeeIndex))
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
    @PostMapping("/info/all")
    public String getEmployee(HttpServletRequest request,HttpServletResponse response)
    {
        ArrayList<Employee> employees = roleService.getEmployees();
        String jsonData = request.getParameter("listQuery");
        JSONArray jsonArray= JSONArray.fromObject(jsonData);
        for(int i = 0;i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Employee employee = new Employee();
            employee.setAddress(jsonObj.getString("address"));
            employee.setEmployeeIndex(jsonObj.getInt("employeeIndex"));
            employee.setIdenticalNumber(jsonObj.getString("identicalNumber"));
            employee.setRealName(jsonObj.getString("realName"));
            employee.setUserId(jsonObj.getInt("userId"));
            employees.add(employee);
        }
        StringBuilder array = new StringBuilder();
        for (Employee E : employees) {
            String arr = "{\"userId\":\"" + E.getUserId() + "\", \"employeeIndex\":\"" + E.getEmployeeIndex() + "\", \"identicalNumber\":\"" + E.getIdenticalNumber() + "\", \"realName\":\"" + E.getRealName() + "\",\"address\":\"" + E.getAddress() + "\"},";
            array.append(arr);
        }
        String result = "{\"Employee\":[" + array.substring(0, array.length() - 1) + "]}";
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }
    @DeleteMapping("/deletegroup")
    public void deleteEmpolyeeGroup(HttpServletRequest request,HttpServletResponse response){
          String jsonData=request.getParameter("epIds");
          JSONArray jsonArray= JSONArray.fromObject(jsonData);
          String result = "{\"result\":\"success\"}";
        for(int i = 0;i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            int employeeIndex;
            employeeIndex = jsonObj.getInt("employeeIndex");

            if (!roleService.deleteEmployee(employeeIndex)) {
                 result = "{\"result\":\"error\"}";
            }
            response.setContentType("application/json");
        }
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
}
