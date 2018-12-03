package com.sshProject.dao.impl;
import com.sshProject.dao.UserDao;
import com.sshProject.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;

@Repository
@Transactional
public class ImplementUserDao implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public boolean loginUsername(String username,String password){
        String sql="from User u where u.username = ? and u.password = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setString(0, username);
        query.setString(1,password);
        return query.uniqueResult()!=null;
    }

    public boolean loginEmail(String email,String password){
        String sql="";
    }
}
