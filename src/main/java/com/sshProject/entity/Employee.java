package com.sshProject.entity;
import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @Column(name="userId")
    private int userId;

    @Column(name="employeeIndex")
    private int employeeIndex;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="gender")
    private String gender;

    @Column(name="identicalNumber")
    private String identicalNumber;

    @Column(name="status")
    private String status;

    @Column(name="phone")
    private String phone;


    public int getEmployeeIndex() {
        return employeeIndex;
    }

    public void setEmployeeIndex(int employeeIndex) {
        this.employeeIndex = employeeIndex;
    }

    public String getIdenticalNumber() {
        return identicalNumber;
    }

    public void setIdenticalNumber(String identicalNumber) {
        this.identicalNumber = identicalNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
