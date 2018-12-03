package com.sshProject.dao.impl;


import com.sshProject.dao.ProjectDao;
import com.sshProject.entity.Project;
import com.sshProject.entity.ProjectGroup;
import com.sshProject.entity.Task;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public class ImplementProjectDao implements ProjectDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory=sessionFactory;
    }
    public  boolean addProject(Project project)
    {
      //不会
    }
    public  boolean updateProject(Project project)
    {
        String hql="update Project p set p.projName=?,p.ownerId=?,createTime=?,endTime=?,projStatus=?;description=? where projIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,project.getProjName());
        query.setString(1,String.valueOf(project.getOwnerld()));
        query.setString(2,String.valueOf(project.getCreatTime()));
        query.setString(3,String.valueOf(project.getEndTime()));
        query.setString(4,String.valueOf(project.getProjStatus()));
        query.setString(5,project.getDescription());
        query.setString(6,String.valueOf(project.getProjIndex()));
        return (query.executeUpdate()>0);

    }
    public  boolean deleteProject(Project project)
    {
        String hql="delete Project p where p.projIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(project.getProjIndex()));
        return  (query.executeUpdate()>0);
    }
    public ProjectGroup getProjectTeam(int projectIndex)
    {
        String hql="from ProjectGroup p where p.projIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(projectIndex));
        return (ProjectGroup)query.uniqueResult();
    }
    public ArrayList<Task> getProjectTasks (int projectIndex)
    {
        String hql="from Task t where t.projIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(projectIndex));
        return (ArrayList<Task>) query.list();
    }
    public boolean addTask(Task task)
    {

    }
    public boolean updateTask(Task task)
    {
        String hql="update Task t set t.taskName=?,t.ownerId=?,t.createTime=?.t.projIndex=?,t.description=? where t.taskId=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,task.getTaskName());
        query.setString(1,String.valueOf(task.getTaskId()));
        query.setString(2,String.valueOf(task.getCreatTime()));
        query.setString(3,String.valueOf(task.getProjIndex()));
        query.setString(4,task.getDescription());
        query.setString(5,String.valueOf(task.getTaskId()));
        return (query.executeUpdate()>0);
    }
    public  boolean deleteTask(int taskIndex)
    {
        String hql="delete Task t where t.taskId";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(taskIndex));
        return (query.executeUpdate()>0);
    }
    public Task getTask(int taskIndex)
    {
        String hql="form Task t where t.taskId=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(taskIndex));
        return (Task)query.uniqueResult();
    }
    public Project getProject(int projectIndex)
    {
        String hql="from Project p where p.projIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(projectIndex);
        return (Project)query.uniqueResult();
    }
    public ArrayList<Project> getAllProjects()
    {
        String hql="form Project";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return (ArrayList<Project>) query.list();
    }


}
