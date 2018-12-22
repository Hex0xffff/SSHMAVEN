package com.sshProject.service;

import com.sshProject.entity.TrainingResource;

import java.util.ArrayList;

public interface ResourceService {
    boolean addResource(TrainingResource resource);
    boolean deleteResource(int trainerId);
    boolean updateResource(TrainingResource resource);
    ArrayList<TrainingResource> getallResource();
}
