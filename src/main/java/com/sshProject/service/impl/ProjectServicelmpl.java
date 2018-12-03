package com.sshProject.service.impl;

import com.sshProject.dao.ProjectDao;
import com.sshProject.entity.Project;
import com.sshProject.entity.ProjectGroup;
import com.sshProject.entity.Task;
import com.sshProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ProjectServicelmpl implements ProjectService{
    @Autowired
    private ProjectDao projectDao;

    public boolean addProject(Project project) {
    	return projectDao.addProject(project);
    }

    public boolean updateProject(Project project) {
    	return projectDao.updateProject(project);
    }

    public boolean deleteProject(Project project) {
    	return projectDao.deleteProject(project);
    }

    public Project getProject(int projIndex) {
    	return projectDao.getProject(projIndex);
    }


    public ArrayList<Project> getAllProjects(){
    	ArrayList<Project> List = projectDao.getAllProjects();
    	return List;
    }

    public ProjectGroup getProjectTeam(int projectIndex) {
    	return projectDao.getProjectTeam(projectIndex);
    }

    public ArrayList<Task> getProjectTasks(int taskId){
    	ArrayList<Task> List = projectDao.getProjectTasks(taskId);
    	return List;
    }

   public boolean addTask(Task task) {
    	return projectDao.addTask(task);
    }

    public boolean updateTask(Task task) {
    	return projectDao.updateTask(task);
    }

    public boolean deleteTask(int taskIndex) {
    	return projectDao.deleteTask(taskIndex);
    }

    public Task getTask(int taskIndex) {
    	return projectDao.getTask(taskIndex);
    }
}
