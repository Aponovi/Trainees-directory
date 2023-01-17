package fr.eql.aicap.annuaire;

import java.io.File;

public class Main {
    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";
    public static final String BINARYFILE = "files\\stagiaires.bin";
    private static final String TXTFILE = "C:\\Users\\Formation\\Documents\\Projects\\Trainees_directory\\stagiaires.txt";



    public static void main(String[] args) {

        File folder = new File(FOLDER);
        folder.mkdir();

        //From_Txt_To_Bin(Chemin,Nom_Binaire)

        Bin_File Bin_File = new Bin_File();
        Bin_File.From_Txt_To_Bin(TXTFILE, BINARYFILE);

        //Creer_Modifier_Noeud(Nom_Binaire)

        //Ajouter_Stagiaire(Nom_Binaire,Stagiaire)

        //Modifier_Stagiaire(Nom_Binaire,Stagiaire)

        //Supprimer_Stagiaire(Nom_Binaire,Stagiaire)

        //Rechercher_Stagiaire(Nom_Binaire,Stagiaire_A_Rechercher)

        Bin_File.Add_Children_Addresses_into_Parent_Data(BINARYFILE);
        // Bin_File.Visual_Check_of_binary_tree(BINARYFILE);

        //stagiaires.Select_Trainee(BINARYFILE,0);
        // Stagiaire.GetSelect(BINARYFILE,0);


        // Stagiaire.Trainees_List(BINARYFILE, Bin_File.theTree);

        // System.out.println(Stagiaire.Trainees_List(BINARYFILE, Bin_File.theTree));


        Stagiaire julie = new Stagiaire("ai cap1", "2023", "juju", "julie", "92");
        julie.Add(BINARYFILE);

        Bin_File.Visual_Check_of_binary_tree(BINARYFILE);

        Stagiaire.Trainees_List(BINARYFILE, Bin_File.theTree);
        System.out.println(Stagiaire.Trainees_List(BINARYFILE, Bin_File.theTree));



    }



}


