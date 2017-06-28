package pl.kolata.utils;

import javafx.scene.control.Alert;

/**
 * Created by Aleksander on 2017-06-29.
 */
public class ApplicationDialogs {

    public static void displayAboutApplicationDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("About");
        alert.setContentText("This is a really,really simple application.\n" +
                "The goal is to encode polish alphabet characters to unicode.\n" +
                "For now, only polish language is supported, but later - who knows :)");

        alert.showAndWait();
    }
}
