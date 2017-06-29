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


    @FXML
    private void initialize(){
        bindSizeProperties();
    }

    private void bindSizeProperties() {
        rawTextTA.prefHeightProperty().bind(leftAP.heightProperty());
        rawTextTA.prefWidthProperty().bind(leftAP.widthProperty());

        encodedTextTA.prefHeightProperty().bind(rightAP.heightProperty());
        encodedTextTA.prefWidthProperty().bind(rightAP.widthProperty());

        encodeBTN.prefWidthProperty().bind(bottomHBox.widthProperty());

        if(!isPropertiesFileLoaded){
            encodeBTN.setDisable(true);
        }
    }

    @FXML
    private void closeApplication(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void showAboutApplicationDialog(){
        ApplicationDialogs.displayAboutApplicationDialog();
    }

    @FXML
    private void setModena(){
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    @FXML
    private void setCaspian(){
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    @FXML
    private void setAlwaysOnTop(){
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean alwaysOnTop = alwaysOnTopCMI.isSelected();
        stage.setAlwaysOnTop(alwaysOnTop);
    }

    @FXML
    private void clearBothSides(){
        rawTextTA.clear();
        encodedTextTA.clear();
    }

    @FXML
    private void encodeText(){
        polishTextEncoder.setTextToEncode(rawTextTA.getText());
        encodedTextTA.setText(polishTextEncoder.encodeText());
    }

}
