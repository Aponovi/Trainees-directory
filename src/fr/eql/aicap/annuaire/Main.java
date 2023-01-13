package fr.eql.aicap.annuaire;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;

public class Main {

    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";

    //allocation taille des données pour l'arbre

    public static final int PROMO = 12;
    public static final int ANNEE = 4;
    public static final int NOM = 40;
    public static final int PRENOM = 40;
    public static final int DEPARTEMENT = 4;
    public static final int LEFTCHILD = 20;
    public static final int RIGHTCHILD = 20;


    public static final int LONGUEURSTAGIAIRE = ((PROMO + ANNEE + NOM + PRENOM + DEPARTEMENT + LEFTCHILD + RIGHTCHILD ) * 2);


    public static void main(String[] args) throws FileNotFoundException {

        String ligne = "";
        String mot = "";
        int compteurLigne;
        int compteurStagiaire = 0;
        int leftChild = 1;
        int rightChild = 1;

        // int adresseStagiaire = 0;

        RandomAccessFile stagiaires;

        File folder = new File(FOLDER);
        folder.mkdir();

        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            FileReader fichierOriginal = new FileReader("C:\\Users\\Formation\\Documents\\Projects\\Trainees_directory\\stagiaires.txt");
            BufferedReader bf = new BufferedReader(fichierOriginal);
            compteurLigne = 0;
            // ajout du leftchild dans le fichier bin
            String convert = String.format("%0" + Integer.numberOfLeadingZeros(leftChild) + "d", leftChild);
            System.out.println(convert);
            stagiaires.writeInt(Integer.parseInt(convert));
            // ajout du rightchild dans le fichier bin
            String conver = String.format("%0" + Integer.numberOfLeadingZeros(rightChild) + "d", rightChild);
            System.out.println(conver);
            stagiaires.writeInt(Integer.parseInt(conver));

                while ((ligne = bf.readLine()) != null) {
                    compteurLigne = compteurLigne % 6;
                    // System.out.println(ligne);
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
                            break;

                        case 3:
                            mot = completer(mot, PRENOM);
                            stagiaires.writeChars(mot);
                            break;

                        case 4:
                            mot = completer(mot, DEPARTEMENT);
                            stagiaires.writeChars(mot);
                            break;

                    }
                    compteurLigne += 1;
                    compteurStagiaire += 1;

//                    String convert = String.format("%0" + Integer.numberOfLeadingZeros(leftChild) + "d", leftChild);
//
//                    System.out.println(convert);
//
//                    stagiaires.writeInt(Integer.parseInt(convert));

//                System.out.println(leftChild);
//                System.out.println(Integer.numberOfLeadingZeros(leftChild));
//                System.out.println(Integer.numberOfLeadingZeros(leftChild) * 0);
                    // leftChild = (Integer.numberOfLeadingZeros(leftChild) * 0) + leftChild;


//                def digitFormatter(long: Long, numDigits: Int): String = {
//                        val padlength = if (long >= 0) numDigits else numDigits + 1
//                String.format(s"%0${padlength}d", long)
//                  }
                //}
//            for (int i = 0; i < compteurStagiaire; i++) {

                // System.out.println(compteurStagiaire);
            }

            // ajout du leftchild qui est un integer
            // stagiaires.writeInt(leftChild);
            // System.out.println(leftChild);
            // cherche à compléter le leftchild avec des espaces vides pour "garder sa place"


            // stagiaires.writeInt(leftChild,LEFTCHILD);
            // stagiaires.writeInt(rightChild,LEFTCHILD);
//            System.out.println("Taille du RAF : " + stagiaires.length());
//            System.out.println("nb stagiaires : " + compteurStagiaire);
//            System.out.println("lenght raf par stagiaires : " + (stagiaires.length() / compteurStagiaire));

            stagiaires.close();

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            /* le raf possède une méthode get File Pointer() qui permet de savoir où est placé le pointeur
             * cad là où la lecture ou l'écriture s'effectuera.
             */
            System.out.println("Avant lecture le pointeur se situe sur la position : " + stagiaires.getFilePointer());
            System.out.println("Lecture du leftchild : " + stagiaires.readInt());
            System.out.println("Lecture du rightchild : " + stagiaires.readInt());
            for (int i = 0; i < 100; i++) {
                System.out.println("Lecture du caractère : " + stagiaires.readChar());
            }

            System.out.println("Maintenant  le pointure se situe sur la position : " + stagiaires.getFilePointer());
            System.out.println("Lecture du caractère : " + stagiaires.readChar());

        } catch (
                IOException e) {
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


    //TODO
    /* créer une fonction "compléter" pour les children
    qui ajoute des 0 devant l'adresse des children
    */


}


