package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Column(name="userId")
    private int userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="SalaryMoudle")
    private int SalaryMoudle;

    @Column(name="ProjectMoudle")
    private int ProjectMoudle;

    @Column(name="TrainingMoudle")
    private int TrainingMoudle;

    @Column(name="EmployeeMoudle")
    private int EmployeeMoudle;

    @Column(name="isAdmin")
    private int isAdmin;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalaryMoudle() {
        return SalaryMoudle;
    }

    public void setSalaryMoudle(int salaryMoudle) {
        SalaryMoudle = salaryMoudle;
    }

    public int getProjectMoudle() {
        return ProjectMoudle;
    }

    public void setProjectMoudle(int projectMoudle) {
        ProjectMoudle = projectMoudle;
    }

    public int getTrainingMoudle() {
        return TrainingMoudle;
    }

    public void setTrainingMoudle(int trainingMoudle) {
        TrainingMoudle = trainingMoudle;
    }

    public int getEmployeeMoudle() {
        return EmployeeMoudle;
    }

    public void setEmployeeMoudle(int employeeMoudle) {
        EmployeeMoudle = employeeMoudle;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
