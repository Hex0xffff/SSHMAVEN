package com.sshProject.controller;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;
import com.sshProject.entity.Employee;
import  com.sshProject.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(path="/api/training")
public class TrainingController {
@Autowired
    private  TrainingService trainingService;
@PostMapping("/addTraining")
    public  boolean addTraining (@RequestBody Training training)
{
    return trainingService.addTraining(training);
}
@PostMapping("/updateTraining")
    public  boolean updateTraining(@RequestBody Training training)
{
    return trainingService.updateTraining(training);
}
@PostMapping("/deteleTraining")
    public  boolean deleteTraining(@RequestBody Training training)
{
     return trainingService.deleteTraining(training);
}
@PostMapping("/getTrainingGroup")
    public ArrayList<TrainingGroup> getTrainingGroup(@RequestParam int trainingIndex)
{
    return trainingService.getTrainingGroup();
}
@PostMapping("/getAllTrainings")
    public  ArrayList<Training> getAllTrainings()
{
    return trainingService.getAllTrainings();
}
@PostMapping("/getAllTrainingGroups")
    public  ArrayList<TrainingGroup> getAllTrainingGroups()
{
    return trainingService.getAllTrainingGroups();
}
@PostMapping("/getTraining")
    public  ArrayList<Training> getTraining(@RequestParam int empolyeeId)
{
    return trainingService.getTraining();
}
}
