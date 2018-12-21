package com.sshProject.service;

import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;

import java.util.ArrayList;

public interface TrainingService {
    boolean addTraining(Training training);

    boolean updateTraining(Training training);

    boolean deleteTraining(int trainingIndex);

    ArrayList<TrainingGroup> getTrainingGroup(int trainingIndex);

    ArrayList<Training> getAllTrainings();

    ArrayList<TrainingGroup> getAllTrainingGroups();

    ArrayList<Training> getTraining(int employeeId);
}
