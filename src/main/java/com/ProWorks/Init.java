package com.ProWorks;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Init extends Application {

    private double corX=0;
    private double corY=0;

    private Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {

        primaryStage=stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("/com/ProWorks/ProWorks/GUI.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 500, Color.TRANSPARENT);

        // Removing Windows border

        stage.initStyle(StageStyle.TRANSPARENT);

        // Getting first click coordenates X and Y

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                corX=mouseEvent.getX();
                corY=mouseEvent.getY();
            }
        });

        // Setting new coordenates when dragging windows

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX()-corX);
                stage.setY(mouseEvent.getScreenY()-corY);

            }
        });

        stage.setTitle("ProWorks");

        stage.setScene(scene);

        stage.setResizable(false);

        stage.show();



    }


    public static void main(String[] args) {
        launch();
    }
}