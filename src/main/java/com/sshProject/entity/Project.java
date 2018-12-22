package com.sshProject.entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Project")
public class Project {

    @Column(name="projName")
    private String projName;

    @Id
    @Column(name="projIndex")
    private int projIndex;

    @Column(name="employeeld")
    private int employeeld;

    @Column(name="employeeName")
    private String employeeNmae;

    @Column(name="creatTime")
    private Date creatTime;

    @Column(name="endTime")
    private Date endTime;

    @Column(name="projStatus")
    private int projStatus;

    @Column(name="description")
    private String description;

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public int getProjIndex() {
        return projIndex;
    }

    public void setProjIndex(int projIndex) {
        this.projIndex = projIndex;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getProjStatus() {
        return projStatus;
    }

    public void setProjStatus(int projStatus) {
        this.projStatus = projStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeld() {
        return employeeld;
    }

    public void setEmployeeld(int employeeld) {
        this.employeeld = employeeld;
    }

    public String getEmployeeNmae() {
        return employeeNmae;
    }

    public void setEmployeeNmae(String employeeNmae) {
        this.employeeNmae = employeeNmae;
    }
}
