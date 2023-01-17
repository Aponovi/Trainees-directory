package fr.eql.aicap.annuaire;

import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


    public class WindowAdmin extends Application {


        @Override
        public void start(Stage stage) throws IOException {


//        ObservableList<Stagiaire> data = getStagiairesList();

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
//        table.setItems(data);

            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_LEFT);
            Button buttonAdd= new Button("Ajouter un stagiaire");
            Button buttonDelete= new Button("Supprimer un stagiaire");
            Button buttonExport= new Button("Exporter en PDF");
            Button buttonSearch= new Button("Rechercher");
            Button buttonUpdate= new Button("Modifier mes identifiants");

//            Button buttonConnexion= new Button("Se connecter");

            hbBtn.getChildren().addAll(buttonAdd, buttonExport, buttonSearch, buttonDelete, buttonUpdate);

            //action bouton se connecter

//            buttonConnexion.setOnAction(new EventHandler<ActionEvent>() {
//
//                TextField dataLogin = new TextField();
//                @Override
//                public void handle(ActionEvent event) {
//
//                    GridPane windowCoGrille = new GridPane();
//                    Scene windowCoScene = new Scene(windowCoGrille, 500, 300);
//
//                    // Nouvelle Fenêtre (Stage)
//                    Stage windowCo = new Stage();
//                    windowCo.setTitle("Fenêtre de connexion");
//                    windowCo.setScene(windowCoScene);
//
//                    // GridPane Layout
//                    windowCoGrille.setAlignment(Pos.CENTER);
//                    windowCoGrille.setHgap(10);
//                    windowCoGrille.setVgap(10);
//                    windowCoGrille.setPadding(new Insets(20,20,20,20));
//
//                    //Remplir la grille
//                    Text titre = new Text("Connectez-vous");
//                    titre.setId("titreText"); //noeud pour CSS
//                    windowCoGrille.add(titre, 1,0,2,1);
//
//
//                    Label labelLogin = new Label("Login");
//                    windowCoGrille.add(labelLogin, 0, 1);
//                    TextField textLogin = new TextField();
//                    windowCoGrille.add(textLogin, 1, 1);
//
//                    Label labelPassword = new Label("Password");
//                    windowCoGrille.add(labelPassword, 0, 2);
//                    TextField textPassword = new TextField();
//                    windowCoGrille.add(textPassword, 1, 2);
//
//                    //Nouveau bouton
//
//                    Button btnCo = new Button("Connexion");
//                    windowCoGrille.add(btnCo, 1,3);
//
//
//                    // Définir la position de la nouvelle fenetre
//                    //relativement à la fenetre principale.
//                    windowCo.setX(stage.getX() + 200);
//                    windowCo.setY(stage.getY() + 100);
//                    //Affichage de la nouvelle fenêtre
//                    windowCo.show();
//                    windowCoScene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
//
//
//
//                }
//            });

            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10 ,10,10,10));
            vbox.getChildren().addAll(table,hbBtn);

            Scene scene = new Scene(vbox);
            stage.setScene(scene);
            stage.show();
            scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
            stage.setTitle("Annuaire SQL - User Admin");
        }

//    private ObservableList<Stagiaire> getStagiairesList(){
////        LesStagiaires lesStag = new LesStagiaires("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
////        List<Stagiaire> liste = lesStag.fabriqueVecteur();
//          ObservableList<Stagiaire> list = FXCollections.observableArrayList(liste);
//
//        return list;
//
//
//    }

        public static void main(String[] args) {
            Application.launch(args);
        }

    }

