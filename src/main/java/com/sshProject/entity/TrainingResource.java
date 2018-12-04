package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="TrainingResource")
public class TrainingResource {
    @Column(name="className")
    private String className;

    @Column(name="trainerId")
    private int trainerId;

    @Column(name="resourceStatus")
    private int resourceStatus;

    @Column(name="description")
    private String description;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(int resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
