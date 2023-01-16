package fr.eql.aicap.annuaire;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;



public class Window extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        ObservableList<Stagiaires> data = getStagiairesList();

        //Création Table
        TableView<Stagiaires> table = new TableView<Stagiaires>();
        table.setEditable(true);

        //Création des 5colonnes

        TableColumn<Stagiaires, String> promoCol =
                new TableColumn<Stagiaires, String>("Promotion");
        promoCol.setMinWidth(100);

        TableColumn<Stagiaires, Integer> anneeCol =
                new TableColumn<Stagiaires, Integer>("Année");
        anneeCol.setMinWidth(100);
        TableColumn<Stagiaires, String> nomCol =
                new TableColumn<Stagiaires, String>("Nom");
        nomCol.setMinWidth(100);
        TableColumn<Stagiaires, String> prenomCol =
                new TableColumn<Stagiaires, String>("Prénom");
        prenomCol.setMinWidth(100);

        TableColumn<Stagiaires, Integer> dptCol =
                new TableColumn<Stagiaires, Integer>("Département");
        dptCol.setMinWidth(100);

        // ajout des colonnes à la table

        table.getColumns().addAll(promoCol,nomCol,prenomCol, anneeCol,dptCol);
        table.setItems(data);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        Button button1= new Button("Ajouter un stagiaire");
        Button button2= new Button("Exporter en PDF");
        Button button3= new Button("Rechercher");

        Button button4= new Button("Se connecter");
        button4.setTranslateX(50);
        button4.setTranslateY(30);

        hbBtn.getChildren().addAll(button1, button2, button3, button4);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.getChildren().addAll(table);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        stage.setTitle("Annuaire SQL");
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Stagiaires> getStagiairesList(){
        Stagiaires stagiaire1 = new Stagiaires("BLOB 56", 2008, "KANAAN", "Suhaila", 94);
        Stagiaires stagiaire2 = new Stagiaires("BLOB 56", 2008, "FIORE", "Prassede", 94);
        Stagiaires stagiaire3 = new Stagiaires("BLOB 56", 2008, "BEATRICE", "Rita" ,13);
        ObservableList<Stagiaires> list = FXCollections.observableArrayList(stagiaire1, stagiaire2, stagiaire3);

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

