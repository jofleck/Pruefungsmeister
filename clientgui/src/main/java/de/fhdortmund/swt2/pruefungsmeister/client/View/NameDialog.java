package de.fhdortmund.swt2.pruefungsmeister.client.View;


import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by jonas on 11.07.17.
 */
public class NameDialog {

    public static String show() {
        final TextInputDialog inputDlg = new TextInputDialog();
        inputDlg.setTitle("Namen eingeben");
        inputDlg.setContentText("Name");
        inputDlg.setHeaderText("Wie sollen wir dich nennen??");
        Optional<String> result = inputDlg.showAndWait();
        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
}
