package de.fhdortmund.swt2.pruefungsmeister.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jonas on 07.07.17.
 */
public class Main  extends Application{

    public static void main(String[] args) {
        launch(args);;
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));

        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Pruefungsmeister");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }





}
