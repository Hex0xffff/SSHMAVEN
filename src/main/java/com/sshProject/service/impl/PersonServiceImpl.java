package com.sshProject.service.impl;

import com.sshProject.dao.PersonDao;
import com.sshProject.entity.Person;
import com.sshProject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonDao personDao;

    public Person getPerson(String id) {
        return personDao.getPerson(id);
    }

    public List<Person> getAllPerson() {
        List<Person> list = personDao.getAllPerson();
        return list;
    }


    public boolean addPerson(Person person) {
        return personDao.addPerson(person);
    }

    public boolean delPerson(String id) {
        return personDao.delPerson(id);
    }

    public boolean updatePerson(Person person) {
        return personDao.updatePerson(person);
    }
}