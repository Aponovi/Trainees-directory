package fr.eql.aicap.annuaire;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


    public class WindowAdmin extends Application {

        @Override
        public void start(Stage stageAdmin) throws IOException {


            //Création Table
            TableView<Stagiaire> table = new TableView<Stagiaire>();
            table.setEditable(true);

            Label label = new Label("Liste des stagiaires");

            //Création des 5colonnes

            TableColumn<Stagiaire, String> promoCol =
                    new TableColumn<Stagiaire, String>("Promotion");
            promoCol.setMinWidth(100);
            promoCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, String>("promo"));

            TableColumn<Stagiaire, Integer> anneeCol =
                    new TableColumn<Stagiaire, Integer>("Année");
            anneeCol.setMinWidth(100);
            anneeCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, Integer>("Année")
            );

            TableColumn<Stagiaire, String> nomCol =
                    new TableColumn<Stagiaire, String>("Nom");
            nomCol.setMinWidth(100);
            nomCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, String>("Nom")
            );

            TableColumn<Stagiaire, String> prenomCol =
                    new TableColumn<Stagiaire, String>("Prénom");
            prenomCol.setMinWidth(100);
            prenomCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, String>("Prénom")
            );

            TableColumn<Stagiaire, Integer> dptCol =
                    new TableColumn<Stagiaire, Integer>("Département");
            dptCol.setMinWidth(100);
            dptCol.setCellValueFactory(
                    new PropertyValueFactory<Stagiaire, Integer>("Département")
            );


            // ajout des colonnes à la table

            table.getColumns().addAll(promoCol,nomCol,prenomCol, anneeCol,dptCol);

            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_LEFT);
            Button buttonAdd= new Button("Ajouter un stagiaire");
            Button buttonDelete= new Button("Supprimer un stagiaire");
            Button buttonExport= new Button("Exporter en PDF");
            Button buttonSearch= new Button("Rechercher");
            Button buttonUpdate= new Button("Modifier mes identifiants");


            hbBtn.getChildren().addAll(buttonAdd, buttonExport, buttonSearch, buttonDelete, buttonUpdate);



            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10 ,10,10,10));
            vbox.getChildren().addAll(table,hbBtn);

            Scene scene = new Scene(vbox);
            stageAdmin.setScene(scene);
            stageAdmin.show();
            scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
            stageAdmin.setTitle("Annuaire SQL - User Admin");
        }

    }

