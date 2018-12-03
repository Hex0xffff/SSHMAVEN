package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="SalaryMoudle")
    private boolean SalaryMoudle;

    @Column(name="ProjectMoudle")
    private boolean ProjectMoudle;

    @Column(name="TrainingMoudle")
    private boolean TrainingMoudle;

    @Column(name="EmployeeMoudle")
    private boolean EmployeeMoudle;

    @Column(name="isAdmin")
    private boolean isAdmin;

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

    public boolean isSalaryMoudle() {
        return SalaryMoudle;
    }

    public void setSalaryMoudle(boolean salaryMoudle) {
        SalaryMoudle = salaryMoudle;
    }

    public boolean isProjectMoudle() {
        return ProjectMoudle;
    }

    public void setProjectMoudle(boolean projectMoudle) {
        ProjectMoudle = projectMoudle;
    }

    public boolean isTrainingMoudle() {
        return TrainingMoudle;
    }

    public void setTrainingMoudle(boolean trainingMoudle) {
        TrainingMoudle = trainingMoudle;
    }

    public boolean isEmployeeMoudle() {
        return EmployeeMoudle;
    }

    public void setEmployeeMoudle(boolean employeeMoudle) {
        EmployeeMoudle = employeeMoudle;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
