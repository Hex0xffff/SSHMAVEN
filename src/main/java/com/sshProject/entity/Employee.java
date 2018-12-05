package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name="userId")
    private int userId;

    @Column(name="employeeIndex")
    private int employeeIndex;

    @Column(name="identicalNumber")
    private String identicalNumber;

    @Column(name="realName")
    private String realName;

    @Column(name="address")
    private String address;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
}
