package com.sshProject.service;

import com.sshProject.entity.TrainingDemand;

import java.util.ArrayList;

public interface DemandService
{
    void addDemand(TrainingDemand demand);

    boolean updateDemand(TrainingDemand demand);

    boolean deleteDemand(int demandIndex);

    ArrayList<TrainingDemand> getAllDemands();

    TrainingDemand getDemand(int employeeIndex);
}
