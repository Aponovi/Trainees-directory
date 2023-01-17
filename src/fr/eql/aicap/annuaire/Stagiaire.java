package fr.eql.aicap.annuaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Stagiaire {
    private String _promo;
    private String _annee;
    private String _nom;
    private String _prenom;
    private String _dpt;
    RandomAccessFile stagiaires;

    //Constructeurs
    public Stagiaire(String promo, String annee, String nom, String prenom, String dpt) {
        this._promo = promo;
        this._annee = annee;
        this._nom = nom;
        this._prenom = prenom;
        this._dpt = dpt;
    }

    //temporary constructer
    public Stagiaire(String nom, String prenom, String annee) {
        this._annee = annee;
        this._nom = nom;
        this._prenom = prenom;
    }

    //méthodes d'accès aux variables d'instance
    public String getPromo() {
        return _promo;
    }

    public String getAnnee() {
        return _annee;
    }

    public String getNom() {
        return _nom;
    }

    public String getPrenom() {
        return _prenom;
    }

    public String getDpt() {
        return _dpt;
    }

    public void setPromo(String Value) {
        this._promo = Value;
    }

    public void setAnnee(String Value) {
        this._annee = Value;
    }

    public void setNom(String Value) {
        this._nom = Value;
    }

    public void setPrenom(String Value) {
        this._prenom = Value;
    }

    public void setDpt(String Value) {
        this._dpt = Value;
    }

    public String toString() {
        return _promo + " " + _annee + " " + _nom + " " + _prenom + " " + _dpt;
    }

    public void Add(String fichier_Binaire) {
        //Méthode qui ajoute dans le fichier bin le stagiaire this
        try {


            RandomAccessFile RandomAccessFile = new RandomAccessFile(fichier_Binaire, "rw");
            int focusTrainee = Bin_File.compteurStagiaire * Bin_File.LONGUEURSTAGIAIRE;
            System.out.println(focusTrainee);
            // System.out.println("Avant lecture le pointeur se situe sur la position : " + fichier_Binaire.getFilePointer());
            RandomAccessFile.writeInt(Bin_File.LEFTCHILD);
            RandomAccessFile.writeInt(Bin_File.RIGHTCHILD);
            this._promo = Bin_File.completer(this._promo, Bin_File.PROMO);
            RandomAccessFile.writeChars(this._promo);
            this._annee = Bin_File.completer(this._annee, Bin_File.ANNEE);
            RandomAccessFile.writeChars(this._annee);
            this._nom = Bin_File.completer(this._nom, Bin_File.NOM);
            RandomAccessFile.writeChars(this._nom);
            this._prenom = Bin_File.completer(this._prenom, Bin_File.PRENOM);
            RandomAccessFile.writeChars(this._prenom);
            this._dpt = Bin_File.completer(this._dpt, Bin_File.DEPARTEMENT);
            RandomAccessFile.writeChars(this._dpt);




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Delete(String fichier_Binaire, int pointerPosition) {
        //Méthode qui supprime dans le fichier bin le stagiaire this
    }

    public void Update(String fichier_Binaire, int pointerPosition) {
        //Méthode qui modifie dans le fichier bin le stagiaire this
    }

    public static List<Stagiaire> Trainees_List(String fichier_Binaire, BinaryTree theTree) {

        return Trainees_List(fichier_Binaire, "", theTree);
    }

    public static List<Stagiaire> Trainees_List(String fichier_Binaire, String Nom_Filtre, BinaryTree theTree) {
        //Méthode qui liste tous les stagiaires du fichier binaire et qui les renvoie
        int pointer = 0;
        List<Stagiaire> Trainees_List = new ArrayList<Stagiaire>();
        for (int i = 0; i < Bin_File.compteurStagiaire; i++) {
            Stagiaire traineeToAddInList = GetSelect(fichier_Binaire, pointer);
            Trainees_List.add(traineeToAddInList);
            pointer += Bin_File.LONGUEURSTAGIAIRE;
        }

        return Trainees_List;
    }

    public static Stagiaire GetSelect(String fichier_Binaire, int pointerPosition) {
        try {
            RandomAccessFile RandomAccessFile = new RandomAccessFile(fichier_Binaire, "rw");
            int focusTrainee = pointerPosition + 8;
            String formationStagiaires = "";
            String anneeFormationStagiaire = "";
            String nomStagiaire = "";
            String prenomStagiaire = "";
            String departementStagiaire = "";
            RandomAccessFile.seek(focusTrainee);
            // System.out.println("Avant lecture le pointeur se situe sur la position : " + stagiaires.getFilePointer());
            for (int i = 0; i < 50; i++) {
                formationStagiaires += RandomAccessFile.readChar();
            }
            for (int i = 0; i < 4; i++) {
                anneeFormationStagiaire += RandomAccessFile.readChar();
                // System.out.println(anneeFormationStagiaire + "boucle" + i);
            }
            for (int i = 0; i < 40; i++) {
                nomStagiaire += RandomAccessFile.readChar();
            }
            for (int i = 0; i < 40; i++) {
                prenomStagiaire += RandomAccessFile.readChar();
            }
            for (int i = 0; i < 4; i++) {
                departementStagiaire += RandomAccessFile.readChar();
            }
//                System.out.println("stagiaire : "
//                        + formationStagiaires + " "
//                        + anneeFormationStagiaire + ""
//                        + nomStagiaire + ""
//                        + prenomStagiaire + ""
//                        + departementStagiaire);
            RandomAccessFile.close();
            return new Stagiaire(formationStagiaires.replaceAll("\\s+", ""), anneeFormationStagiaire.replaceAll("\\s+", ""), nomStagiaire.replaceAll("\\s+", ""), prenomStagiaire.replaceAll("\\s+", ""), departementStagiaire.replaceAll("\\s+", ""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}