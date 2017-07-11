package de.fhdortmund.swt2.pruefungsmeister.client.Controller;

import de.fhdortmund.swt2.pruefungsmeister.client.View.TradeDialog;
import com.google.gson.Gson;
import de.fhdortmund.swt2.pruefungsmeister.client.Model.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URISyntaxException;


/**
 * Created by jonas on 07.07.17.
 */
public class MainViewController {
    // Label
    @FXML Label lblTechnology;
    @FXML Label lblEnergydrinks;
    @FXML Label lblKnowHow;
    @FXML Label lblFastfood;
    @FXML Label lblExtrapoints;
    //Textfeld
    @FXML
    TextArea output;
    //Button
    @FXML
    Button btnFinishTurn;
    @FXML
    Button btnTrade;

    //Kontakte
    @FXML Circle c0;
    @FXML Circle c1;
    @FXML Circle c2;
    @FXML Circle c3;
    @FXML Circle c4;
    @FXML Circle c5;
    @FXML Circle c6;
    @FXML Circle c7;
    @FXML Circle c8;
    @FXML Circle c9;
    @FXML Circle c10;
    @FXML Circle c11;
    @FXML Circle c12;
    @FXML Circle c13;
    @FXML Circle c14;
    @FXML Circle c15;
    @FXML Circle c16;
    @FXML Circle c17;
    @FXML Circle c18;
    @FXML Circle c19;
    @FXML Circle c20;
    @FXML Circle c21;
    @FXML Circle c22;
    @FXML Circle c23;
    @FXML Circle c24;
    @FXML Circle c25;
    @FXML Circle c26;
    @FXML Circle c27;
    @FXML Circle c28;
    @FXML Circle c29;
    @FXML Circle c30;
    @FXML Circle c31;
    @FXML Circle c32;
    @FXML Circle c33;
    @FXML Circle c34;
    @FXML Circle c35;
    @FXML Circle c36;
    @FXML Circle c37;
    @FXML Circle c38;
    @FXML Circle c39;
    @FXML Circle c40;
    @FXML Circle c41;
    @FXML Circle c42;
    @FXML Circle c43;
    @FXML Circle c44;
    @FXML Circle c45;
    @FXML Circle c46;
    @FXML Circle c47;
    @FXML Circle c48;
    @FXML Circle c49;
    @FXML Circle c50;
    @FXML Circle c51;
    @FXML Circle c52;
    @FXML Circle c53;
    @FXML Circle c54;
    @FXML Circle c55;
    @FXML Circle c56;
    @FXML Circle c57;
    @FXML Circle c58;
    @FXML Circle c59;
    @FXML Circle c60;
    @FXML Circle c61;
    @FXML Circle c62;
    @FXML Circle c63;
    @FXML Circle c64;
    @FXML Circle c65;
    @FXML Circle c66;
    @FXML Circle c67;
    @FXML Circle c68;
    @FXML Circle c69;
    @FXML Circle c70;
    @FXML Circle c71;
    //Lerngruppe

    @FXML
    Rectangle r0;
    @FXML Rectangle r1;
    @FXML Rectangle r2;
    @FXML Rectangle r3;
    @FXML Rectangle r4;
    @FXML Rectangle r5;
    @FXML Rectangle r6;
    @FXML Rectangle r7;
    @FXML Rectangle r8;
    @FXML Rectangle r9;
    @FXML Rectangle r10;
    @FXML Rectangle r11;
    @FXML Rectangle r12;
    @FXML Rectangle r13;
    @FXML Rectangle r14;
    @FXML Rectangle r15;
    @FXML Rectangle r16;
    @FXML Rectangle r17;
    @FXML Rectangle r18;
    @FXML Rectangle r19;
    @FXML Rectangle r20;
    @FXML Rectangle r21;
    @FXML Rectangle r22;
    @FXML Rectangle r23;
    @FXML Rectangle r24;
    @FXML Rectangle r25;
    @FXML Rectangle r26;
    @FXML Rectangle r27;
    @FXML Rectangle r28;
    @FXML Rectangle r29;
    @FXML Rectangle r30;
    @FXML Rectangle r31;
    @FXML Rectangle r32;
    @FXML Rectangle r33;
    @FXML Rectangle r34;
    @FXML Rectangle r35;
    @FXML Rectangle r36;
    @FXML Rectangle r37;
    @FXML Rectangle r38;
    @FXML Rectangle r39;
    @FXML Rectangle r40;
    @FXML Rectangle r41;
    @FXML Rectangle r42;
    @FXML Rectangle r43;
    @FXML Rectangle r44;
    @FXML Rectangle r45;
    @FXML Rectangle r46;
    @FXML Rectangle r47;
    @FXML Rectangle r48;
    @FXML Rectangle r49;
    @FXML Rectangle r50;
    @FXML Rectangle r51;
    @FXML Rectangle r52;
    @FXML Rectangle r53;

    //Spielfeld
    @FXML  Label l0;
    @FXML  Label l1;
    @FXML  Label l2;
    @FXML  Label l3;
    @FXML  Label l4;
    @FXML  Label l5;
    @FXML  Label l6;
    @FXML  Label l7;
    @FXML  Label l8;
    @FXML  Label l9;
    @FXML  Label l10;
    @FXML  Label l11;
    @FXML  Label l12;
    @FXML  Label l13;
    @FXML  Label l14;
    @FXML  Label l15;
    @FXML  Label l16;
    @FXML  Label l17;
    @FXML  Label l18;

    Circle[] circles = new Circle[72];
    Rectangle[] rectangles = new Rectangle[54];
    Label[] labels=new Label[19];

    Socket socket;

    private void initSocketIO() {
        try {
            socket = IO.socket("http://localhost:9092");
            socket.on("message", objects -> output.setText((String)objects[0])).on("mapUpdate", objects -> {
                String mapJson = (String) objects[0];
                System.out.println(mapJson);
                Gson gson = new Gson();
                Map map = gson.fromJson(mapJson, Map.class);
                Platform.runLater(() -> updateMap(map));
            }).on("playerUpdate", objects -> {
                String playerJson = (String) objects[0];
                System.out.println(playerJson);
                Gson gson = new Gson();
                Player player = gson.fromJson(playerJson, Player.class);
                Platform.runLater(() -> updatePlayer(player));


                    });

                    socket.connect();
            socket.emit("join", "Hans");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void updatePlayer(Player p) {
        lblEnergydrinks.setText(p.getEnergydrinks() + "");
        lblExtrapoints.setText(p.getExtrapoints() + "");
        lblFastfood.setText(p.getFastfood() + "");
        lblKnowHow.setText(p.getKnowhow() + "");
        lblTechnology.setText(p.getTechnology() + "");
    }

    private void updateMap(Map map) {
        for(int i = 0; i < map.getFields().length; i++) {
            MapField mapField = map.getFields()[i];
            labels[i].setText(mapField.getResource().toString() + "\n" + mapField.getRandomValue());
        }
        for(int i = 0; i < map.getEdges().length; i++) {
            Edge edge = map.getEdges()[i];
            if(edge.getOwner() != null) {
                circles[i].setFill(Constants.PLAYER_COLORS[edge.getOwner().getId()]);
            }
        }
        for(int i = 0; i < map.getKnots().length; i++) {
            Knot knot = map.getKnots()[i];
            if(knot.getOwner() != null) {
                rectangles[i].setFill(Constants.PLAYER_COLORS[knot.getOwner().getId()]);
            }
        }
    }

    public void initialize() {
        //lerngruppe
        rectangles[0] = r0;
        rectangles[1] = r1;
        rectangles[2] = r2;
        rectangles[3] = r3;
        rectangles[4] = r4;
        rectangles[5] = r5;
        rectangles[6] = r6;
        rectangles[7] = r7;
        rectangles[8] = r8;
        rectangles[9] = r9;
        rectangles[10] = r10;
        rectangles[11] = r11;
        rectangles[12] = r12;
        rectangles[13] = r13;
        rectangles[14] = r14;
        rectangles[15] = r15;
        rectangles[16] = r16;
        rectangles[17] = r17;
        rectangles[18] = r18;
        rectangles[19] = r19;
        rectangles[20] = r20;
        rectangles[21] = r21;
        rectangles[22] = r22;
        rectangles[23] = r23;
        rectangles[24] = r24;
        rectangles[25] = r25;
        rectangles[26] = r26;
        rectangles[27] = r27;
        rectangles[28] = r28;
        rectangles[29] = r29;
        rectangles[30] = r30;
        rectangles[31] = r31;
        rectangles[32] = r32;
        rectangles[33] = r33;
        rectangles[34] = r34;
        rectangles[35] = r35;
        rectangles[36] = r36;
        rectangles[37] = r37;
        rectangles[38] = r38;
        rectangles[39] = r39;
        rectangles[40] = r40;
        rectangles[41] = r41;
        rectangles[42] = r42;
        rectangles[43] = r43;
        rectangles[44] = r44;
        rectangles[45] = r45;
        rectangles[46] = r46;
        rectangles[47] = r47;
        rectangles[48] = r48;
        rectangles[49] = r49;
        rectangles[50] = r50;
        rectangles[51] = r51;
        rectangles[52] = r52;
        rectangles[53] = r53;

        //kontakte
        circles[0] = c0;
        circles[1] = c1;
        circles[2] = c2;
        circles[3] = c3;
        circles[4] = c4;
        circles[5] = c5;
        circles[6] = c6;
        circles[7] = c7;
        circles[8] = c8;
        circles[9] = c9;
        circles[10] = c10;
        circles[11] = c11;
        circles[12] = c12;
        circles[13] = c13;
        circles[14] = c14;
        circles[15] = c15;
        circles[16] = c16;
        circles[17] = c17;
        circles[18] = c18;
        circles[19] = c19;
        circles[20] = c20;
        circles[21] = c21;
        circles[22] = c22;
        circles[23] = c23;
        circles[24] = c24;
        circles[25] = c25;
        circles[26] = c26;
        circles[27] = c27;
        circles[28] = c28;
        circles[29] = c29;
        circles[30] = c30;
        circles[31] = c31;
        circles[32] = c32;
        circles[33] = c33;
        circles[34] = c34;
        circles[35] = c35;
        circles[36] = c36;
        circles[37] = c37;
        circles[38] = c38;
        circles[39] = c39;
        circles[40] = c40;
        circles[41] = c41;
        circles[42] = c42;
        circles[43] = c43;
        circles[44] = c44;
        circles[45] = c45;
        circles[46] = c46;
        circles[47] = c47;
        circles[48] = c48;
        circles[49] = c49;
        circles[50] = c50;
        circles[51] = c51;
        circles[52] = c52;
        circles[53] = c53;
        circles[54] = c54;
        circles[55] = c55;
        circles[56] = c56;
        circles[57] = c57;
        circles[58] = c58;
        circles[59] = c59;
        circles[60] = c60;
        circles[61] = c61;
        circles[62] = c62;
        circles[63] = c63;
        circles[64] = c64;
        circles[65] = c65;
        circles[66] = c66;
        circles[67] = c67;
        circles[68] = c68;
        circles[69] = c69;
        circles[70] = c70;
        circles[71] = c71;

        //labels

        labels[0]=l0;
        labels[1]=l1;
        labels[2]=l2;
        labels[3]=l3;
        labels[4]=l4;
        labels[5]=l5;
        labels[6]=l6;
        labels[7]=l7;
        labels[8]=l8;
        labels[9]=l9;
        labels[10]=l10;
        labels[11]=l11;
        labels[12]=l12;
        labels[13]=l13;
        labels[14]=l14;
        labels[15]=l15;
        labels[16]=l16;
        labels[17]=l17;
        labels[18]=l18;

        initActionHandlers();
        initSocketIO();

    }

    private void initActionHandlers() {
        for(int i = 0; i < circles.length; i++) {
            final int circleNumber = i;
            circles[i].setOnMouseClicked(e -> contactClicked(circleNumber));
        }
        for(int i = 0; i < rectangles.length; i++) {
            final int rectangleNumber = i;
            rectangles[i].setOnMouseClicked(e -> groupClicked(rectangleNumber));
        }
        btnFinishTurn.setOnAction((e) -> finishTurn());
        btnTrade.setOnAction((e) -> trade());
    }

    private void trade() {
        Resource[] resources = TradeDialog.show();
        if(resources != null) {
            Gson gson = new Gson();
            String json = gson.toJson(resources);
            socket.emit("trade", json);
        }
    }

    private void finishTurn() {
        socket.emit("finishTurn");
    }

    private void contactClicked(int number) {
        socket.emit("buildContact", number);
    }
    private void groupClicked(int number) {
        socket.emit("buildGroup", number);
    }

}
