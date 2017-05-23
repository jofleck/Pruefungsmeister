package de.fhdortmund.swt2.pruefungsmeister.Controller;

/**
 * Created by jonas on 22.05.17.
 */
public enum Resource {
    ENERGYDRINK(0), FASTFOOD(1), EXTRA_POINTS(2), KNOW_HOW(3), TECHNOLOGY(4);

    private final int number;
    Resource(int number) {
        this.number = number;
    }

    public int getIntValue() { return number; }
    }
