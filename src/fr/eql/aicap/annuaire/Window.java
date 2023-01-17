package fr.eql.aicap.annuaire;


import javafx.application.Application;
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
import javafx.scene.control.*;
import javafx.collections.FXCollections;


public class Window extends Application {

    Scene sceneSearch;

    //temporary table
    TableView<Stagiaire> tempTable;

    @Override
    public void start(Stage stage) throws IOException {


        // ObservableList<Stagiaires> data = getStagiairesList();

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
        // table.setItems(data);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        Button button1= new Button("Ajouter un stagiaire");
        Button button2= new Button("Exporter en PDF");

        //go to scene Rechercher
        Button button3= new Button("Rechercher");
        button3.setOnAction(e -> stage.setScene(sceneSearch));

        Button button4= new Button("Se connecter");

        hbBtn.getChildren().addAll(button1, button2, button3, button4);


        // scene RECHERCHER --------------------------------------------------------

        //temporary table

        TableColumn<Stagiaire, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setMinWidth(200);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Stagiaire, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setMinWidth(100);
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Stagiaire, String> anneeColumn = new TableColumn<>("Annee");
        anneeColumn.setMinWidth(100);
        anneeColumn.setCellValueFactory(new PropertyValueFactory<>("annee"));

        //search button
        Button buttonRecherche = new Button("Rechercher");
        buttonRecherche.setOnAction(e -> buttonRechercheClicked());

        //search boxes
        TextField prenomTextField = new TextField();
        prenomTextField.setPromptText("Prénom");
        prenomTextField.setMinWidth(100);

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom");
        nomTextField.setMinWidth(100);

        /*TextField departementTextField = new TextField();
        departementTextField.setPromptText("Département");
        departementTextField.setMinWidth(100);

        TextField promoTextField = new TextField();
        promoTextField.setPromptText("Promotion");
        promoTextField.setMinWidth(100);*/

        TextField anneeTextField = new TextField();
        anneeTextField.setPromptText("Année");
        anneeTextField.setMinWidth(100);

        //temporary search table
        tempTable = new TableView<>();
        tempTable.setItems(getStagiaire());
        tempTable.getColumns().addAll(nomColumn, prenomColumn, anneeColumn);


        HBox layoutSearch = new HBox();
        layoutSearch.setPadding(new Insets(10,10,10,10));
        layoutSearch.setSpacing(10);
        layoutSearch.getChildren().addAll(tempTable,prenomTextField,nomTextField,anneeTextField, buttonRecherche);


        VBox layoutRec = new VBox();
        sceneSearch = new Scene(layoutRec, 600, 300);
        layoutRec.getChildren().addAll(tempTable, layoutSearch);


        // fin RECHERCHER -----------------------------------------------------------------------------------------------------------------

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

    private void buttonRechercheClicked() {
        ObservableList<Stagiaire> stagiairesSelected, allStagiaires;
        allStagiaires = tempTable.getItems();
        stagiairesSelected = tempTable.getSelectionModel().getSelectedItems();

        stagiairesSelected.forEach(allStagiaires::remove);
    }

    public ObservableList<Stagiaire> getStagiaire(){

        ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();

        //String nom, String prenom, String annee
        stagiaires.add(new Stagiaire("CUVILLIER", "Leonard", "2016"));
        stagiaires.add(new Stagiaire("BENNIS","Amaniyy","2017"));
        stagiaires.add(new Stagiaire("MOLINA","Giuliana","2017"));

        return stagiaires;
    }

//    private ObservableList<Stagiaires> getStagiairesList(){
//        LesStagiaires lesStag = new LesStagiaires("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
//        List<Stagiaires> liste = lesStag.fabriqueVecteur();
//        ObservableList<Stagiaires> list = FXCollections.observableArrayList(liste);
//
//        return list;
//
//
//    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

