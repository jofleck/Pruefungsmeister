package de.fhdortmund.swt2.pruefungsmeister.Controller.SpecialCards;

import de.fhdortmund.swt2.pruefungsmeister.Controller.Player;

/**
 * Created by jonas on 23.05.17.
 */
public abstract class SpecialCard {

    public abstract void apply(Player p);

    public static SpecialCard randomCard() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 1:
                return new CoffeinCard();
            case 2:
                return new WindowsCard();
            case 3:
                return new ContactCard();

        }
        return null;
    }
}
