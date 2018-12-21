package com.sshProject.controller;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;
import com.sshProject.entity.Employee;
import  com.sshProject.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@RestController
@RequestMapping(path="/api/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping("/add")
    public boolean addTraining(@RequestBody Training training) {
        return trainingService.addTraining(training);
    }

    @PostMapping("/update")
    public boolean updateTraining(@RequestBody Training training) {
        return trainingService.updateTraining(training);
    }

    @PostMapping("/detele")
    public boolean deleteTraining(@RequestBody Training training) {
        return trainingService.deleteTraining(training);
    }

    @PostMapping("/getTrainingGroup")
    public String getTrainingGroup(@RequestParam int trainingIndex, HttpServletResponse response) {
        TrainingGroup trainingGroups = trainingService.getTrainingGroup(trainingIndex);
        String tra = "{\"trainingIndex\":\"" + trainingGroups.getTrainingIndex() + "\",\"trainingTeam\":\"" + trainingGroups.getTrainingTeam()
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

    @PostMapping("/getAllTrainings")
    public String getAllTrainings(HttpServletResponse response) {
        ArrayList<Training> trainings = TrainingService.getAllTrainings();
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
        ArrayList<TrainingGroup> trainingGroups = TrainingService.getAllTrainingGroups();
        StringBuilder array = new StringBuilder();
        for (TrainingGroup G : trainingGroups) {
            String arr = "{\"trainingIndex\":\"" + trainingGroups.getTrainingIndex() + "\",\"trainingTeam\":\"" + trainingGroups.getTrainingTeam()
            "\"}";
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

    @PostMapping("/getTraining")
    public String getTraining(@RequestParam int empolyeeId, HttpServletResponse response) {
        Training training=trainingService.getTrainingGroup(empolyeeId);
        String tra= "{\"trainingName\":\"" + training.getTrainingName() + "\", \"trainingOwnerId\":\"" + training.getTrainingOwnerId() + "\", \"trainingIndex\":\"" + training.getTrainingIndex() + "\", \"creatTime\":\"" + training.getCreatTime() + "\",\"endTime\":\"" + training.getEndTime() + "\", \"description\":\"" + training.getDescription() + "\"},";
        response.setContentType("application/json");
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

    @PostMapping("/getTrainProject")
    public String getTrainProject(@RequestParam int trainingIndex) {
          TrainingGroup trainingGroup
    }
}