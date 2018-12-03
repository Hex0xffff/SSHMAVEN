package com.sshProject.dao;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;
import com.sshProject.entity.Employee;
import java.util.ArrayList;
public interface TrainingDao {
    void addTraining(Training training);

    boolean updateTraining(Training training);

    boolean deleteTraining(int trainingIndex);

    ArrayList<TrainingGroup> getTrainingGroup(int trainingIndex);

    ArrayList<Training> getAllTrainings();

    ArrayList<TrainingGroup> getAllTrainingGroups();

    ArrayList<Training> getTraining(int employeeId);

}
