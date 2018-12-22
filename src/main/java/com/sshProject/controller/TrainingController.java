package com.sshProject.controller;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;
import com.sshProject.entity.Employee;
import  com.sshProject.service.TrainingService;
import net.sf.json.*;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RestController
@RequestMapping(path="/api/train")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping("/add")
    public void addTraining(HttpServletRequest request, HttpServletResponse response)
    {
        Training training = new Training();
        String jsonData = request.getParameter("traininfo");
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        for(int i = 0;i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            training.setTrainingName(jsonObj.getString("trainingName"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date utilDate=sdf.parse(jsonObj.getString("creatTime"));
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                training.setCreatTime(sqlDate);
                utilDate = sdf.parse(jsonObj.getString("endTime"));
                sqlDate = new java.sql.Date(utilDate.getTime());
                training.setEndTime(sqlDate);
            }
            catch(Exception e){
                e.printStackTrace();
            }training.setDescription(jsonObj.getString("description"));
            training.setTrainingIndex(jsonObj.getInt("trainingIndex"));
            training.setTrainingOwnerId(jsonObj.getInt("trainingOwnerId"));
        }
            String result="{\"result\":\"error\"}";
        if(trainingService.addTraining(training))
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
    public void updateTraining(HttpServletRequest request,HttpServletResponse response) {
        Training training = new Training();
        String jsonData = request.getParameter("traininfo");
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            training.setTrainingName(jsonObj.getString("trainingName"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            training.setDescription(jsonObj.getString("description"));
            try {
                java.util.Date utilDate = sdf.parse(jsonObj.getString("creatTime"));
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                training.setCreatTime(sqlDate);
                utilDate = sdf.parse(jsonObj.getString("endTime"));
                sqlDate = new java.sql.Date(utilDate.getTime());
                training.setEndTime(sqlDate);
            } catch (Exception e) {
                e.printStackTrace();
                training.setTrainingIndex(jsonObj.getInt("trainingIndex"));
                training.setTrainingOwnerId(jsonObj.getInt("trainingOwnerId"));
            }
            String result = "{\"result\":\"error\"}";
            if (trainingService.updateTraining(training)) {
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
    }

    @DeleteMapping("/detele")
    public void deleteTraining(HttpServletRequest request,HttpServletResponse response)
    {
        int trainingIndex;
        String jsonData = request.getParameter("trainId");
        JSONObject jsonObj=JSONObject.fromObject(jsonData);
        trainingIndex=jsonObj.getInt("trainId");
        String result="{\"result\":\"error\"}";
        if(trainingService.deleteTraining(trainingIndex))
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


    @PostMapping("/getTrainingGroup")
    public String getTrainingGroup(HttpServletRequest request, HttpServletResponse response) {
        String jsonData = request.getParameter("trainId");
        JSONObject jsonObj=JSONObject.fromObject(jsonData);
        int trainingIndex;
        trainingIndex=jsonObj.getInt("trainId");
        TrainingGroup trainingGroups = trainingService.getTrainingGroup(trainingIndex).get(0);
        String tra = "{\"trainingIndex\":\"" + trainingGroups.getTrainingIndex() + "\",\"trainingTeam\":\"" + trainingGroups.getTrainingTeam()+
        "\"}";
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(tra);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }

    @PostMapping("/all")
    public String getAllTrainings(HttpServletResponse response) {
        ArrayList<Training> trainings = trainingService.getAllTrainings();
        StringBuilder array = new StringBuilder();
        for (Training t : trainings) {
            String arr = "{\"trainingName\":\"" + t.getTrainingName() + "\", \"trainingOwnerId\":\"" + t.getTrainingOwnerId() + "\", \"trainingIndex\":\"" + t.getTrainingIndex() + "\", \"creatTime\":\"" + t.getCreatTime() + "\",\"endTime\":\"" + t.getEndTime() + "\", \"description\":\"" + t.getDescription() + "\"},";
            array.append(arr);
        }
        String result = "{\"training\":[" + array.substring(0, array.length() - 1) + "]}";
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";

    }

    @PostMapping("/getAllTrainingGroups")
    public String getAllTrainingGroups(HttpServletResponse response) {
        ArrayList<TrainingGroup> trainingGroups = trainingService.getAllTrainingGroups();
        StringBuilder array = new StringBuilder();
        for (TrainingGroup G : trainingGroups) {
            String arr = "{\"trainingIndex\":\"" + G.getTrainingIndex() + "\",\"trainingTeam\":\"" + G.getTrainingTeam()+"\"}";
            array.append(arr);
        }
        String result = "{\"trainingGroups\":[" + array.substring(0, array.length() - 1) + "]}";
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }

    @PostMapping("/info")
    public String getTraining(HttpServletRequest request, HttpServletResponse response) {
        String jsonData = request.getParameter("trainId");
        JSONObject jsonObj=JSONObject.fromObject(jsonData);
        int employeeId;
        employeeId=jsonObj.getInt("trainId");
        ArrayList<Training> trainings =trainingService.getTraining(employeeId);
        response.setContentType("application/json");
        String tra=new String();
        for (Training T:trainings){
             tra= "{\"trainingName\":\"" + T.getTrainingName() + "\", \"trainingOwnerId\":\"" + T.getTrainingOwnerId() + "\", \"trainingIndex\":\"" + T.getTrainingIndex() + "\", \"creatTime\":\"" + T.getCreatTime() + "\",\"endTime\":\"" + T.getEndTime() + "\", \"description\":\"" + T.getDescription() + "\"},";
        }
        try {
            PrintWriter out =response.getWriter();
            out.write(tra);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return  "/index";

    }
}