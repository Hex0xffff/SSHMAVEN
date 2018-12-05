package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue
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

    @Column(name="SalaryModule")
    private int SalaryModule;

    @Column(name="ProjectModule")
    private int ProjectModule;

    @Column(name="TrainingModule")
    private int TrainingModule;

    @Column(name="EmployeeModule")
    private int EmployeeModule;

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

    public int getSalaryModule() {
        return SalaryModule;
    }

    public void setSalaryModule(int salaryModule) {
        SalaryModule = salaryModule;
    }

    public int getProjectModule() {
        return ProjectModule;
    }

    public void setProjectModule(int projectModule) {
        ProjectModule = projectModule;
    }

    public int getTrainingModule() {
        return TrainingModule;
    }

    public void setTrainingModule(int trainingModule) {
        TrainingModule = trainingModule;
    }

    public int getEmployeeModule() {
        return EmployeeModule;
    }

    public void setEmployeeModule(int employeeModule) {
        EmployeeModule = employeeModule;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
