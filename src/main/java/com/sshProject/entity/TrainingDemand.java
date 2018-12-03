package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="TrainingDemand")
public class TrainingDemand {
    @Column(name="demandName")
    private String demandName;

    @Column(name="employeeId")
    private int employeeId;

    @Column(name="description")
    private String description;

    @Column(name="demandStatus")
    private boolean demandStatus;

    @Column(name="demandIndex")
    private int demandIndex;

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(boolean demandStatus) {
        this.demandStatus = demandStatus;
    }

    public int getDemandIndex() {
        return demandIndex;
    }

    public void setDemandIndex(int demandIndex) {
        this.demandIndex = demandIndex;
    }
}
