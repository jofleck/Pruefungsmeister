package de.fhdortmund.swt2.pruefungsmeister.Controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fhdortmund.swt2.pruefungsmeister.Model.*;
import de.fhdortmund.swt2.pruefungsmeister.Model.Map;
import de.fhdortmund.swt2.pruefungsmeister.Model.SpecialCards.SpecialCard;
import de.fhdortmund.swt2.pruefungsmeister.Persistence.PersistenceManager;
import de.fhdortmund.swt2.pruefungsmeister.Persistence.PruefungsmeisterDAO;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jonas on 22.05.17.
 */
public class Game implements Observer{

    private SocketIOServer server;

    private List<Player> players;
    private Player currentPlayer;
    private Map map;
    private PruefungsmeisterDAO dao;
    private int round;
    private boolean playing;

    public Game() {
        players = new LinkedList<Player>();
        map = new Map();
        dao = new PersistenceManager();
    }

    private void initSocketIO() {
        Configuration config = new Configuration();
        //config.setHostname("localhost");
        config.setPort(9092);

        server = new SocketIOServer(config);
        server.addEventListener("join", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String name, AckRequest ackRequest) throws Exception {
                if(!playing) {
                    Player p = new Player(name);
                    p.setClient(socketIOClient);
                    socketIOClient.sendEvent("message", "Du nimmst am Spiel teil");
                    addPlayer(p);
                    sendMapToAllPlayers();
                    sendPlayerInformationToAllPlayers();
                } else {
                    socketIOClient.sendEvent("message", "Das Spiel hat schon angefangen");
                }
            }
        });

        server.addEventListener("buildGroup", int.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Integer knotNumber, AckRequest ackRequest) throws Exception {
                if(!verifyClient(socketIOClient)) {
                    socketIOClient.sendEvent("message", "Darf der das? Nö!");
                    return;
                }
                socketIOClient.sendEvent("message", buildGroup(knotNumber));
                System.out.println("Test123");
                sendMapToAllPlayers();
                sendPlayerInformationToAllPlayers();

            }
        });

        server.addEventListener("buildContact", int.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Integer edgeNumber, AckRequest ackRequest) throws Exception {
                if(!verifyClient(socketIOClient)) {
                    socketIOClient.sendEvent("message", "Darf der das? Nö!");
                    return;
                }
                socketIOClient.sendEvent("message", buildContact(edgeNumber));
                sendMapToAllPlayers();
                sendPlayerInformationToAllPlayers();

            }
        });
        server.addEventListener("finishTurn", int.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Integer edgeNumber, AckRequest ackRequest) throws Exception {
                if(!verifyClient(socketIOClient)) {
                    socketIOClient.sendEvent("message", "Darf der das? Nö!");
                    return;
                }
                nextPlayer();

            }
        });
        server.addEventListener("trade", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String json, AckRequest ackRequest) throws Exception {
                if(!verifyClient(socketIOClient)) {
                    socketIOClient.sendEvent("message", "Darf der das? Nö!");
                    return;
                }
                Gson gson = new Gson();
                Resource[] resources = gson.fromJson(json, Resource[].class);
                socketIOClient.sendEvent("message", trade(resources));
                sendPlayerInformationToAllPlayers();

            }
        });
        server.addEventListener("specialCard", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String json, AckRequest ackRequest) throws Exception {
                if(!verifyClient(socketIOClient)) {
                    socketIOClient.sendEvent("message", "Darf der das? Nö!");
                    return;
                }
                socketIOClient.sendEvent("message", generateSpecialCard());
                sendPlayerInformationToAllPlayers();

            }
        });
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down");
            for(Player p : players) {
                p.getClient().disconnect();
            }
            server.stop();
        }));
    }


    private void nextPlayer() {
        if(currentPlayer == null) {
            currentPlayer = players.get(0);
        } else {
            dao.beginTransaction();
            dao.update(currentPlayer);
            dao.commitTransaction();
            int nextPlayerId = players.indexOf(currentPlayer) +1;
            if(nextPlayerId >= players.size()) {
                nextPlayerId = 0;
                round ++;
            }
            currentPlayer = players.get(nextPlayerId);
        }

        broadcastMessage(generateResources());
        sendPlayerInformationToAllPlayers();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        broadcastMessage(currentPlayer.getName() + " ist am Zug");
    }

    private void sendPlayerInformationToAllPlayers() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        for (Player p : players) {
            p.getClient().sendEvent("playerUpdate", gson.toJson(p));
        }
    }

    private void broadcastMessage(String message) {
        for (Player p : players) {
            p.getClient().sendEvent("message", message);
        }
    }

    private void sendMapToAllPlayers() {
        try {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String mapJson = gson.toJson(map);
            System.out.println(mapJson);
            for (Player p : players) {
                p.getClient().sendEvent("mapUpdate", mapJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean verifyClient(SocketIOClient client) {
        if(currentPlayer == null && players.size() > 0) {
            currentPlayer = players.get(0);
        }
        for(Player p : players) {
            if(p.getClient() == client) {
                System.out.println("Player found");
            }
            if(p.getClient() == client && p == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private void addPlayer(Player p) {
        p.addObserver(this);
        players.add(p);
        p.setLastUpdate(LocalDateTime.now());
        dao.beginTransaction();
        dao.persist(p);
        dao.commitTransaction();
    }

    public void start() {
       initSocketIO();
    }

    private String trade(Resource[] resources) {

        String out = "";
        boolean allowed = false;
        switch (resources[0]) {
            case FASTFOOD:
                if(currentPlayer.getFastfood() >= 3) {
                    currentPlayer.setFastfood(currentPlayer.getFastfood() - 3);
                    allowed = true;
                }
                break;
            case ENERGYDRINK:
                if(currentPlayer.getEnergydrinks() >= 3) {
                    currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() - 3);
                    allowed = true;
                }
                break;
            case EXTRA_POINTS:
                if(currentPlayer.getExtrapoints() >= 3) {
                    currentPlayer.setExtrapoints(currentPlayer.getExtrapoints() - 3);
                    allowed = true;
                }
                break;
            case TECHNOLOGY:
                if(currentPlayer.getTechnology() >= 3) {
                    currentPlayer.setTechnology(currentPlayer.getTechnology() - 3);
                    allowed = true;
                }
                break;
            case KNOW_HOW:
                if(currentPlayer.getKnowhow() >= 3) {
                    currentPlayer.setKnowhow(currentPlayer.getKnowhow() - 3);
                    allowed = true;
                }
                break;
            default:
                break;
        }

        if(!allowed) {
            return "Leider hast du nicht genügend resourcen zum handeln";
        }

        switch (resources[1]) {
            case FASTFOOD:
                currentPlayer.setFastfood(currentPlayer.getFastfood() + 1);
                break;
            case ENERGYDRINK:
                currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() + 1);
                break;
            case EXTRA_POINTS:
                currentPlayer.setExtrapoints(currentPlayer.getExtrapoints() + 1);
                break;
            case TECHNOLOGY:
                currentPlayer.setTechnology(currentPlayer.getTechnology() + 1);
                break;
            case KNOW_HOW:
                currentPlayer.setKnowhow(currentPlayer.getKnowhow() + 1);
                break;
            default:
                return "Diese resource gibt es nicht. Aber deinen Einsatz nehmen wir trotzdem.";
        }
        return "Deal!";
    }

    private String generateResources() {
        String out = "";
        int random = (int)((Math.random() * 11) + 2);
        out += "Es wurde gewürfelt: " + random+ "\n";
        MapField[] fields = Arrays.asList(map.getFields()).stream().filter((mf) -> mf.getRandomValue() == random).
                toArray(size -> new MapField[size]);

        for(MapField mf: fields) {
            Resource resource = mf.getResource();
            for(Knot k : mf.getKnots()) {
                if(k.getOwner() != null) {
                    Player p = k.getOwner();
                    switch (resource) {
                        case FASTFOOD:
                            p.setFastfood(p.getFastfood() + 1);
                            break;
                        case TECHNOLOGY:
                            p.setTechnology(p.getTechnology() + 1);
                            break;
                        case KNOW_HOW:
                            p.setKnowhow(p.getKnowhow() + 1);
                            break;
                        case ENERGYDRINK:
                            p.setEnergydrinks(p.getEnergydrinks() + 1);
                            break;
                        case EXTRA_POINTS:
                            p.setExtrapoints(p.getExtrapoints() + 1);
                            break;

                    }
                    out += String.format("%s, du hast 1 %s bekommen\n", p.getName(), resource.toString());
                }
            }
        }
        return out;
    }



    private String generateSpecialCard() {
        if(currentPlayer.getExtrapoints() >= 1) {
            currentPlayer.setExtrapoints(currentPlayer.getExtrapoints() - 1);
            return SpecialCard.randomCard().apply(currentPlayer);
        } else {
            return "Leider hast du keine Bonuspunkte. Geh studieren!" ;
        }
    }

    private String buildContact(int edgeNum) {
        if(currentPlayer.getEnergydrinks() < 1 || currentPlayer.getFastfood() < 1) {
           return "Blöderweise hast du nicht genug Verpflegung um dich einzuschleimen.";
        }


        if(edgeNum < 0 || edgeNum > 71) {
           return "Diese Strecke gibt es nicht";
        }

        Edge edge = map.getEdges()[edgeNum];

        if(edge.getOwner() != null) {
          return String.format("Hier treibt schon %s sein unwesen!%n", edge.getOwner().getName());
        }

        boolean allowed = false;

        for(Knot k : edge.getKnots()) {
            if(k.getOwner() == currentPlayer) {
                allowed = true;
                break;
            }

            for(Edge e : k.getEdges()) {
                if(e.getOwner() == currentPlayer) {
                    allowed = true;
                    break;
                }
            }
            if(allowed)
                break;
        }

        if(allowed) {
            edge.setOwner(currentPlayer);
            System.out.println("Du hast nun eine neue Connection!");
            currentPlayer.setFastfood(currentPlayer.getFastfood() - 1);
            currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() - 1);
            return ("Du hast nun eine neue Connection!");

        } else {
            return "Leider reichen deine connections nicht bis hier!";
        }

    }

    private String buildGroup(int knotNum) {
        if(currentPlayer.getEnergydrinks() < 1 || currentPlayer.getFastfood() < 1 || currentPlayer.getKnowhow() < 1 || currentPlayer.getTechnology() < 1 ) {
            return "Blöderweise hast du deiner Gruppe nicht viel entgegen zu setzen. Zieh leine!";
        }

        if(knotNum < 0 || knotNum > 53) {
            return "Diese Krezung gibt es nicht";
        }

        Knot knot = map.getKnots()[knotNum];

        if(knot.getOwner() != null) {
            return String.format("Hier treibt schon %s sein unwesen!%n", knot.getOwner().getName());
        }

        boolean allowed = false;

        if(round == 0) {
            allowed = true;
        } else {
            for (Edge e : knot.getEdges()) {
                if (e.getOwner() == currentPlayer) {
                    allowed = true;
                }
            }
        }

        if(allowed) {
            knot.setOwner(currentPlayer);
            System.out.println(currentPlayer.getName());
            currentPlayer.setKnowhow(currentPlayer.getKnowhow() - 1);
            currentPlayer.setTechnology(currentPlayer.getTechnology() - 1);
            currentPlayer.setFastfood(currentPlayer.getFastfood() - 1);
            currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() - 1);
            currentPlayer.setExams(currentPlayer.getExams() + 1);
            if(currentPlayer.getExams() >= 12) {
                return currentPlayer.getName() + " hat gewonnen";
            }
            return "Du hast nun eine neue Gruppe";

        } else {
            return "Leider reichen deine connections nicht bis hier!";
        }


    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Player) {
            Player p = (Player)o;
            System.out.println(p.getName() + " hat jetzt " + p.getExams() + " Klausuren bestanden");
        }
    }
}
