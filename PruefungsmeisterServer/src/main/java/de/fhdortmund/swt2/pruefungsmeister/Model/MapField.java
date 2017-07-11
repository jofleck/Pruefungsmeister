package de.fhdortmund.swt2.pruefungsmeister.Model;

import com.google.gson.annotations.Expose;

import javax.persistence.Transient;

/**
 * Created by jonas on 22.05.17.
 */
public class MapField {
    @Expose
    private Resource resource;
    private  Knot[] knots;
    private int knotCount;
    @Expose
    private int randomValue;

    public MapField() {
        knots = new Knot[6];
    }

    public Knot[] getKnots() {
        return knots;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void addKnot(Knot k) {
        assert k != null;
        assert knotCount < 6;
        knots[knotCount++] = k;
    }

    public int getRandomValue() {
        return randomValue;
    }

    public void setRandomValue(int randomValue) {
        this.randomValue = randomValue;
    }
}
