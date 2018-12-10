package com.sshProject.controller;
import com.sshProject.entity.Project;
import com.sshProject.entity.ProjectGroup;
import com.sshProject.entity.Task;
import com.sshProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(path="/api/Project")
public class ProjectController {
@Autowired
    private  ProjectService projectService;
@PostMapping("/addProject")
    public  boolean addProject(@RequestBody Project project)
{
    return projectService.addProject(project);
}
@PostMapping("/updateProject")
    public  boolean updateProject(@RequestBody Project project)
{
    return projectService.updateProject(project);
}
@PostMapping("/deleteProject")
   public  boolean deleteProject(@RequestBody Project project)
{
    return projectService.deleteProject(project);
}
@PostMapping("/getProjectTeam")
   public  ProjectGroup getProjectTeam(@RequestParam int projectIndex)
{
    return projectService.getProjectTeam(projectIndex);
}
@PostMapping("/getProjectTasks")
   public ArrayList<Task> getProjectTasks(@RequestBody int projectIndex)
{
    return projectService.getProjectTasks(projectIndex);
}
@PostMapping("/addTask")
   public boolean addTask(@RequestBody Task task)
{
    return projectService.addTask(task);
}
@PostMapping("/updateTask")
   public boolean updateTask(@RequestBody Task task)
{
    return projectService.updateTask(task);
}
@PostMapping("/deleteTask")
    public  boolean deleteTask(@RequestParam int taskIndex)
    {
        return projectService.deleteTask(taskIndex);
    }
@PostMapping("/getTask")
    public   Task getTask(@RequestParam int taskIndex)
{
    return projectService.getTask(taskIndex);
}
@PostMapping("/getProject")
    public   Project getProject(@RequestParam int projectIndex)
{
    return projectService.getProject(projectIndex);
}
@PostMapping("/getAllProjects")
    public  ArrayList<Project> getAllProjects()
{
    return projectService.getAllProjects();
}
}
