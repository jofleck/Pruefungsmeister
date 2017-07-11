package de.fhdortmund.swt2.pruefungsmeister.Model;

import com.google.gson.annotations.Expose;

/**
 * Created by jonas on 22.05.17.
 */
public class Map {

    @Expose
    private MapField[] fields;
    @Expose
    private Knot[] knots;
    @Expose
    private Edge[] edges;

    public Map() {
        fields = new MapField[19];
        knots = new Knot[54];
        edges = new Edge[72];
        for(int i = 0; i < fields.length; i++) {
            fields[i] = new MapField();
        }

        for(int i = 0; i < knots.length; i++) {
            knots[i] = new Knot();
        }

        for(int i = 0; i < edges.length; i++) {
            edges[i] = new Edge();
        }

        knots[0].addMapField(fields[0]);
        knots[1].addMapField(fields[0]);
        knots[2].addMapField(fields[0]);
        knots[8].addMapField(fields[0]);
        knots[9].addMapField(fields[0]);
        knots[10].addMapField(fields[0]);

        knots[2].addMapField(fields[1]);
        knots[3].addMapField(fields[1]);
        knots[4].addMapField(fields[1]);
        knots[10].addMapField(fields[1]);
        knots[11].addMapField(fields[1]);
        knots[12].addMapField(fields[1]);

        knots[4].addMapField(fields[2]);
        knots[5].addMapField(fields[2]);
        knots[6].addMapField(fields[2]);
        knots[12].addMapField(fields[2]);
        knots[13].addMapField(fields[2]);
        knots[14].addMapField(fields[2]);

        knots[7].addMapField(fields[3]);
        knots[8].addMapField(fields[3]);
        knots[9].addMapField(fields[3]);
        knots[17].addMapField(fields[3]);
        knots[18].addMapField(fields[3]);
        knots[19].addMapField(fields[3]);

        knots[9].addMapField(fields[4]);
        knots[10].addMapField(fields[4]);
        knots[11].addMapField(fields[4]);
        knots[19].addMapField(fields[4]);
        knots[20].addMapField(fields[4]);
        knots[21].addMapField(fields[4]);

        knots[11].addMapField(fields[5]);
        knots[12].addMapField(fields[5]);
        knots[13].addMapField(fields[5]);
        knots[21].addMapField(fields[5]);
        knots[22].addMapField(fields[5]);
        knots[23].addMapField(fields[5]);

        knots[13].addMapField(fields[6]);
        knots[14].addMapField(fields[6]);
        knots[15].addMapField(fields[6]);
        knots[23].addMapField(fields[6]);
        knots[24].addMapField(fields[6]);
        knots[25].addMapField(fields[6]);

        knots[16].addMapField(fields[7]);
        knots[17].addMapField(fields[7]);
        knots[18].addMapField(fields[7]);
        knots[27].addMapField(fields[7]);
        knots[28].addMapField(fields[7]);
        knots[29].addMapField(fields[7]);

        knots[18].addMapField(fields[8]);
        knots[19].addMapField(fields[8]);
        knots[20].addMapField(fields[8]);
        knots[29].addMapField(fields[8]);
        knots[30].addMapField(fields[8]);
        knots[31].addMapField(fields[8]);

        knots[20].addMapField(fields[9]);
        knots[21].addMapField(fields[9]);
        knots[22].addMapField(fields[9]);
        knots[31].addMapField(fields[9]);
        knots[32].addMapField(fields[9]);
        knots[33].addMapField(fields[9]);

        knots[22].addMapField(fields[10]);
        knots[23].addMapField(fields[10]);
        knots[24].addMapField(fields[10]);
        knots[33].addMapField(fields[10]);
        knots[34].addMapField(fields[10]);
        knots[35].addMapField(fields[10]);

        knots[24].addMapField(fields[11]);
        knots[25].addMapField(fields[11]);
        knots[26].addMapField(fields[11]);
        knots[35].addMapField(fields[11]);
        knots[36].addMapField(fields[11]);
        knots[37].addMapField(fields[11]);

        knots[28].addMapField(fields[12]);
        knots[29].addMapField(fields[12]);
        knots[30].addMapField(fields[12]);
        knots[38].addMapField(fields[12]);
        knots[39].addMapField(fields[12]);
        knots[40].addMapField(fields[12]);

        knots[30].addMapField(fields[13]);
        knots[31].addMapField(fields[13]);
        knots[32].addMapField(fields[13]);
        knots[40].addMapField(fields[13]);
        knots[41].addMapField(fields[13]);
        knots[42].addMapField(fields[13]);

        knots[32].addMapField(fields[14]);
        knots[33].addMapField(fields[14]);
        knots[34].addMapField(fields[14]);
        knots[42].addMapField(fields[14]);
        knots[43].addMapField(fields[14]);
        knots[44].addMapField(fields[14]);

        knots[34].addMapField(fields[15]);
        knots[35].addMapField(fields[15]);
        knots[36].addMapField(fields[15]);
        knots[44].addMapField(fields[15]);
        knots[45].addMapField(fields[15]);
        knots[46].addMapField(fields[15]);

        knots[39].addMapField(fields[16]);
        knots[40].addMapField(fields[16]);
        knots[41].addMapField(fields[16]);
        knots[47].addMapField(fields[16]);
        knots[48].addMapField(fields[16]);
        knots[49].addMapField(fields[16]);

        knots[41].addMapField(fields[17]);
        knots[42].addMapField(fields[17]);
        knots[43].addMapField(fields[17]);
        knots[49].addMapField(fields[17]);
        knots[50].addMapField(fields[17]);
        knots[51].addMapField(fields[17]);

        knots[43].addMapField(fields[18]);
        knots[44].addMapField(fields[18]);
        knots[45].addMapField(fields[18]);
        knots[51].addMapField(fields[18]);
        knots[52].addMapField(fields[18]);
        knots[53].addMapField(fields[18]);

        knots[0].addEdge(edges[0]);
        knots[0].addEdge(edges[6]);

        knots[1].addEdge(edges[0]);
        knots[1].addEdge(edges[1]);

        knots[2].addEdge(edges[1]);
        knots[2].addEdge(edges[2]);
        knots[2].addEdge(edges[7]);

        knots[3].addEdge(edges[2]);
        knots[3].addEdge(edges[3]);

        knots[4].addEdge(edges[3]);
        knots[4].addEdge(edges[4]);
        knots[4].addEdge(edges[8]);

        knots[5].addEdge(edges[4]);
        knots[5].addEdge(edges[5]);

        knots[6].addEdge(edges[5]);
        knots[6].addEdge(edges[9]);

        knots[7].addEdge(edges[10]);
        knots[7].addEdge(edges[18]);

        knots[8].addEdge(edges[6]);
        knots[8].addEdge(edges[10]);
        knots[8].addEdge(edges[11]);

        knots[9].addEdge(edges[11]);
        knots[9].addEdge(edges[12]);
        knots[9].addEdge(edges[19]);

        knots[10].addEdge(edges[7]);
        knots[10].addEdge(edges[12]);
        knots[10].addEdge(edges[13]);

        knots[11].addEdge(edges[13]);
        knots[11].addEdge(edges[14]);
        knots[11].addEdge(edges[20]);

        knots[12].addEdge(edges[8]);
        knots[12].addEdge(edges[14]);
        knots[12].addEdge(edges[15]);

        knots[13].addEdge(edges[15]);
        knots[13].addEdge(edges[16]);
        knots[13].addEdge(edges[21]);

        knots[14].addEdge(edges[9]);
        knots[14].addEdge(edges[16]);
        knots[14].addEdge(edges[17]);

        knots[15].addEdge(edges[17]);
        knots[15].addEdge(edges[22]);

        knots[16].addEdge(edges[23]);
        knots[16].addEdge(edges[33]);

        knots[17].addEdge(edges[18]);
        knots[17].addEdge(edges[23]);
        knots[17].addEdge(edges[24]);

        knots[18].addEdge(edges[24]);
        knots[18].addEdge(edges[25]);
        knots[18].addEdge(edges[34]);

        knots[19].addEdge(edges[19]);
        knots[19].addEdge(edges[25]);
        knots[19].addEdge(edges[26]);

        knots[20].addEdge(edges[26]);
        knots[20].addEdge(edges[27]);
        knots[20].addEdge(edges[35]);

        knots[21].addEdge(edges[20]);
        knots[21].addEdge(edges[27]);
        knots[21].addEdge(edges[28]);

        knots[22].addEdge(edges[28]);
        knots[22].addEdge(edges[29]);
        knots[22].addEdge(edges[36]);

        knots[23].addEdge(edges[21]);
        knots[23].addEdge(edges[29]);
        knots[23].addEdge(edges[30]);

        knots[24].addEdge(edges[30]);
        knots[24].addEdge(edges[31]);
        knots[24].addEdge(edges[37]);

        knots[25].addEdge(edges[22]);
        knots[25].addEdge(edges[31]);
        knots[25].addEdge(edges[32]);

        knots[26].addEdge(edges[32]);
        knots[26].addEdge(edges[38]);

        knots[27].addEdge(edges[33]);
        knots[27].addEdge(edges[39]);

        knots[28].addEdge(edges[39]);
        knots[28].addEdge(edges[40]);
        knots[28].addEdge(edges[49]);

        knots[29].addEdge(edges[34]);
        knots[29].addEdge(edges[40]);
        knots[29].addEdge(edges[41]);


        //------

        knots[30].addEdge(edges[41]);
        knots[30].addEdge(edges[42]);
        knots[30].addEdge(edges[50]);

        knots[31].addEdge(edges[35]);
        knots[31].addEdge(edges[42]);
        knots[31].addEdge(edges[43]);

        knots[32].addEdge(edges[43]);
        knots[32].addEdge(edges[44]);
        knots[32].addEdge(edges[51]);

        knots[33].addEdge(edges[36]);
        knots[33].addEdge(edges[44]);
        knots[33].addEdge(edges[45]);

        knots[34].addEdge(edges[45]);
        knots[34].addEdge(edges[46]);
        knots[34].addEdge(edges[52]);

        knots[35].addEdge(edges[37]);
        knots[35].addEdge(edges[46]);
        knots[35].addEdge(edges[47]);

        knots[36].addEdge(edges[47]);
        knots[36].addEdge(edges[48]);
        knots[36].addEdge(edges[53]);

        knots[37].addEdge(edges[38]);
        knots[37].addEdge(edges[48]);

        knots[38].addEdge(edges[49]);
        knots[38].addEdge(edges[54]);

        knots[39].addEdge(edges[54]);
        knots[39].addEdge(edges[55]);
        knots[39].addEdge(edges[62]);

        knots[40].addEdge(edges[50]);
        knots[40].addEdge(edges[55]);
        knots[40].addEdge(edges[56]);

        knots[41].addEdge(edges[56]);
        knots[41].addEdge(edges[57]);
        knots[41].addEdge(edges[63]);

        knots[42].addEdge(edges[51]);
        knots[42].addEdge(edges[57]);
        knots[42].addEdge(edges[58]);

        knots[43].addEdge(edges[58]);
        knots[43].addEdge(edges[59]);
        knots[43].addEdge(edges[64]);

        knots[44].addEdge(edges[52]);
        knots[44].addEdge(edges[59]);
        knots[44].addEdge(edges[60]);

        knots[45].addEdge(edges[60]);
        knots[45].addEdge(edges[61]);
        knots[45].addEdge(edges[65]);

        knots[46].addEdge(edges[53]);
        knots[46].addEdge(edges[61]);

        knots[47].addEdge(edges[62]);
        knots[47].addEdge(edges[66]);

        knots[48].addEdge(edges[66]);
        knots[48].addEdge(edges[67]);

        knots[49].addEdge(edges[63]);
        knots[49].addEdge(edges[67]);
        knots[49].addEdge(edges[68]);

        knots[50].addEdge(edges[68]);
        knots[50].addEdge(edges[69]);

        knots[51].addEdge(edges[64]);
        knots[51].addEdge(edges[69]);
        knots[51].addEdge(edges[70]);

        knots[52].addEdge(edges[70]);
        knots[52].addEdge(edges[71]);

        knots[53].addEdge(edges[65]);
        knots[53].addEdge(edges[71]);


        //TODO Ressourcen besser verteilen
        for(int i = 0; i < fields.length; i++) {
            MapField field = fields[i];
            int value = (int) (Math.random() * 5);
            Resource resource = null;
            switch (value) {
                case 0:
                    resource = Resource.ENERGYDRINK;
                    break;
                case 1:
                    resource = Resource.FASTFOOD;
                    break;
                case 2:
                    resource = Resource.EXTRA_POINTS;
                    break;
                case 3:
                    resource = Resource.KNOW_HOW;
                    break;
                case 4:
                    resource = Resource.TECHNOLOGY;
                    break;
                default:
                    break;
            }

            field.setResource(resource);
            field.setRandomValue((int)((Math.random() * 11) + 2));
            System.out.format("Feld %d hat die Ressource %s und den Zufallswert %d%n", i, resource.toString(), field.getRandomValue());
        }

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
}
