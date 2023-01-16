package fr.eql.aicap.annuaire;


import javafx.application.Application;

import java.io.*;
import java.text.Normalizer;

public class Main {
    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";

    //allocation taille des données pour l'arbre

    public static final int PROMO = 50;
    public static final int ANNEE = 4;
    public static final int NOM = 40;
    public static final int PRENOM = 40;
    public static final int DEPARTEMENT = 4;
    public static final int LEFTCHILD = 20;
    public static final int RIGHTCHILD = 20;


    public static final int LONGUEURSTAGIAIRE = ((PROMO + ANNEE + NOM + PRENOM + DEPARTEMENT + LEFTCHILD + RIGHTCHILD) * 2);


    public static void main(String[] args) throws FileNotFoundException {

        Application.launch(Window.class, args);

        HardBinaryTree theTree = new HardBinaryTree();

        String ligne = "";
        String mot = "";
        int compteurLigne;
        int compteurStagiaire = 0;
        int traineePositionInBinFile = 0;
        int leftChild = 1; // 1 = pas de child
        int rightChild = 1; // 1 = pas de child

        RandomAccessFile stagiaires;

        File folder = new File(FOLDER);
        folder.mkdir();

        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            File fichierOriginal = new File("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
            //System.out.println("fichier original " + fichierOriginal);
            // BufferedReader bf = new BufferedReader(fichierOriginal);
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fichierOriginal),"ISO-8859-1"));
            compteurLigne = 0;

            // ajout du leftchild dans le fichier bin
            String convert = String.format("%0" + Integer.numberOfLeadingZeros(leftChild) + "d", leftChild);
            stagiaires.writeInt(Integer.parseInt(convert));

            // ajout du rightchild dans le fichier bin
            String conver = String.format("%0" + Integer.numberOfLeadingZeros(rightChild) + "d", rightChild);
            stagiaires.writeInt(Integer.parseInt(conver));

            // lecture du fichier txt et recopie dans le fichier bin
            while ((ligne = bf.readLine()) != null) {
                compteurLigne = compteurLigne % 6;
                ligne = stripAccents(ligne);
                mot = ligne;
                switch (compteurLigne) {
                    case 0:
                        mot = completer(mot, PROMO);
                        stagiaires.writeChars(mot);
                        break;
                    case 1:
                        mot = completer(mot, ANNEE);
                        stagiaires.writeChars(mot);
                        break;

                    case 2:
                        mot = completer(mot, NOM);
                        stagiaires.writeChars(mot);
                        theTree.addNode(mot, traineePositionInBinFile);
                        traineePositionInBinFile += 208;
                        break;

                    case 3:
                        mot = completer(mot, PRENOM);
                        stagiaires.writeChars(mot);
                        break;

                    case 4:
                        mot = completer(mot, DEPARTEMENT);
                        stagiaires.writeChars(mot);
                        compteurStagiaire += 1;
                        break;

                }
                compteurLigne += 1;
                System.out.println(NOM);

            }


            stagiaires.close();
            //System.out.println("nb stagiaires : " + compteurStagiaire);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            /* le raf possède une méthode get File Pointer() qui permet de savoir où est placé le pointeur
             * cad là où la lecture ou l'écriture s'effectuera.
             */
//            System.out.println("Avant lecture le pointeur se situe sur la position : " + stagiaires.getFilePointer());
//            System.out.println("Lecture du leftchild : " + stagiaires.readInt());
//            System.out.println("Lecture du rightchild : " + stagiaires.readInt());
//            for (int i = 0; i < 100; i++) {
//                System.out.println("Lecture du caractère : " + stagiaires.readChar());
//            }
//
//            System.out.println("Maintenant  le pointeur se situe sur la position : " + stagiaires.getFilePointer());
//            System.out.println("Lecture du caractère : " + stagiaires.readChar());

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        // theTree.inOrderTraverseTree(theTree.root);
        // theTree.preorderTraverseTree(theTree.root);



        // place le pointeur sur le nom
        try {
            int focusTrainee = 116;
            int nbnoeud = 0;
            stagiaires.seek(focusTrainee);
            String focusTraineeName = "";

            while (nbnoeud != ((compteurStagiaire)))
            {
                //System.out.println("Avant lecture le pointeur se situe sur la position : " + stagiaires.getFilePointer());
                focusTraineeName = "";
                for (int i = 0; i < 40; i++) {
                    focusTraineeName += stagiaires.readChar();
                }
                System.out.println("Nom du noeud : " + focusTraineeName);
                // mot = completer(focusTraineeName, NOM);

                System.out.println("Node " + theTree.findNode(focusTraineeName));
                System.out.println("leftchild : " + theTree.findNode(focusTraineeName).leftChild);
                System.out.println("rightchild : " + theTree.findNode(focusTraineeName).rightChild);

                focusTrainee += 276;
                stagiaires.seek(focusTrainee);
                nbnoeud += 1;
                System.out.println("nb noeuds : " + nbnoeud );
                System.out.println("compteur stagiaires : " + compteurStagiaire);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static String completer(String mot, int taille) {

        int nbEspace = taille - mot.length();
        for (int i = 0; i < nbEspace; i++) {
            mot += " ";
        }

        return mot;
    }

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

}


