package com.sshProject.entity;
import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name="Training")
public class Training {
    @Column(name="trainingName")
    private String trainingName;

    @Column(name="trainingOwnerId")
    private int trainingOwnerId;

    @Id
    @Column(name="trainingIndex")
    private int trainingIndex;

    @Column(name="creatTime")
    private Date creatTime;

    @Column(name="endTime")
    private Date endTime;

    @Column(name="description")
    private String description;

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public int getTrainingOwnerId() {
        return trainingOwnerId;
    }

    public void setTrainingOwnerId(int trainingOwnerId) {
        this.trainingOwnerId = trainingOwnerId;
    }

    public int getTrainingIndex() {
        return trainingIndex;
    }

    public void setTrainingIndex(int trainingIndex) {
        this.trainingIndex = trainingIndex;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
