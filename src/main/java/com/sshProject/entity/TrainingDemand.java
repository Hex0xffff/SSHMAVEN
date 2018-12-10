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
    private int demandStatus;

    @Id
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

    public int getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(int demandStatus) {
        this.demandStatus = demandStatus;
    }

    public int getDemandIndex() {
        return demandIndex;
    }

    public void setDemandIndex(int demandIndex) {
        this.demandIndex = demandIndex;
    }
}
