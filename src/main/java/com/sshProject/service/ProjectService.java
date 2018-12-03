package com.sshProject.service;

import com.sshProject.entity.Project;
import com.sshProject.entity.ProjectGroup;
import com.sshProject.entity.Task;

import java.util.ArrayList;


public interface ProjectService {

	boolean addProject(Project project);

    boolean updateProject(Project project);

    boolean deleteProject(Project project);

    ProjectGroup getProjectTeam(int projectIndex);

    ArrayList<Task> getProjectTasks(int projectIndex);

    boolean addTask(Task task);

    boolean updateTask(Task task);

    boolean deleteTask(int taskIndex);

    Task getTask(int taskIndex);

    Project getProject(int projIndex);

    ArrayList<Project> getAllProjects();
}
