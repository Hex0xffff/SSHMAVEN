package com.sshProject.service.impl;

import com.sshProject.dao.DemandDao;
import com.sshProject.entity.TrainingDemand;
import com.sshProject.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class DemandServiceImpl implements DemandService
{
    @Autowired
    private DemandDao demandDao;

    public void addDemand(TrainingDemand demand)
    {
        demandDao.addDemand(demand);
    }

    public boolean updateDemand(TrainingDemand demand)
    {
        return demandDao.updateDemand(demand);
    }

    public boolean deleteDemand(int demandIndex)
    {
        return demandDao.deleteDemand(demandIndex);
    }

    public ArrayList<TrainingDemand> getAllDemands()
    {
        ArrayList<TrainingDemand> list=demandDao.getAllDemands();
        return list;
    }

    public TrainingDemand getDemand(int employeeIndex)
    {
        return demandDao.getDemand(employeeIndex);
    }

}
