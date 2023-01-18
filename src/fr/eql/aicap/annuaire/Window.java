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
import javafx.scene.text.Text;



import java.io.*;
import java.util.List;

import static fr.eql.aicap.annuaire.Main.*;


public class Window extends Application {

    Scene sceneSearch, scene;

    //temporary table
    TableView<Stagiaire> tempTable;

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
        julie.Add(BINARYFILE, binaryTree);
        Refresh_List(binaryTree, table);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ;

        Bin_File Bin_File = new Bin_File();
        BinaryTree binaryTree = Bin_File.From_Txt_To_Bin(TXTFILE, BINARYFILE);
        Bin_File.Add_Children_Addresses_into_Parent_Data(BINARYFILE, binaryTree);

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
        Button buttonExport = new Button("Exporter en PDF");
        Button buttonSearch = new Button("Rechercher");
        Button buttonConnexion = new Button("Se connecter");

        //go to scene Rechercher
        buttonSearch.setOnAction(e -> primaryStage.setScene(sceneSearch));

        hbBtn.getChildren().addAll(buttonAdd, buttonExport, buttonSearch, buttonConnexion);

        //action bouton se connecter

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Btn_Stagiaire_Add_Click(event, binaryTree, table);
            }
        });

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {

            TextField dataLogin = new TextField();

            @Override
            public void handle(ActionEvent event) {

                GridPane windowCoGrille = new GridPane();
                Scene windowCoScene = new Scene(windowCoGrille, 500, 300);

                // Nouvelle Fenêtre (Stage)
                Stage windowCo = new Stage();
                windowCo.setTitle("Ajouter un stagiaire");
                windowCo.setScene(windowCoScene);

                // GridPane Layout
                windowCoGrille.setAlignment(Pos.CENTER);
                windowCoGrille.setHgap(10);
                windowCoGrille.setVgap(10);
                windowCoGrille.setPadding(new Insets(20, 20, 20, 20));

                //Remplir la grille

                // Création des labels
                // Création des champs de texte

                Label lblPromo = new Label("Promotion :   ");
                windowCoGrille.add(lblPromo, 0, 1);
                TextField Promo = new TextField();
                windowCoGrille.add(Promo, 1, 1);


                Label lblAnnee = new Label("Année :   ");
                windowCoGrille.add(lblAnnee, 0, 2);
                TextField Annee = new TextField();
                windowCoGrille.add(Annee, 1, 2);


                Label lblPrenom = new Label("Prénom :   ");
                windowCoGrille.add(lblPrenom, 0, 3);
                TextField Prenom = new TextField();
                windowCoGrille.add(Prenom, 1, 3);

                Label lblNom = new Label("Nom :   ");
                windowCoGrille.add(lblNom, 0, 4);
                TextField Nom = new TextField();
                windowCoGrille.add(Nom, 1, 4);

                Label lblDpt = new Label("Département :   ");
                windowCoGrille.add(lblDpt, 0, 5);
                TextField Dpt = new TextField();
                windowCoGrille.add(Dpt, 1, 5);


                //Nouveau bouton

                Button btnValider = new Button("Valider");
                windowCoGrille.add(btnValider, 0, 6);

                Button btnAnnuler = new Button("Annuler");
                windowCoGrille.add(btnAnnuler, 1, 6);


                // Définir la position de la nouvelle fenetre
                //relativement à la fenetre principale.
                windowCo.setX(primaryStage.getX() + 200);
                windowCo.setY(primaryStage.getY() + 100);
                //Affichage de la nouvelle fenêtre
                windowCo.show();
                windowCoScene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());

            }
        });

        buttonConnexion.setOnAction(new EventHandler<ActionEvent>() {

            TextField dataLogin = new TextField();

            @Override
            public void handle(ActionEvent event) {

                GridPane windowCoGrille = new GridPane();
                Scene windowCoScene = new Scene(windowCoGrille, 500, 300);

                // Nouvelle Fenêtre (Stage)
                Stage windowCo = new Stage();
                windowCo.setTitle("Fenêtre de connexion");
                windowCo.setScene(windowCoScene);

                // GridPane Layout
                windowCoGrille.setAlignment(Pos.CENTER);
                windowCoGrille.setHgap(10);
                windowCoGrille.setVgap(10);
                windowCoGrille.setPadding(new Insets(20, 20, 20, 20));

                //Remplir la grille
                Text titre = new Text("Connectez-vous");
                titre.setId("titreText"); //noeud pour CSS
                windowCoGrille.add(titre, 1, 0, 2, 1);


                Label labelLogin = new Label("Login");
                windowCoGrille.add(labelLogin, 0, 1);
                TextField textLogin = new TextField();
                windowCoGrille.add(textLogin, 1, 1);

                Label labelPassword = new Label("Password");
                windowCoGrille.add(labelPassword, 0, 2);
                TextField textPassword = new TextField();
                windowCoGrille.add(textPassword, 1, 2);

                //Nouveau bouton

                Button btnCo = new Button("Connexion");
                windowCoGrille.add(btnCo, 1, 3);
//                btnCo.setOnAction(event1 -> mainPage.run());


                // Définir la position de la nouvelle fenetre
                //relativement à la fenetre principale.
                windowCo.setX(primaryStage.getX() + 200);
                windowCo.setY(primaryStage.getY() + 100);
                //Affichage de la nouvelle fenêtre
                windowCo.show();
                windowCoScene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
            }
        });

        // RECHERCHER ---------------------------------------------------------------------------------

        //search button
        Button buttonRecherche = new Button("Rechercher");
        buttonRecherche.setOnAction(e -> buttonRechercheClicked());

        //return button
        Button buttonReturn = new Button("Return");
        buttonReturn.setOnAction(e -> primaryStage.setScene(scene));

        //search box _nom

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom");
        nomTextField.setMinWidth(100);


        //temporary search table
        tempTable = new TableView<>();
        tempTable.setItems(getStagiaire());

        // ajout des colonnes à la table
        tempTable.getColumns().addAll(promoCol, nomCol, prenomCol, anneeCol, dptCol);
        tempTable.setItems(Trainees_List);


        HBox layoutSearch = new HBox();
        layoutSearch.getChildren().addAll(tempTable);

        HBox layoutSearch2 = new HBox(15);
        layoutSearch2.getChildren().addAll(nomTextField, buttonRecherche, buttonReturn);

        VBox layoutRec = new VBox();
        layoutRec.setSpacing(5);
        layoutRec.setPadding(new Insets(10, 10, 10, 10));
        layoutRec.getChildren().addAll(tempTable, layoutSearch, layoutSearch2);

        sceneSearch = new Scene(layoutRec);
        //stage.setScene(sceneSearch);
        primaryStage.show();
        sceneSearch.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        primaryStage.setTitle("Recherche");

        // fin RECHERCHER -----------------------------------------------------------------------------------------------------------------


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(table, hbBtn);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        primaryStage.setTitle("Annuaire SQL");


    }
    private void buttonRechercheClicked() {

        //temporary search list
        ObservableList<Stagiaire> stagiairesSelected, allStagiaires;
        allStagiaires = tempTable.getItems();
        stagiairesSelected = tempTable.getSelectionModel().getSelectedItems();

        stagiairesSelected.forEach(allStagiaires::remove);
    }
    public ObservableList<Stagiaire> getStagiaire(){

        //temporary search list
        ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();


        return stagiaires;
    }
}


