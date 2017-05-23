package de.fhdortmund.swt2.pruefungsmeister.Controller;

/**
 * Created by jonas on 22.05.17.
 */
public enum Resource {
    ENERGYDRINK("Energydrink"), FASTFOOD("Fastfood"), EXTRA_POINTS("Bonuspunkte"), KNOW_HOW("Lernstoff"),
    TECHNOLOGY("Technik");

    private final String description;

    Resource(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}


