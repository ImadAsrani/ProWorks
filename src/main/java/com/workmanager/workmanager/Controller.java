package com.workmanager.workmanager;

import animatefx.animation.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.kordamp.ikonli.bootstrapicons.BootstrapIcons;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;


public class Controller {

    // Variables for dragging the "add new material" window

    private double corX=0;
    private double corY=0;

    // Window frame buttons
    @FXML
    private Button minimizeButton, maximizeButton, closeButton;

    // Left panel buttons
    @FXML
    private Button velocidadavance, pedidomaterial;

    @FXML
    private Pane mainScreen;

    // Image assets from the maximize and normalize buttons
    @FXML
    private ImageView maximizeIMAGE, normalizeIMAGE;

    // The different screens in the app
    @FXML
    private VBox laterald, centrod, herramientasTorno, herramientasCentro;

    @FXML
    private Label titulo1, titulo2;

    @FXML
    private ChoiceBox<String> materiales;


    // Initialize method

    @FXML
    private void initialize() {

        // Windows button design, image.
        windowButtonsDesign();


        // Left panel buttons icons
        leftButtonsIcons();


        // Set custom fonts
        setFonts();

        // Default init settings
        initSettings();




        // -- pruebas codigo

        materiales.getItems().add("F114");


    }



    // First step, select the screen

    public void leftButtons(ActionEvent s) {

        Button pressedButton = (Button) s.getSource();

        if (pressedButton.equals(laterald.getChildren().getFirst())) {

            checkPressed((Button) laterald.getChildren().getFirst());

        } else if (pressedButton.equals(laterald.getChildren().get(1))) {

            checkPressed((Button) laterald.getChildren().get(1));

        }



    }

    // 2.A Feed and speed calculator

    public void feedAndSpeedCalculator(ActionEvent s){

            Button selected=(Button) s.getSource();

        if(selected.getText().contains("Centro")){

                herramientasCentro.setVisible(true);
                herramientasCentro.setManaged(true);

                herramientasTorno.setVisible(false);
                herramientasTorno.setManaged(false);

        } else {

            herramientasCentro.setVisible(false);
            herramientasCentro.setManaged(false);

            herramientasTorno.setVisible(true);
            herramientasTorno.setManaged(true);

        }


    }





    // Adding material ( 2.A OPTION )

    public void addMaterial(){

        // Create a new stage for adding material function

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("addMaterial.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 415, 215, Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.initModality(Modality.APPLICATION_MODAL);


            Stage stagePrincipal= (Stage) titulo1.getScene().getWindow();


            // Move the new opened Window

            scene.setOnMousePressed(mouseEvent -> {
                corX=mouseEvent.getX();
                corY=mouseEvent.getY();
            });

            // Setting new coordenates when dragging windows

            scene.setOnMouseDragged(mouseEvent ->  {
                    stage.setX(mouseEvent.getScreenX()-corX);
                    stage.setY(mouseEvent.getScreenY()-corY);
            });

            stage.setTitle("Añadir nuevo material");
            stage.setScene(scene);

            stage.show();

            stage.setX((stagePrincipal.getX()+stagePrincipal.getWidth()/2)-(stage.getWidth()/2));
            stage.setY((stagePrincipal.getY()+stagePrincipal.getHeight()/2)-(stage.getHeight()/2));



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }






    }





    // Colorize the selected left panel buttons and enable the linked scene to the selected button

    public void checkPressed(Button selected){


        // Set all the buttons text and icons white

        for(int i=0; i<laterald.getChildren().size(); i++){

            Button b=(Button) laterald.getChildren().get(i);

            b.setStyle("-fx-text-fill: white");

            FontIcon icono=(FontIcon) b.getGraphic();

            icono.setIconColor(Color.WHITE);
            b.setGraphic(icono);

        }


        // Set the selected button text and icon blue

        selected.setStyle("-fx-text-fill: #4cacf4");
        FontIcon icono=(FontIcon) selected.getGraphic();
        icono.setIconColor(Color.valueOf("#4cacf4"));
        selected.setGraphic(icono);


        // Hide and disable managed state of all the windows

        for(Node s:centrod.getChildren()){

            VBox selectedVbox=(VBox) s;
            selectedVbox.setVisible(false);
            selectedVbox.setManaged(false);

        }

        // Show and set managed the selected window

        centrod.getChildren().get(laterald.getChildren().indexOf(selected)).setVisible(true);
        centrod.getChildren().get(laterald.getChildren().indexOf(selected)).setManaged(true);


    }





    // Default methods of the window buttons.

    private void windowButtonsDesign(){

        ImageView minimizeIMAGE = new ImageView(new Image("file:src/main/resources/Images/WindowButtons/minimize.png"));
        ImageView closeIMAGE = new ImageView(new Image("file:src/main/resources/Images/WindowButtons/close.png"));
        maximizeIMAGE = new ImageView(new Image("file:src/main/resources/Images/WindowButtons/maximize.png"));
        normalizeIMAGE = new ImageView(new Image("file:src/main/resources/Images/WindowButtons/normalize.png"));

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

    }

    public void windowButtonsFunctionallity(ActionEvent s) {

        Button pressedB = (Button) s.getSource();

        Stage currentStage = (Stage) pressedB.getScene().getWindow();

        if (pressedB.getId().contains("minimize")) {

            // Instance of the animation
            AnimationFX fx = new ZoomOut(mainScreen);

            // Speed of the animation
            fx.setSpeed(1.5);

            // Reset the finish state after plays the animation
            fx.setResetOnFinished(true);
            fx.setOnFinished(_ -> currentStage.setIconified(true));

            // Playing the animation
            fx.play();

        } else if (pressedB.getId().contains("maximize")) {

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            currentStage.setX(bounds.getMinX());
            currentStage.setY(bounds.getMinY());
            currentStage.setWidth(bounds.getWidth());
            currentStage.setHeight(bounds.getHeight());

            pressedB.setId("normalize");

            maximizeButton.setGraphic(normalizeIMAGE);


        } else if (pressedB.getId().contains("normalize")) {


            currentStage.setWidth(800);
            currentStage.setHeight(500);

            // Center the app when normalized

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

            currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
            currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);


            // Set default behavior of the maximize state

            pressedB.setId("maximize");
            maximizeButton.setGraphic(maximizeIMAGE);


        } else {

            AnimationFX animation = new FadeOut(mainScreen);
            animation.setSpeed(1);

            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.8), _ -> currentStage.close()));
            delay.play();

            animation.play();

        }

    }






    // Design methods (Like animations, fonts, etc).

    public void onHover(MouseEvent s){

        AnimationFX animation = new Pulse((Button)s.getSource());
        animation.setSpeed(2);
        animation.setCycleCount(1);
        animation.play();

    }
    public void setFonts(){

        for(int i=0; i<laterald.getChildren().size(); i++){
            Button currentB=(Button)laterald.getChildren().get(i);
            currentB.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/bison.ttf"), 14));
        }

        titulo1.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Gobold.otf"), 20));
        titulo2.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Gobold.otf"), 20));

    }
    public void leftButtonsIcons(){

        // Set icons to left panel buttons

        FontIcon pedidoMaterialIcon = FontIcon.of(BootstrapIcons.CART_FILL);
        pedidoMaterialIcon.setIconColor(Color.WHITE);
        pedidoMaterialIcon.setIconSize(16);

        // Add icon to a created button
        pedidomaterial.setGraphic(pedidoMaterialIcon);

        FontIcon speedFeedIcon = FontIcon.of(BootstrapIcons.SPEEDOMETER);
        speedFeedIcon.setIconColor(Color.WHITE);
        speedFeedIcon.setIconSize(16);

        // Add icon to a created button
        velocidadavance.setGraphic(speedFeedIcon);

    }

    // Default initialize settings

    private void initSettings(){

        for(int i=0; i<centrod.getChildren().size(); i++){

            centrod.getChildren().get(i).setManaged(false);
            centrod.getChildren().get(i).setVisible(false);

        }

        herramientasCentro.setVisible(false);
        herramientasCentro.setManaged(false);
        herramientasTorno.setVisible(false);
        herramientasTorno.setManaged(false);


    }


}