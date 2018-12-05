package com.sshProject.entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Project")
public class Project {

    @Column(name="projName")
    private String projName;

    @Id
    @GeneratedValue
    @Column(name="projIndex")
    private int projIndex;

    @Column(name="ownerld")
    private int ownerld;

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

    public int getOwnerld() {
        return ownerld;
    }

    public void setOwnerld(int ownerld) {
        this.ownerld = ownerld;
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
}
