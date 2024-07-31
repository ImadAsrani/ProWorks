package com.ProWorks;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeOut;
import com.ProWorks.classes.Materials;
import com.ProWorks.classes.middleClass;
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
import java.sql.*;
import java.util.ArrayList;

public class addMaterialController {

    private final ArrayList<Materials> materialList=new ArrayList<>();

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

        recoverMaterialsFromDB();

        boolean valid=false;

           if(materialName.getLength()>10){

               addingMaterialErrors.setText("* El nombre del material no puede ser mayor de 10 caracteres");

           } else if(materialName.getLength()<3){

               addingMaterialErrors.setText("* El nombre del material no puede ser menor de 3 caracteres");

           } else if(materialHardness.getLength()<1){

               addingMaterialErrors.setText("* El valor dureza del material es obligatorio");

           } else if(materialHardness.getLength()>4){

               addingMaterialErrors.setText("* La dureza no puede ser mayor a 9999");


           }else if(materialHardness.getLength()>=1 || materialHardness.getLength()<=4){

               try {

                   int hardness=Integer.parseInt(materialHardness.getText());

                   if(hardness<=0){
                       addingMaterialErrors.setText("* La dureza debe ser mayor de 0");
                   } else {

                       if(!materialList.isEmpty()){

                           for(int i=0; i<materialList.size(); i++){

                               if(materialList.get(i).getName().equalsIgnoreCase(materialName.getText())){

                                   addingMaterialErrors.setText("* No pueden existir dos materiales con el mismo nombre");

                               } else {
                                   valid=true;
                                   break;
                               }

                           }

                       } else {

                           valid=true;

                       }



                   }


               }catch (Exception s){

                   addingMaterialErrors.setText("* La dureza solo puede ser un número entero");

               }


           } else {

               valid=true;

           }

            // If the variable valid is true add the material to the DB

           if(valid){

               // Recover materials to the arraylist materialList

               int id=0;

               if(!materialList.isEmpty()){

                   id=materialList.getLast().getID();
                   id++;

               }

               // instance of the successfully added material
               Materials instanceM=new Materials(id, materialName.getText(), Integer.parseInt(materialHardness.getText()), materialColor.getValue());

               // Adding the instance of the new added material to the list
               materialList.add(instanceM);

               addMaterialtoDB();

               // Traspass the instance of the new material to the ControllSuccessfulAddedMaterial
               middleClass.getInstance().getInstanceOfGoalClass().getInfoAboutMaterial(instanceM);

               Stage stage = (Stage) materialName.getScene().getWindow();
               FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("/com/ProWorks/ProWorks/SmallScenes/materialAddedSuccessful.fxml"));
               Scene scene = new Scene(fxmlLoader.load(), Color.TRANSPARENT);
               stage.setScene(scene);
               stage.show();

               // Auto close the success added window

               Timeline delay = new Timeline(new KeyFrame(Duration.seconds(4), _ -> stage.close()));

               delay.play();

           }

    }

    private void windowButtonsDesign(){

        ImageView closeIMAGE = new ImageView(new Image("file:src/main/resources/com/ProWorks/ProWorks/Images/WindowButtons/close.png"));

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


    private void addMaterialtoDB() throws SQLException {

        Connection conn=DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/ProWorks/ProWorks/db/proworks.db");

        Statement stat=conn.createStatement();

        String query="insert into materials values("+materialList.getLast().getID()+",'"+materialList.getLast().getName()+"',"+materialList.getLast().getHardness()+",'"+materialList.getLast().getColor()+"');";

        stat.execute(query);


    }
    public void removeMaterialFromDB(String matName) throws SQLException {

        Connection conn=DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/ProWorks/ProWorks/db/proworks.db");

        Statement stat=conn.createStatement();

        String query="delete from materials where name='"+matName+"';";

        stat.execute(query);


    }

    public ArrayList<Materials> recoverMaterialsFromDB() {

        try {

            Connection conn=DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/ProWorks/ProWorks/db/proworks.db");

            Statement stat=conn.createStatement();

            ResultSet res=stat.executeQuery("select * from materials");

            while(res.next()){

                materialList.add(new Materials(res.getInt(1), res.getString(2), res.getInt(3), Color.valueOf(res.getString(4))));

            }

        }catch (SQLException s){

            System.out.println(s.getMessage());

        }

        return materialList;


    }
    

}
