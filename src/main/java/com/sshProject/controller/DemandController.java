package com.sshProject.controller;

import com.sshProject.entity.Employee;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingDemand;
import com.sshProject.service.DemandService;

import net.sf.json.*;
import org.hibernate.sql.SelectValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.lang.String;

@RestController
//@RequestMapping(value = "/api/demand")
@RequestMapping(value="/api/train/demand")
public class DemandController
{
    @Autowired
    private DemandService demandService;
    /*@RequestMapping(value = "/getAllDemands",method = RequestMethod.GET)
    public  String getAllDemands(HttpServletRequest request)
    {
        request.setAttribute("TrainingDemandList",demandService.getAllDemands());

    }

    @RequestMapping(value = "/getDemands",method = RequestMethod.GET)
    public  String getDemand( HttpServletRequest request)
    {
       int employeeIndex = Integer.valueOf(request.getParameter("employeeIndex"));
        request.setAttribute("TrainingDemandList",demandService.getDemand(employeeIndex));

    }*/

    @RequestMapping(value="/add" ,method=RequestMethod.POST)
    public void addDemand(HttpServletResponse response,HttpServletRequest request)
    {
        String jsonstring = request.getParameter("demandInfo");
        //JSONObject jsonObject = JSONObject.fromObject(demandInfo);
        JSONObject jsonObject=JSONObject.fromObject(jsonstring);
        TrainingDemand demand=new TrainingDemand();
        int demandIndex=jsonObject.getInt("demandIndex");
        int employeeIndex=jsonObject.getInt("employeeIndex");
        String demandName=jsonObject.getString("demandName");
        String description=jsonObject.getString("descrption");
        int demandStatus=jsonObject.getInt("demandStatus");
        demand.setDemandIndex(demandIndex);
        demand.setDemandName(demandName);
        demand.setDemandStatus(demandStatus);
        demand.setDescription(description);
        demand.setEmployeeIndex(employeeIndex);
        demandService.addDemand(demand);

        String result;
        result="{\"result\":\"success\"}";
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


    }

    @RequestMapping(value = "/delete" ,method=RequestMethod.DELETE)
    public  void deleteDemand(HttpServletResponse response, HttpServletRequest request)
    {
        int demandIndex=Integer.valueOf(request.getParameter("demandId"));
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
    //------------//
    //api
//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    //api Update没information
//    public void updateDemand(HttpServletResponse response, HttpServletRequest request)
//    {
//
//        if(demandService.updateDemand(demand))
//        {
//            demand=demandService.getDemand(demand.getEmployeeIndex());
//            request.setAttribute("demand",demand);
//            return "redirect:/updateDemand";
//        }
//        else
//        {
//            return "/error";
//        }
//    }

    @RequestMapping(value="/all",method =RequestMethod.GET)
    public  void  getAllDemand(HttpServletResponse response,HttpServletRequest request)
    {
        ArrayList<TrainingDemand> demands=demandService.getAllDemands();
        //json对象
        StringBuilder array=new StringBuilder();
        /*for (TrainingDemand d :demands)
        {

            String arr = "{\"employeeIndex\":\"" + d.getEmployeeIndex() + "\", \"demandName\":\"" + d.getDemandName() + "\", \"description\":\"" + d.getDescription() + "\", \"demandStatus\":\"" + d.getDemandStatus() + "\",\"demandIndex\":\""+d.getDemandIndex()+"\"},";
            array.append(arr);
        }
        //json数组
        String result="{\"demands\":["+array.substring(0,array.length()-1)+"]}";
        */
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<demands.size();i++)
        {
            TrainingDemand d=demands.get(i);
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            jsonObject.element("demandIndex",d.getDemandIndex());
            jsonObject.element("employeeId",d.getEmployeeIndex());
            jsonObject.element("demandName",d.getDemandName());
            jsonObject.element("description",d.getDescription());
            jsonObject.element("demandStatus",d.getDemandStatus());
            jsonArray.add(jsonObject);
        }
        String result=jsonArray.toString();//要不要在前面加demand
        result = "{\"Deamnd\":["+result+"]}";
        response.setContentType("application/json");
        try {
            PrintWriter out =response.getWriter();
            out.write(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

   /* @RequestMapping(value="/info",method = RequestMethod.GET)
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

    }*/ ///通过employeeid查找单独的demand 现在好像不需要
}
