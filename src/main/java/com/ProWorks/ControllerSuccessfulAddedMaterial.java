package com.ProWorks;

import animatefx.animation.*;
import com.ProWorks.classes.Materials;
import com.ProWorks.classes.middleClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class ControllerSuccessfulAddedMaterial {

    @FXML
    private Label materialAdded;

    @FXML
    private BorderPane checkScreen;

    private Materials material;

    @FXML
    public void initialize(){

        AnimationFX animation = new BounceIn(checkScreen);
        animation.setSpeed(2);
        animation.play();

        Materials material= middleClass.getInstance().getInstanceOfGoalClass().material;

        materialAdded.setText("Añadido correctamente " + material.getName() + " con dureza " + material.getHardness() + " HB");

        Timeline delay=new Timeline(new KeyFrame(Duration.seconds(3), _ -> {

            AnimationFX animation2 = new BounceOut(checkScreen);
            animation2.setSpeed(2.5);
            animation2.play();

        }));
        delay.play();


    }

    public void getInfoAboutMaterial(Materials instance){

        this.material=instance;

    }




}
