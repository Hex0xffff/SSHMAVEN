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
    private boolean resourceStatus;

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

    public boolean isResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(boolean resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
