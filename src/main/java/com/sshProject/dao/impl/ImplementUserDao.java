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
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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

    public boolean loginEmail(String email,String password)
    {
        String sql="from User u where u.email=? and u.password=?";
        Query query=sessionFactory.getCurrentSession().createQuery(sql);
        query.setString(0,email);
        query.setString(1,password);
        return query.uniqueResult()!=null;
    }

    public boolean updatePassword(int userId, String oldVal, String newVal)
    {
        String sql1="from User u where u.userId=? and u.password=?";
        Query query1=sessionFactory.getCurrentSession().createQuery(sql1);
        query1.setString(0,String.valueOf(userId));
        query1.setString(1,oldVal);
        if(query1.uniqueResult()!=null)
        {
            String sql2="update User u set u.password=? where u.userId=?";
            Query query2=sessionFactory.getCurrentSession().createQuery(sql2);
            query2.setString(0,newVal);
            query2.setString(1,String.valueOf(userId));
            return (query2.executeUpdate()>0);
        }
        else
        {
            return false;
        }
    }

    public boolean deleteUser(int userId)
    {
        String hql="delete User u where u.userId=?";
        Query   query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,String.valueOf(userId));
        return (query.executeUpdate()>0);
    }

  //  public boolean checkAuthorization(String username)

}
