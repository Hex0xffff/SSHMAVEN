package com.sshProject.controller;

import com.sshProject.entity.Project;
import com.sshProject.entity.ProjectGroup;
import com.sshProject.entity.Task;
import com.sshProject.service.ProjectService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @RequestMapping(value = "/info/all", method = RequestMethod.POST)
    public void getAllProject(HttpServletResponse response,HttpServletRequest request){
        ArrayList<Project> projects = projectService.getAllProjects();
        StringBuilder array = new StringBuilder();
        for(Project p : projects) {
            String arr = "{\"projIndex\":\"" + p.getProjIndex() + "\", \"projMame\":\"" + p.getProjName() + "\", \"Ownerid\":\"" + p.getOwnerld() + "\", \"createTime\":\"" + p.getCreatTime() + "\", \"EndTime\":\"" + p.getEndTime() + "\", \"description\":\"" + p.getDescription() + "\"},";
            array.append(arr);
        }

        String result = "{\"project\": [" + array.substring(0, array.length() - 1) + "] }";

        response.setContentType("application/json;charset=utf-8");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public void getProjectById(HttpServletResponse response,HttpServletRequest request){
        int projIndex = Integer.valueOf(request.getParameter("projId"));
        Project project = projectService.getProject(projIndex);
        JSONArray jsonArray = new JSONArray();


    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void delProjectById(HttpServletResponse response,HttpServletRequest request){
        int projId = Integer.valueOf(request.getParameter("projId"));
        String result = "{\"result\":\"error\"}";

        if(projectService.deleteProject(projectService.getProject(projId))){
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


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addProject(Project project, HttpServletRequest request){

        projectService.addProject(project);

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateProject(Project project,HttpServletRequest request){

        if(projectService.updateProject(project)){
            project = projectService.getProject((project.getProjIndex()));
            request.setAttribute("project", project);
            //return "redirect:/getAllProject";
        }else{
            //return "/error";
        }
    }

    @RequestMapping(value = "/delgroup",method = RequestMethod.DELETE)
    public void delProjByGroup(Project project,HttpServletResponse response) {

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

    @RequestMapping(value = "/task/add",method = RequestMethod.POST)
    public void addProjTask(int userId, int projId,HttpServletRequest request) {
        projectService.addTask(projectService.getTask(projId));
        //return "redirect:/addtask";
    }

    @RequestMapping(value = "/task/delete",method = RequestMethod.DELETE)
    public void delProjTaskByid(int userId, int projId, int taskIndex,HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";

        if(projectService.deleteTask(taskIndex)){
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

    @RequestMapping(value = "/task/delgroup",method = RequestMethod.DELETE)
    public void deleteProjTaskByGroup(int userId, int projId, int taskIndex,HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";

        if(projectService.deleteTask(taskIndex)){
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

    @RequestMapping(value = "/team/delgroup",method = RequestMethod.DELETE)
    public void deleteProjTeamByGroup(int userId, int projId, int teamIds, HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";

        if(projectService.delProjTeamByGroup(projectService.getProjectTeam(teamIds))){
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

    @RequestMapping(value = "/task/update",method = RequestMethod.POST)
    public void updateProjectTask(Task task,HttpServletRequest request) {
        if(projectService.updateTask(task)){
            task = projectService.getTask((task.getTaskId()));
            request.setAttribute("task", task);
            // return "redirect:/updateTask";
        }else{
            // return "/error";
        }
    }


    @RequestMapping(value = "/team/update",method = RequestMethod.POST)
    public void updateProjectTeam(ProjectGroup projectGroup, HttpServletRequest request) {
        if(projectService.updateProjectTeam(projectGroup)){
            projectGroup = projectService.getProjectTeam((projectGroup.getProjTeam()));
            request.setAttribute("projectTeam", projectGroup);
            // return "redirect:/updateTask";
        }else{
            // return "/error";
        }
    }

    @RequestMapping(value = "/team/add",method = RequestMethod.POST)
    public void addProjTeam(ProjectGroup projectGroup, HttpServletRequest request) {
        projectService.addProjTeam(projectGroup);
        //return "redirect:/addprojTeam";
    }

    @RequestMapping(value = "/team/all",method = RequestMethod.POST)
    public String getAllProjTeam(ProjectGroup projectGroup, HttpServletRequest request) {
        request.setAttribute("AllProjTeam",projectService.getAllProjTeam());
        return "/editAllProjTeam";
    }

    @RequestMapping(value = "/team/info",method = RequestMethod.POST)
    public String getProjTeamByProjId(int userId, int projIndex, HttpServletRequest request) {
        request.setAttribute("ProjTeam",projectService.getProjectTeam(projIndex));
        return "/editProjTeam";
    }

    @RequestMapping(value = "info/myproject",method = RequestMethod.POST)
    public String getProjectByEmpId(int userId, int employeeId,HttpServletRequest request) {
        request.setAttribute("ProjectByEmpId",projectService.getProjectByEmpId(employeeId));
        return "/editProjectByEmpId";
    }

    @RequestMapping(value = "task/info/mytask", method = RequestMethod.POST)
    public String getAllTaskByEmpId(Task task, HttpServletRequest request) {
        request.setAttribute("AllTask",projectService.getTask(task.getTaskId()));
        return "/editAllTask";
    }

    @RequestMapping(value = "task/info/all", method = RequestMethod.POST)
    public String getAllTaskByProjId(Task task, HttpServletResponse response) {
        ArrayList<Task> tasks = projectService.getProjectTasks(task.getProjIndex());
        StringBuilder array = new StringBuilder();
        for(Task t : tasks) {
            String arr = "{\"taskName\":\"" + t.getTaskName() + "\", \"taskId\":\"" + t.getTaskId() + "\", \"OwnerName\":\"" + t.getTaskName() + "\", \"createTime\":\"" + t.getCreatTime() + "\",  \"description\":\"" + t.getDescription() + "\"},";
            array.append(arr);
        }

        String result = "{\"project\": [" + array.substring(0, array.length() - 1) + "] }";

        response.setContentType("application/json;charset=utf-8");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        };
        return "/index";
    }

    @RequestMapping(value = "team/delete", method = RequestMethod.DELETE)
    public void delProjTeamById(ProjectGroup projectGroup, HttpServletResponse response) {
        String result = "{\"result\":\"error\"}";

        if(projectService.delProjTeam(projectGroup)){
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

    @RequestMapping(value = "/info/empl", method = RequestMethod.POST)
    public String getProjEmplById(Project project,HttpServletRequest request) {
        request.setAttribute("ProjEmplById",projectService.getProject(project.getOwnerld()));
        return "/editProjEmployee";
    }

    @RequestMapping(value = "/info/empl", method = RequestMethod.POST)
    public void updateProjEmplById(Project project, HttpServletRequest request) {
        if(projectService.updateProjEmplById(project)){
            project = projectService.getProject((project.getOwnerld()));
            request.setAttribute("ProjEmplById", project);
            // return "redirect:/updateTask";
        }else{
            // return "/error";
        }
    }

    @RequestMapping(value = "info/empl/delgroup",method = RequestMethod.DELETE)
    public void delProjEmplByGroup(Project project, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if(projectService.delProjEmplByGroup(project)){
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

    @RequestMapping(value = "info/empl/delgroup",method = RequestMethod.DELETE)
    public void delProjEmplById(Project project, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if(projectService.delProjEmplById(project)){
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
}
