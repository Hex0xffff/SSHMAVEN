package com.sshProject.entity;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="TrainingGroup")
public class TrainingGroup {
    @Column(name="trainingIndex")
    private int trainingIndex;

    @Column(name="trainingTeam")
    private ArrayList<Integer> trainingTeam;

    public int getTrainingIndex() {
        return trainingIndex;
    }

    public void setTrainingIndex(int trainingIndex) {
        this.trainingIndex = trainingIndex;
    }

    public ArrayList<Integer> getTrainingTeam() {
        return trainingTeam;
    }

    public void setTrainingTeam(ArrayList<Integer> trainingTeam) {
        this.trainingTeam = trainingTeam;
    }
}
