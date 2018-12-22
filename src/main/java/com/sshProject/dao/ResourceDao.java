package com.sshProject.dao;
import com.sshProject.entity.TrainingResource;

import java.util.ArrayList;

public interface ResourceDao {
    boolean addResource(TrainingResource resource);
    boolean deleteResource(int trainerId);
    boolean updateResource(TrainingResource resource);
    ArrayList<TrainingResource> getallResource();

}
