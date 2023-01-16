package fr.eql.aicap.annuaire;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;


public class Window extends Application {


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
        Button button1= new Button("Ajouter un stagiaire");
        Button button2= new Button("Exporter en PDF");
        Button button3= new Button("Rechercher");
        Button button4= new Button("Se connecter");

        hbBtn.getChildren().addAll(button1, button2, button3, button4);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10 ,10,10,10));
        vbox.getChildren().addAll(table,hbBtn);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        stage.setTitle("Annuaire SQL");
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

