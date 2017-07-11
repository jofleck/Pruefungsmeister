package de.fhdortmund.swt2.pruefungsmeister.Model.SpecialCards;


import de.fhdortmund.swt2.pruefungsmeister.Model.Player;

/**
 * Created by jonas on 23.05.17.
 */
public abstract class SpecialCard {

    public abstract String apply(Player p);

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
