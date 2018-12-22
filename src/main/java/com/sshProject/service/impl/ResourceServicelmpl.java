package com.sshProject.service.impl;

import com.sshProject.dao.ResourceDao;
import com.sshProject.entity.TrainingResource;
import com.sshProject.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ResourceServicelmpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;
    public boolean addResource(TrainingResource resource){
        return resourceDao.addResource(resource);
    }
    public boolean deleteResource(int trainerId){
        return resourceDao.deleteResource(trainerId);
    }
    public boolean updateResource(TrainingResource resource){
        return resourceDao.updateResource(resource);
    }
    public ArrayList<TrainingResource> getallResource(){
        ArrayList<TrainingResource> List=resourceDao.getallResource();
        return List;
    }
}
