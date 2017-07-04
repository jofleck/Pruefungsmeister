package de.fhdortmund.swt2.pruefungsmeister.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Observable;

/**
 * Created by jonas on 22.05.17.
 */
@Entity
@Table(name = "Player")
public class Player extends Observable {
    private static int count;
    @Id
    private int id;
    private String name;
    private LocalDateTime lastUpdate;

    private int energydrinks;
    private int fastfood;
    private int extrapoints;
    private int knowhow;
    private int technology;
    private int exams;


    public Player(String name) {
        this.name = name;
        this.id = count++;

        energydrinks = 2;
        fastfood = 2;
        technology = 1;
        knowhow = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergydrinks() {
        return energydrinks;
    }

    public void setEnergydrinks(int energydrinks) {
        this.energydrinks = energydrinks;
    }

    public int getFastfood() {
        return fastfood;
    }

    public void setFastfood(int fastfood) {
        this.fastfood = fastfood;
    }

    public int getExtrapoints() {
        return extrapoints;
    }

    public void setExtrapoints(int extrapoints) {
        this.extrapoints = extrapoints;
    }

    public int getKnowhow() {
        return knowhow;
    }

    public void setKnowhow(int knowhow) {
        this.knowhow = knowhow;
    }

    public int getTechnology() {
        return technology;
    }

    public void setTechnology(int technology) {
        this.technology = technology;
    }

    public int getExams() {
        return exams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setExams(int exams) {
        this.exams = exams;
        setChanged();
        notifyObservers();
    }
}
