package de.fhdortmund.swt2.pruefungsmeister.Model.SpecialCards;


import de.fhdortmund.swt2.pruefungsmeister.Model.Player;

/**
 * Created by jonas on 23.05.17.
 */
public class ContactCard extends SpecialCard {

    @Override
    public String apply(Player p) {
        p.setKnowhow(p.getKnowhow() + 1);
        return String.format("Du bist zur Sprechstunde deines Dozenten gegangen und hast " +
                "jetzt endlich die Vorlesung verstanden. Du kriegst einen Wissenspunkt.");

    }
}
