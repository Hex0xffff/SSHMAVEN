package com.sshProject.controller;

import com.sshProject.entity.Employee;
//import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingDemand;
import com.sshProject.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@RestController
@RequestMapping(value = "/api/demand")
public class DemandController
{
    @Autowired
    private DemandService demandService;
    @RequestMapping(value = "/getAllDemands",method = RequestMethod.GET)
    public  String getAllDemands(HttpServletRequest request)
    {
        request.setAttribute("TrainingDemandList",demandService.getAllDemands());

        return  "/index";
    }

    @RequestMapping(value = "/getDemands",method = RequestMethod.GET)
    public  String getDemand(int employeeIndex, HttpServletRequest request)
    {
        request.setAttribute("TrainingDemandList",demandService.getDemand(employeeIndex));

        return  "/index";
    }

    @RequestMapping(value="/addDemand" ,method=RequestMethod.POST)
    public String addDemand(TrainingDemand demand , HttpServletRequest request)
    {
        demandService.addDemand(demand);
        return "redirect:/addDemand";
    }
    @RequestMapping(value = "/deleteDemand" ,method=RequestMethod.DELETE)
    public void deleteDemand(int demandIndex,HttpServletResponse response)
    {
        String result="{\"result\":\"error\"}";
        if(demandService.deleteDemand(demandIndex))
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
    @RequestMapping(value = "/updateDemand",method = RequestMethod.POST)
    public String updateDemand(TrainingDemand demand, HttpServletRequest request)
    {
        if(demandService.updateDemand(demand))
        {
            demand=demandService.getDemand(demand.getEmployeeIndex());
            request.setAttribute("demand",demand);
            return "redirect:/updateDemand";
        }
        else
        {
            return "/error";
        }
    }

    @RequestMapping(value="/info/all",method =RequestMethod.GET)
    public  String getDemandGroupjson(HttpServletResponse response)
    {
        ArrayList<TrainingDemand> demands=demandService.getAllDemands();
        //json对象
        StringBuilder array=new StringBuilder();
        for (TrainingDemand d :demands)
        {
            String arr = "{\"employeeIndex\":\"" + d.getEmployeeIndex() + "\", \"demandName\":\"" + d.getDemandName() + "\", \"description\":\"" + d.getDescription() + "\", \"demandStatus\":\"" + d.getDemandStatus() + "\",\"demandIndex\":\""+d.getDemandIndex()+"\"},";
            array.append(arr);
        }
        //json数组
        String result="{\"demands\":["+array.substring(0,array.length()-1)+"]}";
        response.setContentType("application/json");
        try {
            PrintWriter out =response.getWriter();
            out.write(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return  "/index";
    }

    @RequestMapping(value="/info",method = RequestMethod.GET)
    public  String getPersonalDemandjson(int employeeIndex,HttpServletResponse response)
    {
        TrainingDemand demand=demandService.getDemand(employeeIndex);
        Employee employee=demandService.getEmployee(employeeIndex);
        String result="{\"demandName\":\""+demand.getDemandName()+"\",\"employeeIndex\":\""+demand.getEmployeeIndex()+
                "\",\"RealName\":\""+employee.getRealName()+"\",\"demandStatus\":\""+demand.getDemandStatus() +"\",\"Description\":" +
                "\""+demand.getDescription()+"\"},";
        response.setContentType("application/json");
        try
        {
            PrintWriter out =response.getWriter();
            out.write(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "/index";

    }

    /*@RequestMapping(value="/info",method = RequestMethod.GET)
    public  String getDemandjson(HttpServletRequest response)
    {
        //TrainingDemand demand=demandService.
    }
/*
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
     */
}
