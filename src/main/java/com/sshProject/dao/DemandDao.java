package com.sshProject.dao;
import com.sshProject.entity.Employee;
import com.sshProject.entity.TrainingDemand;
import java.util.ArrayList;
public interface DemandDao {
    boolean addDemand(TrainingDemand demand);

    boolean updateDemand(TrainingDemand demand);

    boolean deleteDemand(int demandIndex);

    //ArrayList<TrainingDemand> getDemands(int employeeId);

    ArrayList<TrainingDemand> getAllDemands();

    TrainingDemand getDemand(int employeeIndex);

    Employee getEmployee(int employeeIndex);
}
