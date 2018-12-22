package com.sshProject.controller;


import com.sshProject.entity.TrainingResource;
import com.sshProject.service.ResourceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/train/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @PostMapping("/add")
    public void addResource(HttpServletRequest request, HttpServletResponse response){
        TrainingResource trainingResource = new TrainingResource();
        String jsonData = request.getParameter("resourceInfo");
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        for(int i = 0;i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            trainingResource.setTrainerId(jsonObj.getInt("trainId"));
            trainingResource.setClassName(jsonObj.getString("className"));
            trainingResource.setDescription(jsonObj.getString("description"));
            trainingResource.setResourceStatus(jsonObj.getInt("resourceStatus"));
        }
        String result="{\"result\":\"error\"}";
        if(resourceService.addResource(trainingResource))
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
    public void deleteResource(HttpServletRequest request,HttpServletResponse response)
    {
        int trainerId;
        String jsonData = request.getParameter("resourceId");
        JSONObject jsonObject = JSONObject.fromObject(jsonData);
        trainerId = jsonObject.getInt("resourceId");
        String result="{\"result\":\"error\"}";
        if(resourceService.deleteResource(trainerId))
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
    @PostMapping("/update")
    public void updateResource(HttpServletRequest request,HttpServletResponse response)
    {
        TrainingResource trainingResource = new TrainingResource();
        String jsonData = request.getParameter("resourceInfo");
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        for(int i = 0;i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            trainingResource.setTrainerId(jsonObj.getInt("trainId"));
            trainingResource.setClassName(jsonObj.getString("className"));
            trainingResource.setDescription(jsonObj.getString("description"));
            trainingResource.setResourceStatus(jsonObj.getInt("resourceStatus"));
        }
        String result="{\"result\":\"error\"}";
        if(resourceService.updateResource(trainingResource))
            {
                result = "{\"result\":\"success\"}";
            }

            response.setContentType("application/json");

            try {
                PrintWriter out = response.getWriter();
                out.write(result);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @PostMapping("/all")
    public String getallResource(HttpServletRequest request,HttpServletResponse response){
        ArrayList<TrainingResource> trainingResources = resourceService.getallResource();
        StringBuilder array = new StringBuilder();
        String jsonData = request.getParameter("resourceInfo");
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        for(int i = 0;i < jsonArray.size(); i++) {
            TrainingResource trainingResource = new TrainingResource();
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            trainingResource.setTrainerId(jsonObj.getInt("trainId"));
            trainingResource.setClassName(jsonObj.getString("className"));
            trainingResource.setDescription(jsonObj.getString("description"));
            trainingResource.setResourceStatus(jsonObj.getInt("resourceStatus"));
            trainingResources.add(trainingResource);
        }
        for (TrainingResource t : trainingResources) {
            String arr = "{\"className\":\"" + t.getClassName() + "\", \"trainerId\":\"" + t.getTrainerId() + "\", \"resourceStatus\":\"" + t.getResourceStatus() + "\", \"description\":\"" + t.getDescription() +"\"},";
            array.append(arr);
        }
        String result = "{\"trainingResource\":[" + array.substring(0, array.length() - 1) + "]}";
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }

}
