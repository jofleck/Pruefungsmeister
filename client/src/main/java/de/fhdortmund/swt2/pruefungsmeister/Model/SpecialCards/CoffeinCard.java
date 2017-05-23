package de.fhdortmund.swt2.pruefungsmeister.Model.SpecialCards;

import de.fhdortmund.swt2.pruefungsmeister.Model.Player;

/**
 * Created by jonas on 23.05.17.
 */
public class CoffeinCard extends SpecialCard{

    @Override
    public void apply(Player p) {
        if(p.getEnergydrinks() > 3) {
            p.setEnergydrinks(p.getEnergydrinks() - 3);
        } else {
            p.setEnergydrinks(0);
        }
        System.out.format("Du bist Koffein süchtig geworden. Du verlierst 3 Energydrinks." +
                " Du hast jetzt %d Energydrinks%n", p.getEnergydrinks());

    }
}
