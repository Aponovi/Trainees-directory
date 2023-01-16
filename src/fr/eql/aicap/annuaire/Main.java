package fr.eql.aicap.annuaire;


import java.io.*;
import java.text.Normalizer;

public class Main {
    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";
    private static final String BINARYFILE = "files\\stagiaires.bin";
    private static final String TXTFILE = "C:\\Users\\Formation\\Documents\\Projects\\Trainees_directory\\stagiaires.txt";



    public static void main(String[] args) {


        //From_Txt_To_Bin(Chemin,Nom_Binaire)

        Bin_File stagiaires = new Bin_File();
        stagiaires.From_Txt_To_Bin(TXTFILE, BINARYFILE);

        //Creer_Modifier_Noeud(Nom_Binaire)

        //Ajouter_Stagiaire(Nom_Binaire,Stagiaire)

        //Modifier_Stagiaire(Nom_Binaire,Stagiaire)

        //Supprimer_Stagiaire(Nom_Binaire,Stagiaire)

        //Rechercher_Stagiaire(Nom_Binaire,Stagiaire_A_Rechercher)

        BinaryTree theTree = new BinaryTree();

        stagiaires.Add_Children_Addresses_into_Parent_Data(BINARYFILE);

        stagiaires.Visual_Check_of_binary_tree(BINARYFILE);

        stagiaires.Select_Trainee_Data(BINARYFILE,0);

        System.out.println(stagiaires.Select_Trainee_Data(BINARYFILE,0));



    }



}


