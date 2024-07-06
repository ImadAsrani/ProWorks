package com.workmanager.workmanager;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {
    @FXML
    private Button minimize, maximize, close;

    @FXML
    private Pane main;

    @FXML
    private ImageView i2, i4;


    @FXML
    private void initialize(){

        ImageView i1=new ImageView(new Image("file:src/main/resources/Images/WindowButtons/signo-menos.png"));
        i2=new ImageView(new Image("file:src/main/resources/Images/WindowButtons/redimensionar.png"));
        ImageView i3=new ImageView(new Image("file:src/main/resources/Images/WindowButtons/cruzar.png"));
        i4=new ImageView(new Image("file:src/main/resources/Images/WindowButtons/normalizar.png"));

        i1.setFitWidth(15);
        i1.setFitHeight(15);

        i2.setFitWidth(15);
        i2.setFitHeight(15);

        i3.setFitWidth(15);
        i3.setFitHeight(15);

        i4.setFitWidth(15);
        i4.setFitHeight(15);

        minimize.setGraphic(i1);
        maximize.setGraphic(i2);
        close.setGraphic(i3);

    }


    public void windowButtons(ActionEvent s) throws IOException {

       Button pressedB=(Button) s.getSource();

       Stage currentStage = (Stage) pressedB.getScene().getWindow();

        if(pressedB.getId().equals("minimize")){

            // Instance of the animation
            AnimationFX fx = new ZoomOut(main);

            // Speed of the animation
            fx.setSpeed(1.5);

            // Reset the finish state after plays the animation
            fx.setResetOnFinished(true);
            fx.setOnFinished(actionEvent -> currentStage.setIconified(true));

            // Playing the animation
            fx.play();
       }

        else if(pressedB.getId().equals("maximize")){

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            currentStage.setX(bounds.getMinX());
            currentStage.setY(bounds.getMinY());
            currentStage.setWidth(bounds.getWidth());
            currentStage.setHeight(bounds.getHeight());

            pressedB.setId("normalize");

            maximize.setGraphic(i4);


        }

        else if(pressedB.getId().equals("normalize")){


            currentStage.setWidth(800);
            currentStage.setHeight(500);

            // Center the app when normalized

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

            currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
            currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);


            // Set default behavior of the maximize state

            pressedB.setId("maximize");
            maximize.setGraphic(i2);


        }

        else {

            AnimationFX animation = new FadeOut(main);
            animation.setSpeed(1);

            Timeline delay=new Timeline(new KeyFrame(Duration.seconds(0.8), e -> currentStage.close()));
            delay.play();

            animation.play();

        }

    }



}