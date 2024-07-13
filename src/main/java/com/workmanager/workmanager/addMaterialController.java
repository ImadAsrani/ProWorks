package com.workmanager.workmanager;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeOut;
import com.workmanager.workmanager.classes.middleClass;
import com.workmanager.workmanager.classes.Materials;
import com.workmanager.workmanager.db.DBManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class addMaterialController {


    @FXML
    private Pane addMaterialMainScreen;

    // Window frame buttons
    @FXML
    private Button closeButton;

    @FXML
    private Label addingMaterialErrors;

    @FXML
    private ColorPicker materialColor;

    @FXML
    private TextField materialHardness, materialName;


    @FXML
    private void initialize(){

        windowButtonsDesign();


    }

    @FXML
    private void addMaterialFunction() throws IOException, SQLException {

        //DBManager je=new DBManager("jdbc:mysql://0n2.h.filess.io:3307/proworks_musichair", "proworks_musichair", "534f76d8af5d0e3231697279d418644f42ef8ccb");

        DBManager je=new DBManager("jdbc:mysql://g5e.h.filess.io:3307/WorkManager_rosetrack", "WorkManager_rosetrack", "imadgenio");


        je.checkTable("Materialess");

        int id=0;
        id++;

        Materials instanceM=new Materials(id, materialName.getText(), Integer.parseInt(materialHardness.getText()), materialColor.getValue());

        // Traspass the instance of the new material to the ControllerCheck
        middleClass.getInstance().getInstanceOfGoalClass().getInfoAboutMaterial(instanceM);

        Stage stage = (Stage) materialName.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("SmallScenes/materialAddedSuccessful.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Color.TRANSPARENT);
        stage.setScene(scene);

        stage.show();

        // Auto close the success added window

        Timeline delay = new Timeline(new KeyFrame(Duration.seconds(4), _ -> {
            stage.close();
        }));

        delay.play();

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
