package com.sshProject.entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Task")
public class Task {
    @Column(name="taskName")
    private String taskName;

    @Id
    @GeneratedValue
    @Column(name="taskId")
    private int taskId;

    @Column(name="ownerId")
    private int ownerId;

    @Column(name="creatTime")
    private Date creatTime;

    @Column(name="projIndex")
    private int projIndex;

    @Column(name="description")
    private String description;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public int getProjIndex() {
        return projIndex;
    }

    public void setProjIndex(int projIndex) {
        this.projIndex = projIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
