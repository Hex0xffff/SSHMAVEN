package com.sshProject.controller;

import com.sshProject.entity.Project;
import com.sshProject.entity.Task;
import com.sshProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @RequestMapping(value = "/getAllProject", method = RequestMethod.GET)
    public String getAllProjects(HttpServletRequest request){

        request.setAttribute("projectList", projectService.getAllProjects());

        return "/index";
    }

    @RequestMapping(value = "/getProject", method = RequestMethod.GET)
    public String getProject(int projIndex ,HttpServletRequest request){

        request.setAttribute("project", projectService.getProject(projIndex));

        return "/editProject";
    }
    
    @RequestMapping(value = "/getTask",method = RequestMethod.GET)
    public String getTask(int taskIndex,HttpServletRequest request) {
    	request.setAttribute("task", projectService.getTask(taskIndex));
    	return "/editTask";
    }

    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    public String addProject(Project project, HttpServletRequest request){

    	projectService.addProject(project);

        return "redirect:/addproject";
    }

    @RequestMapping(value = "/delProject",method = RequestMethod.DELETE)
    public void delProject(Project project,HttpServletResponse response){

        String result = "{\"result\":\"error\"}";

        if(projectService.deleteProject(project)){
            result = "{\"result\":\"success\"}";
        }

        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateProject",method = RequestMethod.POST)
    public String updateProject(Project project,HttpServletRequest request){

        if(projectService.updateProject(project)){
        	project = projectService.getProject((project.getProjIndex()));
            request.setAttribute("project", project);
            return "redirect:/getAllProject";
        }else{
            return "/error";
        }
    }
    
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(Task task,HttpServletRequest request) {
    	projectService.addTask(task);
    	return "redirect:/addtask";
    }

    @RequestMapping(value = "/updateTask",method = RequestMethod.POST)
    public String updateTask(Task task,HttpServletRequest request) {
        if(projectService.updateTask(task)){
        	task = projectService.getTask((task.getTaskId()));
            request.setAttribute("task", task);
            return "redirect:/updateTask";
        }else{
            return "/error";
        }
    }

    @RequestMapping(value = "/deleteTask",method = RequestMethod.DELETE)
    public void deleteTask(int taskIndex,HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";

        if(projectService.deleteTask(taskIndex)){
            result = "{\"result\":\"success\"}";
        }

        response.setContentType ("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
