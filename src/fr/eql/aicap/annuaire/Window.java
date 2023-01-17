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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;
import java.util.List;
import java.util.Vector;

import static fr.eql.aicap.annuaire.Main.LONGUEURSTAGIAIRE;


public class Window extends Application {

    private static final String PATH = "C:\\Users\\Formation\\Desktop\\PROJE1\\Trainees-directory\\stagiaires.txt";

    Scene sceneRecherche;
    Button buttonRecherche;

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

        //Rechercher
        Button button3= new Button("Rechercher");
        button3.setOnAction(e -> stage.setScene(sceneRecherche));

        Button button4= new Button("Se connecter");

        hbBtn.getChildren().addAll(button1, button2, button3, button4);

        // RECHERCHER -----------------------------------------------------------------------------------------------------------------

        //Creation des zones de textes et boutons

        TextField prenomTextField = new TextField();
        prenomTextField.setPromptText("Prénom");

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom");

        TextField departementTextField = new TextField();
        departementTextField.setPromptText("Département");

        TextField promoTextField = new TextField();
        promoTextField.setPromptText("Promotion");

        TextField annéeTextField = new TextField();
        annéeTextField.setPromptText("Année");

        TextField resultatRechercheTextField = new TextField();
        annéeTextField.setPromptText("Année ");

        nomTextField.setMaxWidth(170);
        prenomTextField.setMaxWidth(170);
        departementTextField.setMaxWidth(170);
        promoTextField.setMaxWidth(170);
        annéeTextField.setMaxWidth(170);

        //scene2
        buttonRecherche = new Button("Rechercher");
        buttonRecherche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nomR = nomCol.getText();
                RandomAccessFile raf = null;
                try {
                    raf = new RandomAccessFile(PATH, "rw");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                List<Stagiaires> StagiaireTrouve = rechercheStagiaires(0, 1317, raf, nomR);
                table.getItems().clear();
                table.setItems((FXCollections.observableArrayList(StagiaireTrouve)));
            }
        });


        //creation scene recherche
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(buttonRecherche, prenomTextField);
        sceneRecherche = new Scene(layout2, 600, 300);
        
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

    private ObservableList<Stagiaires> getStagiairesList(){
        LesStagiaires lesStag = new LesStagiaires(PATH);
        List<Stagiaires> liste = lesStag.fabriqueVecteur();
        ObservableList<Stagiaires> list = FXCollections.observableArrayList(liste);

        return list;
    }

    // RECHERCHER -----------------------------------------------------------------------------------------------------------------

    public static List<Stagiaires> rechercheStagiaires(int borneInf, int borneSup, RandomAccessFile raf, String nomStagRecherche) {

        int pivot = 0;
        List<Stagiaires> nomStagActuel;

        try {

            if (borneInf <= borneSup) {
                pivot = (borneInf + borneSup) / 2;
                raf.seek(pivot * LONGUEURSTAGIAIRE);
                nomStagActuel = lectureStag(65, raf);
                if (nomStagRecherche.compareToIgnoreCase(nomStagActuel.get(0).getNom()) == 0) {
                    raf.seek(pivot * LONGUEURSTAGIAIRE);
                    System.out.println("Utilisateur trouvé");
                    System.out.println(nomStagActuel.get(0).getNom());
                    System.out.println(nomStagActuel.get(0).getPrenom());
                    System.out.println(nomStagActuel.get(0).getDpt());
                    System.out.println(nomStagActuel.get(0).getAnnee());
                    System.out.println(nomStagActuel.get(0).getPromo());


                    return nomStagActuel;


                } else {

                    if (nomStagRecherche.compareToIgnoreCase(nomStagActuel.get(0).getNom()) < 0) {
                        return rechercheStagiaires(borneInf, pivot - 1, raf, nomStagRecherche);
                    } else {
                        return rechercheStagiaires(pivot + 1, borneSup, raf, nomStagRecherche);
                    }
                }

            } else {

                System.out.println("\r\n***** Stagiaire introuvable *****");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    //////////////////????????

    public static List<Stagiaires> lectureStag(int taillechaine, RandomAccessFile raf){
        boolean fileNotFinished = true;
        List<Stagiaires> listStagiaires = new Vector<>();
        String chaine = "";
        while (fileNotFinished){
            chaine ="";
            for (int i = 0; i < taillechaine; i++) {
                try {
                    chaine += raf.readChar();
                }catch (EOFException e){
                    fileNotFinished=false;
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                chaine = chaine  + raf.readInt() ;
            }catch (EOFException e){
                fileNotFinished = false;
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(chaine);
            LesStagiaires ls = new LesStagiaires();
            listStagiaires.add(ls.fabriqueStagiaire(chaine));

        }
        return listStagiaires;
    }

    // fin RECHERCHER -----------------------------------------------------------------------------------------------------------------


    public static void main(String[] args) {
        Application.launch(args);
    }

}

