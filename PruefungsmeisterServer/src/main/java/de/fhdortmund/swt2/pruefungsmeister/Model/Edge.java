package de.fhdortmund.swt2.pruefungsmeister.Model;

import com.google.gson.annotations.Expose;

import javax.persistence.Transient;

/**
 * Created by jonas on 22.05.17.
 */
public class Edge {

    private transient Knot[] knots;
    @Expose
    private Player owner;

    public Edge() {
        knots = new Knot[2];
    }

    public void addKnot(Knot k) {
        assert k != null;

        if(knots[0] == null) {
            knots[0] = k;
        } else {
            assert knots[1] == null;
            knots[1] = k;
        }
    }

    public Knot[] getKnots() {
        return knots;
    }

    public void setKnots(Knot[] knots) {
        this.knots = knots;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
