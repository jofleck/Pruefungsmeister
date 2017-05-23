package de.fhdortmund.swt2.pruefungsmeister.Controller;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jonas on 22.05.17.
 */
public class Knot {
    private List<MapField> mapFields;
    private List<Edge> edges;
    private Player owner;

    public Knot() {
        mapFields = new LinkedList<MapField>();
        edges = new LinkedList<Edge>();
    }

    public void addMapField(MapField mapField) {
        mapFields.add(mapField);
        mapField.addKnot(this);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        edge.addKnot(this);
    }

    public List<MapField> getMapFields() {
        return mapFields;
    }

    public void setMapFields(List<MapField> mapFields) {
        this.mapFields = mapFields;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
