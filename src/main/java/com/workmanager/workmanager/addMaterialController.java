package com.workmanager.workmanager;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomOut;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class addMaterialController {


    @FXML
    private Pane addMaterialMainScreen;

    // Window frame buttons
    @FXML
    private Button closeButton;

    @FXML
    private void initialize(){

        windowButtonsDesign();

    }




    private void windowButtonsDesign(){

        ImageView closeIMAGE = new ImageView(new Image("file:src/main/resources/Images/WindowButtons/close.png"));

        closeIMAGE.setFitWidth(13);
        closeIMAGE.setFitHeight(13);

        closeButton.setGraphic(closeIMAGE);

    }
    public void windowButtonsFunctionallity(ActionEvent s) {

        Button pressedB = (Button) s.getSource();

        Stage currentStage = (Stage) pressedB.getScene().getWindow();

        if (pressedB.getId().contains("close")) {

            AnimationFX animation = new FadeOut(addMaterialMainScreen);
            animation.setSpeed(2);

            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.8), _ -> currentStage.close()));
            delay.play();

            animation.play();
        }

    }



}
