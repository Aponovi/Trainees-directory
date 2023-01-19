package fr.eql.aicap.annuaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Stagiaire() {

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

    public void add(String fichierBinaire, BinaryTree binaryTree) {
        //Méthode qui ajoute dans le fichier bin le stagiaire this
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fichierBinaire, "rw");
            int focusTrainee = Bin_File.compteurStagiaire * Bin_File.LONGUEURSTAGIAIRE;
            randomAccessFile.seek(focusTrainee);
            // System.out.println("Avant l'écriture le pointeur se situe sur la position : " + RandomAccessFile.getFilePointer());
            int leftchild = Integer.parseInt(String.format("%0" + Integer.numberOfLeadingZeros(1) + "d", 1));
            int rightchild = Integer.parseInt(String.format("%0" + Integer.numberOfLeadingZeros(1) + "d", 1));
            randomAccessFile.writeInt(leftchild);
            randomAccessFile.writeInt(rightchild);
            this._promo = Bin_File.completer(this._promo, Bin_File.PROMO);
            randomAccessFile.writeChars(this._promo);
            this._annee = Bin_File.completer(this._annee, Bin_File.ANNEE);
            randomAccessFile.writeChars(this._annee);
            this._nom = Bin_File.completer(this._nom, Bin_File.NOM);
            randomAccessFile.writeChars(this._nom);
            this._prenom = Bin_File.completer(this._prenom, Bin_File.PRENOM);
            randomAccessFile.writeChars(this._prenom);
            this._dpt = Bin_File.completer(this._dpt, Bin_File.DEPARTEMENT);
            randomAccessFile.writeChars(this._dpt);
            randomAccessFile.close();
            binaryTree.addNode(this._nom, focusTrainee);
            Bin_File.compteurStagiaire += 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String fichierBinaire, int pointerPosition) {
        //Méthode qui supprime dans le fichier bin le stagiaire this
    }

    public void update(String fichierBinaire) {
        //Méthode qui modifie dans le fichier bin le stagiaire this
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fichierBinaire, "rw");
            int focusTrainee = BinaryTree.findNode(Bin_File.completer(this._nom, Bin_File.NOM)).address;
            // RandomAccessFile.seek(focusTrainee);
            // System.out.println("Avant l'écriture le pointeur se situe sur la position : " + RandomAccessFile.getFilePointer());
            randomAccessFile.seek(focusTrainee + Bin_File.RIGHTCHILD * 4 + Bin_File.LEFTCHILD * 4);
            this._promo = Bin_File.completer(this._promo, Bin_File.PROMO);
            randomAccessFile.writeChars(this._promo);
            this._annee = Bin_File.completer(this._annee, Bin_File.ANNEE);
            randomAccessFile.writeChars(this._annee);
            int pointer = (int) randomAccessFile.getFilePointer();
            focusTrainee = pointer + Bin_File.NOM * 2;
            randomAccessFile.seek(focusTrainee);
            this._prenom = Bin_File.completer(this._prenom, Bin_File.PRENOM);
            randomAccessFile.writeChars(this._prenom);
            this._dpt = Bin_File.completer(this._dpt, Bin_File.DEPARTEMENT);
            randomAccessFile.writeChars(this._dpt);
            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static List<Stagiaire> Trainees_List(String fichier_Binaire, BinaryTree binaryTree) {

        return Trainees_List(fichier_Binaire, "", binaryTree);
    }

    public static List<Stagiaire> Trainees_List(String fichier_Binaire, String Nom_Filtre, BinaryTree binaryTree) {
        //Méthode qui liste tous les stagiaires du fichier binaire et qui les renvoie
        List<Stagiaire> Trainees_List = new ArrayList<>();
        BinaryTree.inOrderTraverseTree_List(binaryTree.root, Trainees_List, fichier_Binaire);
        // System.out.println("la list dans la fonction " + Trainees_List);
        if (Nom_Filtre != "") {
            return Trainees_List.stream().filter(c -> c._nom.contains(Nom_Filtre)).collect(Collectors.toList());
        } else {
            return Trainees_List;
        }
    }


    public static Stagiaire getSelect(String fichier_Binaire, int pointerPosition) {
        try {
            RandomAccessFile RandomAccessFile = new RandomAccessFile(fichier_Binaire, "r");
            int focusTrainee = pointerPosition + Bin_File.LEFTCHILD * 4 + Bin_File.RIGHTCHILD * 4; // int sur 4 bytes
            String formationStagiaires = "";
            String anneeFormationStagiaire = "";
            String nomStagiaire = "";
            String prenomStagiaire = "";
            String departementStagiaire = "";
            RandomAccessFile.seek(focusTrainee);
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
            formationStagiaires = (formationStagiaires.replaceAll("\\s+", ""));
            anneeFormationStagiaire = (anneeFormationStagiaire.replaceAll("\\s+", ""));
            nomStagiaire = (nomStagiaire.replaceAll("\\s+", ""));
            prenomStagiaire = (prenomStagiaire.replaceAll("\\s+", ""));
            departementStagiaire = (departementStagiaire.replaceAll("\\s+", ""));
            RandomAccessFile.close();
            return new Stagiaire(formationStagiaires, anneeFormationStagiaire, nomStagiaire, prenomStagiaire, departementStagiaire);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String verif() {
        String mess_err = "";
        if (this._promo.length() == 0) {
            mess_err += "La promotion doit être saisie.\n";
        } else if (this._promo.length() > Bin_File.PROMO) {
            mess_err += "La promotion est trop longue.\n";
        }
        if (this._annee.length() == 0) {
            mess_err += "L'année doit être saisie.\n";
        } else if (this._annee.length() > Bin_File.ANNEE) {
            mess_err += "L'année est trop longue.\n";
        }
        if (this._dpt.length() == 0) {
            mess_err += "Le département doit être saisie.\n";
        } else if (this._dpt.length() > Bin_File.DEPARTEMENT) {
            mess_err += "Le département est trop long.\n";
        }
        if (this._nom.length() == 0) {
            mess_err += "Le nom doit être saisie.\n";
        } else if (this._nom.length() > Bin_File.NOM) {
            mess_err += "Le nom est trop long.\n";
        }
        if (this._prenom.length() == 0) {
            mess_err += "Le prénom doit être saisie.\n";
        } else if (this._prenom.length() > Bin_File.PRENOM) {
            mess_err += "Le prénom est trop long.\n";
        }

        return mess_err;
    }
}