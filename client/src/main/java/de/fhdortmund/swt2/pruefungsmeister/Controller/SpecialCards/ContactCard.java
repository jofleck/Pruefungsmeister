package de.fhdortmund.swt2.pruefungsmeister.Controller.SpecialCards;

import de.fhdortmund.swt2.pruefungsmeister.Controller.Player;

/**
 * Created by jonas on 23.05.17.
 */
public class ContactCard extends SpecialCard {

    @Override
    public void apply(Player p) {
        p.setKnowhow(p.getKnowhow() + 1);
        System.out.println("Du bist zur Sprechstunde deines Dozenten gegangen und hast " +
                "jetzt endlich die Vorlesung verstanden. Du kriegst einen Wissenspunkt.");

    }
}
