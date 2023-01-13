package fr.eql.aicap.annuaire;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Window extends Application {

    @Override
    public void start(Stage stage) {


        //ListView<String> listViewReference = new ListView<String>();

        //adding item to list
//        listViewReference.getItems().add("Promotion");
//        listViewReference.getItems().add("Nom");
//        listViewReference.getItems().add("Prénom");
//        listViewReference.getItems().add("Année");
//        listViewReference.getItems().add("Département");

//        Label promotion = new Label("Promotion");
//        Label nom = new Label("Nom");
//        Label prenom = new Label("Prénom");
//        Label year = new Label("Année");
//        Label dpt = new Label("Département");

//        listViewReference.setOrientation(Orientation.HORIZONTAL);

        //TextArea liste = new TextArea();
        Button button1= new Button("Afficher l'annuaire");
//        button1.setOnAction(event ->{
//        try
//        {
//            FileReader in = new FileReader("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
//            BufferedReader br = new BufferedReader(in);
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while((line = br.readLine()) != null)
//            {
//                sb.append(line);
//                sb.append("\n");
//            }
//            in.close();
//            //liste.setText(sb.toString());
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        });

       BorderPane root = new BorderPane();
//        root.getChildren().addAll(promotion, nom, prenom, year, dpt, button1);
        root.getChildren().addAll(button1);
        root.setPadding(new Insets(20, 20, 20, 20));
        //HBox hbox = new HBox(listViewReference);
        /* creating scene */
        Scene scene = new Scene(root, 800, 200);
        /* adding scene to stage */
        stage.setScene(scene);
        //root.setTop(Label);
        //root.setCenter(liste);
        /* display scene for showing output */
        stage.show();
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        stage.setTitle("Annuaire SQL");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

