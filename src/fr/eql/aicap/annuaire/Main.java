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


    public static final int LONGUEURSTAGIAIRE = ((PROMO + ANNEE + NOM + PRENOM + DEPARTEMENT + LEFTCHILD + RIGHTCHILD) * 2);


    public static void main(String[] args) throws FileNotFoundException {

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
            FileReader fichierOriginal = new FileReader("C:\\Users\\Formation\\Documents\\Projects\\Trainees_directory\\stagiaires.txt");
            BufferedReader bf = new BufferedReader(fichierOriginal);
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
                        // stagiaires.seek(4);
                        System.out.println("dans le tree il y aurait : " + mot );

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
                        break;

                }
                compteurLigne += 1;
                compteurStagiaire += 1;
            }


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
        theTree.inOrderTraverseTree(theTree.root);

    }

    private static String completer(String mot, int taille) {

        int nbEspace = taille - mot.length();
        for (int i = 0; i < nbEspace; i++) {
            mot += " ";
        }

        return mot;
    }


}


