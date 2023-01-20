package fr.eql.aicap.annuaire;

import java.io.File;


import javafx.application.Application;

public class Main {
    private static final String FOLDER = "files/";
    public static final String BINARYFILE = "files/stagiaires.bin";
    public static final String TXTFILE = "files/stagiaires.txt";

    public static void main(String[] args) {
        File folder = new File(FOLDER);
        folder.mkdir();
        Application.launch(Window.class, args);
    }
}


