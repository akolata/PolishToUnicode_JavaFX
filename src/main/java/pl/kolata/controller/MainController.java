package pl.kolata.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.kolata.encoder.Encoder;
import pl.kolata.encoder.impl.PolishToUnicodeEncoder;
import pl.kolata.exception.NoPropertiesFoundException;
import pl.kolata.utils.ApplicationDialogs;

import java.io.IOException;

/**
 * Controller used with main application window
 */
public
    class MainController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane leftAP;
    @FXML
    private AnchorPane rightAP;
    @FXML
    private TextArea encodedTextTA;
    @FXML
    private TextArea rawTextTA;
    @FXML
    private Button encodeBTN;
    @FXML
    private HBox bottomHBox;
    @FXML
    private CheckMenuItem alwaysOnTopCMI;

    private Encoder polishTextEncoder;
    private boolean isPropertiesFileLoaded = true;

    /**
     * Default constructor for controller. Initializes implementation of current language encoder
     */
    public MainController(){
        try {
            polishTextEncoder = new PolishToUnicodeEncoder();
        } catch (IOException e1) {
            Platform.runLater(()-> ApplicationDialogs.displayExceptionWindow(e1));
        } catch (NoPropertiesFoundException e2) {
            Platform.runLater(()-> ApplicationDialogs.displayExceptionWindow(e2));
            isPropertiesFileLoaded = false;
        }
    }


    /**
     * Method called after components from FXML will be binded
     */
    @FXML
    private void initialize(){
        bindSizeProperties();

        if(!isPropertiesFileLoaded){
            encodeBTN.setDisable(true);
        }
    }

    /**
     * Method binds components width and height properties with each other, to make sure layout during
     * window resize behave well.
     */
    private void bindSizeProperties() {
        rawTextTA.prefHeightProperty().bind(leftAP.heightProperty());
        rawTextTA.prefWidthProperty().bind(leftAP.widthProperty());

        encodedTextTA.prefHeightProperty().bind(rightAP.heightProperty());
        encodedTextTA.prefWidthProperty().bind(rightAP.widthProperty());

        encodeBTN.prefWidthProperty().bind(bottomHBox.widthProperty());
    }

    /**
     * Closes an application after user will click on menu item
     */
    @FXML
    private void closeApplication(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Calls dialog after after user will click on menu item
     */
    @FXML
    private void showAboutApplicationDialog(){
        ApplicationDialogs.displayAboutApplicationDialog();
    }

    /**
     * Changes the application style to Modena
     */
    @FXML
    private void setModena(){
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    /**
     * Changes the application style to Caspian
     */
    @FXML
    private void setCaspian(){
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    /**
     * Changes the value of alwaysOnTop window property
     */
    @FXML
    private void setAlwaysOnTop(){
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean alwaysOnTop = alwaysOnTopCMI.isSelected();
        stage.setAlwaysOnTop(alwaysOnTop);
    }

    /**
     * Clears both text areas
     */
    @FXML
    private void clearBothSides(){
        rawTextTA.clear();
        encodedTextTA.clear();
    }

    /**
     * Shows encoded text in right text area
     */
    @FXML
    private void encodeText(){
        polishTextEncoder.setTextToEncode(rawTextTA.getText());
        encodedTextTA.setText(polishTextEncoder.encodeText());
    }

}
