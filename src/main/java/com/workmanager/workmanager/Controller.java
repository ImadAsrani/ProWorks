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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.javafx.FontIcon;

public class Controller {

    // Window frame buttons
    @FXML
    private Button minimizeButton, maximizeButton, closeButton;

    // Left panel buttons
    @FXML
    private Button velocidadavance;

    @FXML
    private Pane mainScreen;

    // Image assets from the maximize and normalize buttons
    @FXML
    private ImageView maximizeIMAGE, normalizeIMAGE;

    // The different screens in the app
    @FXML
    private VBox pantalla1;


    @FXML
    private void initialize(){

        ImageView minimizeIMAGE=new ImageView(new Image("file:src/main/resources/Images/WindowButtons/minimize.png"));
        ImageView closeIMAGE=new ImageView(new Image("file:src/main/resources/Images/WindowButtons/close.png"));
        maximizeIMAGE =new ImageView(new Image("file:src/main/resources/Images/WindowButtons/maximize.png"));
        normalizeIMAGE =new ImageView(new Image("file:src/main/resources/Images/WindowButtons/normalize.png"));

        minimizeIMAGE.setFitWidth(13);
        minimizeIMAGE.setFitHeight(13);

        maximizeIMAGE.setFitWidth(13);
        maximizeIMAGE.setFitHeight(13);

        closeIMAGE.setFitWidth(13);
        closeIMAGE.setFitHeight(13);

        normalizeIMAGE.setFitWidth(13);
        normalizeIMAGE.setFitHeight(13);

        minimizeButton.setGraphic(minimizeIMAGE);
        maximizeButton.setGraphic(maximizeIMAGE);
        closeButton.setGraphic(closeIMAGE);

        FontIcon icon = FontIcon.of(BootstrapIcons.SPEEDOMETER);
        icon.setIconColor(Color.WHITE);
        icon.setIconSize(16);

        // Add icon to a created button
        velocidadavance.setGraphic(icon);


    }


    public void windowButtons(ActionEvent s) {

       Button pressedB=(Button) s.getSource();

       Stage currentStage = (Stage) pressedB.getScene().getWindow();

        if(pressedB.getId().contains("minimize")){

            // Instance of the animation
            AnimationFX fx = new ZoomOut(mainScreen);

            // Speed of the animation
            fx.setSpeed(1.5);

            // Reset the finish state after plays the animation
            fx.setResetOnFinished(true);
            fx.setOnFinished(_ -> currentStage.setIconified(true));

            // Playing the animation
            fx.play();
        }

        else if(pressedB.getId().contains("maximize")){

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            currentStage.setX(bounds.getMinX());
            currentStage.setY(bounds.getMinY());
            currentStage.setWidth(bounds.getWidth());
            currentStage.setHeight(bounds.getHeight());

            pressedB.setId("normalize");

            maximizeButton.setGraphic(normalizeIMAGE);


        }

        else if(pressedB.getId().contains("normalize")){


            currentStage.setWidth(800);
            currentStage.setHeight(500);

            // Center the app when normalized

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

            currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
            currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);


            // Set default behavior of the maximize state

            pressedB.setId("maximize");
            maximizeButton.setGraphic(maximizeIMAGE);


        }

        else {

            AnimationFX animation = new FadeOut(mainScreen);
            animation.setSpeed(1);

            Timeline delay=new Timeline(new KeyFrame(Duration.seconds(0.8), _ -> currentStage.close()));
            delay.play();

            animation.play();

        }

    }

    public void leftButtons(ActionEvent s){

        Button pressedButton=(Button) s.getSource();

        if(pressedButton.getId().contains("velocidad")){

            pantalla1.setVisible(true);

            velocidadavance.setStyle("-fx-text-fill: #4cacf4");

            FontIcon icon = FontIcon.of(BootstrapIcons.SPEEDOMETER);
            icon.setIconColor(Color.valueOf("#4cacf4"));
            icon.setIconSize(16);

            // Add icon to a created button
            velocidadavance.setGraphic(icon);


        }


    }


}