package pl.kolata.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Class with application dialogs used within application
 * Created by Aleksander on 2017-06-29.
 */
public class ApplicationDialogs {

    /**
     * Dialog showing information about application
     */
    public static void displayAboutApplicationDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("About");
        alert.setContentText("This is a really,really simple application.\n" +
                "The goal is to encode polish alphabet characters to unicode.\n" +
                "For now, only polish language is supported, but later - who knows :)");

        alert.showAndWait();
    }

    /**
     * Exception dialog showed when an exception is thrown
     * @param exception throwed exception
     */
    public static void displayExceptionWindow(Exception exception){
        Alert errorWindow = new Alert(Alert.AlertType.ERROR);

        errorWindow.setHeaderText("An exception was thrown");
        errorWindow.setTitle("Exception");
        errorWindow.setContentText("Below You can see a stack trace");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        BorderPane layout = new BorderPane();

        Label label = new Label(exception.getClass().getSimpleName());
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);

        layout.setCenter(textArea);
        layout.setTop(label);
        errorWindow.getDialogPane().setExpandableContent(layout);
        errorWindow.getDialogPane().getScene().getWindow().sizeToScene();

        errorWindow.showAndWait();
    }
}
