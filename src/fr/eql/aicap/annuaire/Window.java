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
import java.util.List;


public class Window extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        ObservableList<Stagiaires> data = getStagiairesList();

        //Création Table
        TableView<Stagiaires> table = new TableView<Stagiaires>();
        table.setEditable(true);

        Label label = new Label("Liste des stagiaires");

        //Création des 5colonnes

        TableColumn<Stagiaires, String> promoCol =
                new TableColumn<Stagiaires, String>("Promotion");
        promoCol.setMinWidth(100);
        promoCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaires, String>("promo"));

        TableColumn<Stagiaires, Integer> anneeCol =
                new TableColumn<Stagiaires, Integer>("Année");
        anneeCol.setMinWidth(100);
        anneeCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaires, Integer>("Année")
        );

        TableColumn<Stagiaires, String> nomCol =
                new TableColumn<Stagiaires, String>("Nom");
        nomCol.setMinWidth(100);
        nomCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaires, String>("Nom")
        );

        TableColumn<Stagiaires, String> prenomCol =
                new TableColumn<Stagiaires, String>("Prénom");
        prenomCol.setMinWidth(100);
        prenomCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaires, String>("Prénom")
        );

        TableColumn<Stagiaires, Integer> dptCol =
                new TableColumn<Stagiaires, Integer>("Département");
        dptCol.setMinWidth(100);
        dptCol.setCellValueFactory(
                new PropertyValueFactory<Stagiaires, Integer>("Département")
        );


        // ajout des colonnes à la table

        table.getColumns().addAll(promoCol,nomCol,prenomCol, anneeCol,dptCol);
        table.setItems(data);

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

    private ObservableList<Stagiaires> getStagiairesList(){
        LesStagiaires lesStag = new LesStagiaires("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
        List<Stagiaires> liste = lesStag.fabriqueVecteur();
        ObservableList<Stagiaires> list = FXCollections.observableArrayList(liste);

        return list;


    }




//        button1.setOnAction(event -> {
//            FileReader in = null;
//            try {
//                in = new FileReader("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            BufferedReader br = new BufferedReader(in);
//            StringBuffer sb = new StringBuffer();
//            String line;
//
//
//        });





    public static void main(String[] args) {
        Application.launch(args);
    }

}

