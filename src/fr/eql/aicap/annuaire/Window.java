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

import static fr.eql.aicap.annuaire.Main.BINARYFILE;
import static fr.eql.aicap.annuaire.Main.TXTFILE;

public class Window extends Application {
    Scene sceneSearch, scene;
    //temporary table
    //TableView<Stagiaire> tempTable;
    BinaryTree binaryTree;
    ObservableList<Stagiaire> traineesList;
    TableView<Stagiaire> table;
    GridPane gridPaneTop;

    private void refreshList() {
        refreshList(new Stagiaire());
    }

    private void refreshList(Stagiaire stagiaireFilter) {
        List<Stagiaire> data = Stagiaire.Trainees_List(BINARYFILE, stagiaireFilter, binaryTree);
        traineesList = FXCollections.observableArrayList(data);
        table.setItems(traineesList);
        table.refresh();
    }

    private void chargementFichier() throws IOException {
        File f = new File(BINARYFILE);
        if (!f.exists()) {
            Bin_File Bin_File = new Bin_File();
            binaryTree = Bin_File.fromTxtToBin(TXTFILE, BINARYFILE);
            Bin_File.addChildrenAddressesIntoParentData(BINARYFILE, binaryTree);
        }
        else{
            binaryTree = new BinaryTree();
            RandomAccessFile RandomAccessFile = new RandomAccessFile(BINARYFILE, "r");
            int pos = 0;
            String Key ="";
            while (pos<RandomAccessFile.length()) {
                Bin_File.compteurStagiaire += 1;
                int pos_Name = pos + Bin_File.ADRESSBIGINNINGNAME;
                RandomAccessFile.seek(pos_Name);
                for (int i = 0; i < Bin_File.NOM; i++) {
                    Key += RandomAccessFile.readChar();
                }
                binaryTree.addNode(Key, pos);
                pos = pos + Bin_File.LONGUEURSTAGIAIRE;
                Key ="";
            }
            RandomAccessFile.close();
        }
        List<Stagiaire> data = Stagiaire.Trainees_List(BINARYFILE, binaryTree);
        traineesList = FXCollections.observableArrayList(data);
    }

    private void chargementTop() {
        gridPaneTop = new GridPane();
        Label lblFiltre = new Label("Filtre :   ");
        gridPaneTop.add(lblFiltre, 0, 1);
        TextField nomFilter = new TextField();
        nomFilter.setPromptText("Nom");
        gridPaneTop.add(nomFilter, 1, 1);
        TextField prenomFilter = new TextField();
        prenomFilter.setPromptText("Prénom");
        gridPaneTop.add(prenomFilter, 2, 1);
        TextField promoFilter = new TextField();
        promoFilter.setPromptText("Promotion");
        gridPaneTop.add(promoFilter, 3, 1);
        TextField yearFilter = new TextField();
        yearFilter.setPromptText("Année");
        gridPaneTop.add(yearFilter, 4, 1);
        TextField dptFilter = new TextField();
        dptFilter.setPromptText("Département");
        gridPaneTop.add(dptFilter, 5, 1);

        Button buttonFilter = new Button("Rechercher");
        buttonFilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stagiaire stagiaireFilter = new Stagiaire(promoFilter.getText(),yearFilter.getText(),nomFilter.getText(),prenomFilter.getText(),dptFilter.getText());
                refreshList(stagiaireFilter);
            }

            ;
        });
        gridPaneTop.add(buttonFilter, 9, 1);
    }

    private void chargementList() {
        //Création Table
        table = new TableView<Stagiaire>();
        table.setEditable(true);
        //Label label = new Label("Liste des stagiaires");
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
        table.setItems(traineesList);
    }

    private void chargementFenStagiaire(Stage primaryStage, Stagiaire stagiaireSelected) {
        //Affichage PopUp Stagiaire
        GridPane windowCoGrille = new GridPane();
        Scene windowCoScene = new Scene(windowCoGrille, 500, 300);
        // Nouvelle Fenêtre (Stage)
        Stage windowCo = new Stage();
        if (stagiaireSelected.getNom() == null) {
            windowCo.setTitle("Ajouter un stagiaire");
        } else {
            windowCo.setTitle("Modifier le stagiaire " + stagiaireSelected.getNom());
        }
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
        TextField Promo = new TextField(stagiaireSelected.getPromo());
        windowCoGrille.add(Promo, 1, 1);
        Label lblAnnee = new Label("Année :   ");
        windowCoGrille.add(lblAnnee, 0, 2);
        TextField Annee = new TextField(stagiaireSelected.getAnnee());
        windowCoGrille.add(Annee, 1, 2);
        Label lblPrenom = new Label("Prénom :   ");
        windowCoGrille.add(lblPrenom, 0, 3);
        TextField Prenom = new TextField(stagiaireSelected.getPrenom());
        windowCoGrille.add(Prenom, 1, 3);
        Label lblNom = new Label("Nom :   ");
        windowCoGrille.add(lblNom, 0, 4);
        TextField Nom = new TextField(stagiaireSelected.getNom());
        if (stagiaireSelected.getNom() != null) {
            Nom.setEditable(false);
        }
        windowCoGrille.add(Nom, 1, 4);
        Label lblDpt = new Label("Département :   ");
        windowCoGrille.add(lblDpt, 0, 5);
        TextField Dpt = new TextField(stagiaireSelected.getDpt());
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
        // fermer la fenetre en cliquant sur annuler
        btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                windowCo.close();
            }
        });
        // Ajouter du nouveau stagiaire à la liste des stagiaires
        btnValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                //Code ajout stagiaire
                Stagiaire stg = new Stagiaire(Promo.getText(), Annee.getText(), Nom.getText(), Prenom.getText(), Dpt.getText());
                String msg_err = stg.verif();
                if (msg_err == "") {
                    if (stagiaireSelected.getNom() == null) {
                        stg.add(BINARYFILE, binaryTree);
                    } else {
                        //stg.setNom(Bin_File.completer(Nom.getText(),Bin_File.NOM));
                        stg.update(BINARYFILE);
                    }
                    refreshList();
                    windowCo.close();
                } else {
                    //Alert msg_err
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Impossible de réaliser l'ajout ou la modification du stagiaire:");
                    alert.setContentText(msg_err);
                    alert.showAndWait();
                }
            }
        });
    }

    private void chargementBouton(Stage primaryStage) {

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        Button buttonAdd = new Button("Ajouter un stagiaire");
        Button buttonUpdate = new Button("Modifier le stagiaire");
        Button buttonExport = new Button("Exporter en PDF");

        //go to scene Rechercher
        Button buttonSearch = new Button("Rechercher");
        buttonSearch.setOnAction(e -> primaryStage.setScene(sceneSearch));

        Button buttonConnexion = new Button("Se connecter");

        //go to scene Rechercher
        buttonSearch.setOnAction(e -> primaryStage.setScene(sceneSearch));
        hbBtn.getChildren().addAll(buttonAdd, buttonUpdate, buttonExport, buttonConnexion);

        //action bouton se connecter

        /*buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnStagiaireAddClick(event,binaryTree,table);
            }
        });*/

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chargementFenStagiaire(primaryStage, new Stagiaire());
            }

            ;
        });
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (table.getSelectionModel() != null && table.getSelectionModel().getSelectedItem() != null) {
                    Stagiaire stagiaireSelected = table.getSelectionModel().getSelectedItem();
                    chargementFenStagiaire(primaryStage, stagiaireSelected);
                }
            }

            ;
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
                Button btnAnnuler = new Button("Annuler");
                windowCoGrille.addRow(3, btnCo, btnAnnuler);
                btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        windowCo.close();
                    }
                });
                // Définir la position de la nouvelle fenetre
                //relativement à la fenetre principale.
                windowCo.setX(primaryStage.getX() + 200);
                windowCo.setY(primaryStage.getY() + 100);
                //Affichage de la nouvelle fenêtre
                windowCo.show();
                windowCoScene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
            }
        });
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(gridPaneTop, table, hbBtn);
        scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        primaryStage.setTitle("Annuaire EQL");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        chargementFichier();
        chargementTop();
        chargementList();
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
        //tempTable = new TableView<>();
        //tempTable.setItems(getStagiaire());

        // ajout des colonnes à la table
        //tempTable.getColumns().addAll(promoCol, nomCol, prenomCol, anneeCol, dptCol);
        //tempTable.setItems(Trainees_List);

        HBox layoutSearch = new HBox();
        //layoutSearch.getChildren().addAll(tempTable);

        HBox layoutSearch2 = new HBox(15);
        layoutSearch2.getChildren().addAll(nomTextField, buttonRecherche, buttonReturn);

        VBox layoutRec = new VBox();
        layoutRec.setSpacing(5);
        layoutRec.setPadding(new Insets(10, 10, 10, 10));
        //layoutRec.getChildren().addAll(tempTable, layoutSearch, layoutSearch2);

        sceneSearch = new Scene(layoutRec);
        //stage.setScene(sceneSearch);
        primaryStage.show();
        sceneSearch.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        primaryStage.setTitle("Recherche");
        // fin RECHERCHER -----------------------------------------------------------------------------------------------------------------

        chargementBouton(primaryStage);

    }

    private void buttonRechercheClicked() {
        //temporary search list
        ObservableList<Stagiaire> stagiairesSelected, allStagiaires;
        //allStagiaires = tempTable.getItems();
        //stagiairesSelected = tempTable.getSelectionModel().getSelectedItems();
        //stagiairesSelected.forEach(allStagiaires::remove);
    }

    public ObservableList<Stagiaire> getStagiaire() {
        //temporary search list
        ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();
        return stagiaires;
    }
}


