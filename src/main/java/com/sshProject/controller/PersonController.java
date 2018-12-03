package com.sshProject.controller;

import com.sshProject.entity.Person;
import com.sshProject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class PersonController {

    @Autowired
    private PersonService personService;
    @RequestMapping("/getAllPerson")
    public String getAllPerson(HttpServletRequest request){

        request.setAttribute("personList", personService.getAllPerson());

        return "/index";
    }

    @RequestMapping("/getPerson")
    public String getPerson(String id,HttpServletRequest request){

        request.setAttribute("person", personService.getPerson(id));

        return "/editPerson";
    }

    @RequestMapping("/toAddPerson")
    public String toAddPerson(){
        return "/addPerson";
    }

    @RequestMapping("/addPerson")
    public String addPerson(Person person, HttpServletRequest request){

        personService.addPerson(person);

        return "redirect:/getAllPerson";
    }

    @RequestMapping("/delPerson")
    public void delPerson(String id,HttpServletResponse response){

        String result = "{\"result\":\"error\"}";

        if(personService.delPerson(id)){
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

    @RequestMapping("/updatePerson")
    public String updatePerson(Person person,HttpServletRequest request){

        if(personService.updatePerson(person)){
            person = personService.getPerson(String.valueOf(person.getId()));
            request.setAttribute("person", person);
            return "redirect:/getAllPerson";
        }else{
            return "/error";
        }
    }
}