package com.sshProject.service.impl;

import com.sshProject.dao.TrainingDao;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;
import com.sshProject.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrainingServicelmpl implements TrainingService {
    @Autowired
    private TrainingDao trainingDao;
    public boolean addTraining(Training training){
        return trainingDao.addTraining(training);
    }

    public boolean updateTraining(Training training){
        return trainingDao.updateTraining(training);
    }

    public boolean deleteTraining(int trainingIndex){
        return trainingDao.deleteTraining(trainingIndex);
    }

    public ArrayList<TrainingGroup> getTrainingGroup(int trainingIndex){
        return trainingDao.getTrainingGroup(trainingIndex);
    }

    public ArrayList<Training> getAllTrainings(){
        return trainingDao.getAllTrainings();
    }

    public ArrayList<TrainingGroup> getAllTrainingGroups(){
        return trainingDao.getAllTrainingGroups();
    }

   public  ArrayList<Training> getTraining(int employeeId){
        return trainingDao.getTraining(employeeId);
    }
}
