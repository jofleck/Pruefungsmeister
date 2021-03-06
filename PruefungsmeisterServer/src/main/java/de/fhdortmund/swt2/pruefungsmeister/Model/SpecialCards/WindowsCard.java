package de.fhdortmund.swt2.pruefungsmeister.Model.SpecialCards;


import de.fhdortmund.swt2.pruefungsmeister.Model.Player;

/**
 * Created by jonas on 23.05.17.
 */
public class WindowsCard extends SpecialCard {
    @Override
    public String apply(Player p) {
        if(p.getTechnology() > 2) {
            p.setTechnology(p.getTechnology() - 2);
        } else {
            p.setTechnology(0);
        }
        return String.format("Windows installiert Updates als du eine Präsentation halten willst . Du verlierst 2 Technik." +
                " Du hast jetzt %d Technik.%n", p.getTechnology());
    }
}
