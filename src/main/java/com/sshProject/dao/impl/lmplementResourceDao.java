package com.sshProject.dao.impl;
import com.sshProject.entity.TrainingResource;
import com.sshProject.dao.ResourceDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@Transactional
public class lmplementResourceDao implements ResourceDao {
    @Autowired
    private SessionFactory sessionFactory;
    public  void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory=sessionFactory;
    }
    public  boolean addResource(TrainingResource resource){
        try{
            sessionFactory.getCurrentSession().save(resource);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    public  boolean deleteResource(int trainerId){
        String hql="delete TrainingResource td where td.trainerId=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(trainerId));
        return  (query.executeUpdate()>0);
    }
    public boolean updateResource(TrainingResource resource){
        String hql="Update TrainingResource tr set tr.className=?,tr.resourceStatus=?,tr.description=? where trainerId=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(resource.getClassName()));
        query.setString(1,String.valueOf(resource.getResourceStatus()));
        query.setString(2,String.valueOf(resource.getDescription()));
        query.setString(3,String.valueOf(resource.getTrainerId()));
        return (query.executeUpdate()>0);
    }
   public ArrayList<TrainingResource> getallResource(){
       String hql="from TrainingResource";
       Query query=sessionFactory.getCurrentSession().createQuery(hql);
       return  (ArrayList<TrainingResource>)query.list();
   }
}
