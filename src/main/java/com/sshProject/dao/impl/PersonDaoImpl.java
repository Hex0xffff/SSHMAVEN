package com.sshProject.dao.impl;

import com.sshProject.dao.PersonDao;
import com.sshProject.entity.Person;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;


@Repository
@Transactional
public class PersonDaoImpl implements PersonDao{
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Person getPerson(String id) {
        String hql = "from Person p where p.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);
        return (Person)query.uniqueResult();
    }

    public List<Person> getAllPerson() {
        String hql = "from Person";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public boolean addPerson(Person person) {
        try{
            sessionFactory.getCurrentSession().save(person);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean delPerson(String id) {
        String hql = "delete Person p where p.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);
        return (query.executeUpdate() > 0);
    }

    public boolean updatePerson(Person person) {
        String hql = "update Person p set p.name = ? where p.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, person.getName());
        query.setString(1, String.valueOf(person.getId()));
        return (query.executeUpdate() > 0);
    }
}