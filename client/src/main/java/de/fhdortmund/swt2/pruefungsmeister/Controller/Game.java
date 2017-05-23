package de.fhdortmund.swt2.pruefungsmeister.Controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jonas on 22.05.17.
 */
public class Game {

    public List<Player> players;
    private Player currentPlayer;
    private Map map;
    private int round;

    public Game() {
        players = new LinkedList<Player>();
        map = new Map();
    }

    public void start() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Herzlich Willkommen bei Prüfungsmeister, dem wohl realistischten Konsolengame des Planeten!");
        System.out.print("Wie viele Spieler seid ihr?");

        int playerCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < playerCount; i++) {
            System.out.format("Bitte den Namen von Spieler %d eingeben:", i+1);
            System.out.println("\n");
            String name = sc.nextLine();
            players.add(new Player(name));
            System.out.println("Hallo " + name);
        }

        while(true) {
            for(Player p : players) {
                System.out.println("");
                currentPlayer = p;
                chooseAction();
                System.out.println("");
                generateResources();
            }
            round++;
        }
    }

    private void trade() {
        System.out.println("1. Fastfood");
        System.out.println("2. Energydrinks");
        System.out.println("3. Bonuspunkte");
        System.out.println("4. Lernstoff");
        System.out.println("5. Technik");

        System.out.println("Was willst du verkaufen?");
        int sell = new Scanner(System.in).nextInt();
        System.out.println("Was willst du verkaufen?");
        int buy = new Scanner(System.in).nextInt();

        boolean allowed = false;

        switch (sell) {
            case 1:
                if(currentPlayer.getFastfood() >= 3) {
                    currentPlayer.setFastfood(currentPlayer.getFastfood() - 3);
                    allowed = true;
                }
                break;
            case 2:
                if(currentPlayer.getEnergydrinks() >= 3) {
                    currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() - 3);
                    allowed = true;
                }
                break;
            case 3:
                if(currentPlayer.getExtrapoints() >= 3) {
                    currentPlayer.setExtrapoints(currentPlayer.getExtrapoints() - 3);
                    allowed = true;
                }
                break;
            case 4:
                if(currentPlayer.getTechnology() >= 3) {
                    currentPlayer.setTechnology(currentPlayer.getTechnology() - 3);
                    allowed = true;
                }
                break;
            case 5:
                if(currentPlayer.getKnowhow() >= 3) {
                    currentPlayer.setKnowhow(currentPlayer.getKnowhow() - 3);
                    allowed = true;
                }
                break;
            default:
                break;
        }

        if(!allowed) {
            System.out.println("Leider hast du nicht genügend resourcen zum handeln");
            return;
        }

        switch (buy) {
            case 1:
                currentPlayer.setFastfood(currentPlayer.getFastfood() + 1);
                break;
            case 2:
                currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() + 1);
                break;
            case 3:
                currentPlayer.setExtrapoints(currentPlayer.getExtrapoints() + 1);
                break;
            case 4:
                currentPlayer.setTechnology(currentPlayer.getTechnology() + 1);
                break;
            case 5:
                currentPlayer.setKnowhow(currentPlayer.getKnowhow() + 1);
                break;
            default:
                System.out.println("Diese resource gibt es nicht. Aber deinen Einsatz nehmen wir trotzdem.");
                break;
        }
    }

    private void generateResources() {
        int random = (int)((Math.random() * 11) + 2);
        System.out.println("Es wurde gewürfelt: " + random);
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
                    System.out.format("%s, du hast 1 %s bekommen", p.getName(), resource.toString());
                }
            }
        }
    }

    private void chooseAction() {
        System.out.println(currentPlayer.getName() + " du bist dran");
        System.out.println("1: Bei Komilitonen einschleimen (Kontakte)");
        System.out.println("2: Eine Gruppe Gründen");
        System.out.println("3: Handeln");
        System.out.println("4: Ereigniskarte kaufen");
        System.out.println("5: Zug beenden");

        int action = new Scanner(System.in).nextInt();
        switch (action) {
            case 1:
                buildContact();
                break;
            case 2:
                buildGroup();
                break;
            case 3:
                trade();
                break;
            case 4:
                break;
            case 5:
                return;
            default:
                System.out.println("Wer lesen kann ist klar im Vorteil! Man sollte dich sofort exmatrikulieren");
        }

        chooseAction();
    }

    private void buildContact() {
        if(currentPlayer.getEnergydrinks() < 1 || currentPlayer.getFastfood() < 1) {
            System.out.println("Blöderweise hast du nicht genug Verpflegung um dich einzuschleimen.");
            return;
        }

        System.out.println("Auf Welcher strecke soll deine connection lobbyarbeit für dich verrichten?");
        int edgeNum = new Scanner(System.in).nextInt();

        if(edgeNum < 0 || edgeNum > 71) {
            System.out.println("Diese Strecke gibt es nicht");
            return;
        }

        Edge edge = map.getEdges()[edgeNum];

        if(edge.getOwner() != null) {
            System.out.format("Hier treibt schon %s sein unwesen!%n", edge.getOwner().getName());
            return;
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

        } else {
            System.out.println("Leider reichen deine connections nicht bis hier!");
        }

    }

    private void buildGroup() {
        if(currentPlayer.getEnergydrinks() < 1 || currentPlayer.getFastfood() < 1 || currentPlayer.getKnowhow() < 1 || currentPlayer.getTechnology() < 1 ) {
            System.out.println("Blöderweise hast du deiner Gruppe nicht viel entgegen zu setzen. Zieh leine!");
            return;
        }

        System.out.println("Auf Welcher kreuzung soll deine Gruppe auf youtube videos ähhhhh lernen?");
        int knotNum = new Scanner(System.in).nextInt();
        if(knotNum < 0 || knotNum > 53) {
            System.out.println("Diese Krezung gibt es nicht");
            return;
        }

        Knot knot = map.getKnots()[knotNum];

        if(knot.getOwner() != null) {
            System.out.format("Hier treibt schon %s sein unwesen!%n", knot.getOwner().getName());
            return;
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
            System.out.println("Du hast nun eine neue Gruppe");
            knot.setOwner(currentPlayer);
            currentPlayer.setKnowhow(currentPlayer.getKnowhow() - 1);
            currentPlayer.setTechnology(currentPlayer.getTechnology() - 1);
            currentPlayer.setFastfood(currentPlayer.getFastfood() - 1);
            currentPlayer.setEnergydrinks(currentPlayer.getEnergydrinks() - 1);

        } else {
            System.out.println("Leider reichen deine connections nicht bis hier!");
        }


    }



}
