package fr.eql.aicap.annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
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
    }

    public void Delete(String fichier_Binaire, int pointerPosition) {
        //Méthode qui supprime dans le fichier bin le stagiaire this
    }

    public void Update(String fichier_Binaire, int pointerPosition) {
        //Méthode qui modifie dans le fichier bin le stagiaire this
    }

    public List<Stagiaire> List(String fichier_Binaire) {
        return List(fichier_Binaire, "");
    }

    public List<Stagiaire> List(String fichier_Binaire, String Nom_Filtre) {
        //Méthode qui liste tous les stagiaires du fichier binaire et qui les renvoie
        return null;
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
            return new Stagiaire(formationStagiaires, anneeFormationStagiaire, nomStagiaire, prenomStagiaire, departementStagiaire);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}