package fr.eql.aicap.annuaire;

import java.io.File;


import javafx.application.Application;

public class Main {
    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";
    public static final String BINARYFILE = "files/stagiaires.bin";
    public static final String TXTFILE = "files/stagiaires.txt";
    public static BinaryTree binaryTree = null;


    public static void main(String[] args) {

        File folder = new File(FOLDER);
        folder.mkdir();


        Application.launch(Window.class, args);
        //Bin_File.Visual_Check_of_binary_tree(BINARYFILE);
    }
}


