package de.fhdortmund.swt2.pruefungsmeister.client.Model;

/**
 * Created by jonas on 22.05.17.
 */
public class Map {

    private MapField[] fields;
    private Knot[] knots;
    private Edge[] edges;

    public Map() {


    }

    public MapField[] getFields() {
        return fields;
    }

    public Knot[] getKnots() {
        return knots;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setFields(MapField[] fields) {
        this.fields = fields;
    }

    public void setKnots(Knot[] knots) {
        this.knots = knots;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }
}
