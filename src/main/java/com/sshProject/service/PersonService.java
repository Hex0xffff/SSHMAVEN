package com.sshProject.service;

import com.sshProject.entity.Person;

import java.util.List;



public interface PersonService {

    Person getPerson(String id);

    List<Person> getAllPerson();

    boolean addPerson(Person person);

    boolean delPerson(String id);

    boolean updatePerson(Person person);
}