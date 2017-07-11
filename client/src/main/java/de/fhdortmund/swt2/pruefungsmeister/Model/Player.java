package de.fhdortmund.swt2.pruefungsmeister.Model;

import com.corundumstudio.socketio.SocketIOClient;
import com.google.gson.annotations.Expose;
import javafx.fxml.FXML;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @Expose
    private int id;
    @Expose
    private String name;
    private LocalDateTime lastUpdate;

    @Expose
    private int energydrinks;
    @Expose
    private int fastfood;
    @Expose
    private int extrapoints;
    @Expose
    private int knowhow;
    @Expose
    private int technology;
    @Expose
    private int exams;

    private transient SocketIOClient client;

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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Player.count = count;
    }

    public SocketIOClient getClient() {
        return client;
    }

    public void setClient(SocketIOClient client) {
        this.client = client;
    }
}
