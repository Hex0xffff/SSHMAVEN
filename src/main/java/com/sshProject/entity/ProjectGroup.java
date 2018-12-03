package com.sshProject.entity;
import javax.persistence.*;

@Entity
@Table(name="ProectGroup")
public class ProjectGroup {
    @Column(name="projIndex")
    private int projIndex;

    @Column(name="projTeam")
    private int projTeam;

    public int getProjIndex() {
        return projIndex;
    }

    public void setProjIndex(int projIndex) {
        this.projIndex = projIndex;
    }

    public int getProjTeam() {
        return projTeam;
    }

    public void setProjTeam(int projTeam) {
        this.projTeam = projTeam;
    }
}
