package com.sshProject.dao.impl;

import com.sshProject.dao.TrainingDao;
import com.sshProject.entity.Employee;
import com.sshProject.entity.Training;
import com.sshProject.entity.TrainingGroup;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public class ImplementTrainingDao implements TrainingDao
{
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SessionFactory sessionFactory;
    public  void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory=sessionFactory;
    }
    public boolean addTraining(Training training)
    {
        try{
            sessionFactory.getCurrentSession().save(training);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    public boolean updateTraining(Training training)
    {
        String hql="update Training t set t.trainingName=?,t.trainingOwnerId=?,t.createTime=?,t.endTime=?,t.description where t.trainingIndex=? ";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,training.getTrainingName());
        query.setString(1,String.valueOf(training.getTrainingOwnerId()));
        query.setString(2,String.valueOf(training.getCreatTime()));
        query.setString(3,String.valueOf(training.getEndTime()));
        query.setString(4,training.getDescription());
        query.setString(5,String.valueOf(training.getTrainingIndex()));
        return (query.executeUpdate()>0);
    }
    public boolean deleteTraining(int trainingIndex)

    {
        String hql="delete Training t where t.traningIndex=? ";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(trainingIndex));
        return (query.executeUpdate()>0);
    }
    public ArrayList<TrainingGroup> getTrainingGroup(int trainingIndex)
    {
        String hql="form TrainingGroup t where t.trainingIndex=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(trainingIndex));
        return (ArrayList<TrainingGroup>)query.list();
    }
    public ArrayList<Training> getTraining(int employeeId)
    {
        String hql="form Training t where t.trainingOwnerId=:?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(employeeId));
        return (ArrayList<Training>)query.list();

    }
    public ArrayList<Training> getAllTrainings()
    {
        String hql="from Training";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return (ArrayList<Training>)query.list();
    }
    public ArrayList<TrainingGroup> getAllTrainingGroups()
    {
        String hql="from TrainingGroup";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        return  (ArrayList<TrainingGroup>)query.list();
    }

}
