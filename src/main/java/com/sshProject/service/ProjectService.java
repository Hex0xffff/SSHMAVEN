package com.sshProject.service;

import com.sshProject.entity.Employee;
import com.sshProject.entity.Project;
import com.sshProject.entity.ProjectGroup;
import com.sshProject.entity.Task;

import java.util.ArrayList;


public interface ProjectService {

    Task getTask(int taskIndex);

    Project getProject(int projIndex);

    ProjectGroup getProjectTeam(int projectIndex);

    ArrayList<Project> getAllProjects();

    ArrayList<ProjectGroup> getAllProjTeam();

    ArrayList<Task> getProjectTasks(int projectIndex);

    boolean addProject(Project project);

    boolean updateProject(Project project);

    boolean deleteProject(Project project);

    boolean addTask(Task task);

    boolean updateTask(Task task);

    boolean deleteTask(int taskIndex);

    boolean updateProjectTeam(ProjectGroup projectGroup);

    boolean delProjTeamByGroup(ProjectGroup projectGroup);

    boolean addProjTeam(ProjectGroup projectGroup);

    boolean getProjectByEmpId(int employeeId);

    boolean delProjTeam(ProjectGroup projectGroup);

    boolean updateProjEmplById(Project project);

    boolean delProjEmplByGroup(Project project);

    boolean delProjEmplById(Project project);
}
