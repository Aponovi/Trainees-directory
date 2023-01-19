package fr.eql.aicap.annuaire;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;;
import java.util.List;

import static fr.eql.aicap.annuaire.Main.BINARYFILE;
import static fr.eql.aicap.annuaire.Main.TXTFILE;


public class WindowAdmin extends Application {

//    private Runnable adminSwitch;
//
//    public WindowAdmin(Runnable adminSwitch){
//        this.adminSwitch = adminSwitch;
//    }

    private void Refresh_List(BinaryTree binaryTree, TableView<Stagiaire> table) {
        List<Stagiaire> data = Stagiaire.Trainees_List(BINARYFILE, binaryTree);
        ObservableList<Stagiaire> _Trainees_List = FXCollections.observableArrayList(data);
        table.setItems(_Trainees_List);
        table.refresh();
    }

    private void Btn_Stagiaire_Add_Click(ActionEvent event, BinaryTree binaryTree, TableView<Stagiaire> table) {
        //Affichage PopUp Stagiaire

        //Code ajout stagiaire
        Stagiaire julie = new Stagiaire("ai cap1", "2023", "AAA", "julie", "92");
        julie.add(BINARYFILE, binaryTree);
        Refresh_List(binaryTree, table);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Bin_File Bin_File = new Bin_File();
        BinaryTree binaryTree = Bin_File.fromTxtToBin(TXTFILE, BINARYFILE);
        Bin_File.addChildrenAddressesIntoParentData(BINARYFILE, binaryTree);

        List<Stagiaire> data = Stagiaire.Trainees_List(BINARYFILE, binaryTree);
        ObservableList<Stagiaire> Trainees_List = FXCollections.observableArrayList(data);

        //Création Table
        TableView<Stagiaire> table = new TableView<Stagiaire>();
        table.setEditable(true);

        Label label = new Label("Liste des stagiaires");
        //Création des 5 colonnes
        TableColumn<Stagiaire, String> promoCol =
                new TableColumn<Stagiaire, String>("Promotion");
        promoCol.setMinWidth(100);
        promoCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("promo"));

        TableColumn<Stagiaire, String> anneeCol =
                new TableColumn<Stagiaire, String>("Année");
        anneeCol.setMinWidth(100);
        anneeCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("annee")
        );

        TableColumn<Stagiaire, String> nomCol =
                new TableColumn<Stagiaire, String>("Nom");
        nomCol.setMinWidth(100);
        nomCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("nom")
        );

        TableColumn<Stagiaire, String> prenomCol =
                new TableColumn<Stagiaire, String>("Prénom");
        prenomCol.setMinWidth(100);
        prenomCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("prenom")
        );

        TableColumn<Stagiaire, String> dptCol =
                new TableColumn<Stagiaire, String>("Département");
        dptCol.setMinWidth(100);
        dptCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaire, String>("dpt")
        );

        // ajout des colonnes à la table
        table.getColumns().addAll(promoCol, nomCol, prenomCol, anneeCol, dptCol);
        table.setItems(Trainees_List);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        Button buttonAdd = new Button("Ajouter un stagiaire");
        Button buttonModify = new Button("Modifier un stagiaire");
        Button buttonDelete = new Button("Supprimer un stagiaire");
        Button buttonExport = new Button("Exporter en PDF");
        Button buttonSearch = new Button("Rechercher");
        Button buttonUpdate = new Button("Modifier mes identifiants");
        Button buttonOff = new Button("Se déconnecter");


        hbBtn.getChildren().addAll(buttonAdd, buttonDelete, buttonExport, buttonSearch, buttonUpdate, buttonOff, buttonModify);

        //action bouton se connecter

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Btn_Stagiaire_Add_Click(event, binaryTree, table);
            }
        });

//        buttonOff.setOnAction(evt -> adminSwitch.run());

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(table, hbBtn);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        stage.setTitle("Annuaire SQL");
    }
}






