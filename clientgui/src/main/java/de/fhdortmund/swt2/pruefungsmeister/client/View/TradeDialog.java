package de.fhdortmund.swt2.pruefungsmeister.client.View;

import de.fhdortmund.swt2.pruefungsmeister.client.Model.Resource;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jonas on 11.07.17.
 */
public class TradeDialog {

    public static Resource[] show() {
        Resource[] resources = new Resource[2];
        List<Resource> choices = new ArrayList<>();
        choices.add(Resource.ENERGYDRINK);
        choices.add(Resource.FASTFOOD);
        choices.add(Resource.EXTRA_POINTS);
        choices.add(Resource.KNOW_HOW);
        choices.add(Resource.TECHNOLOGY);

        ChoiceDialog<Resource> dialog = new ChoiceDialog<>(Resource.ENERGYDRINK, choices);
        dialog.setTitle("Angebot");
        dialog.setHeaderText("Was bietest du an?");
        dialog.setContentText("Dein Angebot:");

        Optional<Resource> result = dialog.showAndWait();
        if (result.isPresent()) {
            resources[0] = result.get();
        } else {
            return null;
        }

        dialog.setTitle("Wunsch");
        dialog.setHeaderText("Was möchstes du haben?");
        dialog.setContentText("Benötigte Resource:");

        result = dialog.showAndWait();
        if (result.isPresent()) {
            resources[1] = result.get();
        } else {
            return null;
        }

        return resources;
    }
}
