package fr.eql.aicap.annuaire;

import java.io.*;
import java.text.Normalizer;

public class Main {

    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";

    //tree data size allocation

    public static final int PROMO = 50;
    public static final int ANNEE = 4;
    public static final int NOM = 40;
    public static final int PRENOM = 40;
    public static final int DEPARTEMENT = 4;
    public static final int LEFTCHILD = 1;
    public static final int RIGHTCHILD = 1;


    public static final int LONGUEURSTAGIAIRE = (PROMO * 2 + ANNEE * 2 + NOM * 2 + PRENOM * 2 + DEPARTEMENT * 2 + LEFTCHILD * 4 + RIGHTCHILD * 4);
    public static final int ADRESSBIGINNINGNAME = (PROMO * 2 + ANNEE * 2 + LEFTCHILD * 4 + RIGHTCHILD * 4);


    public static void main(String[] args) throws FileNotFoundException {



        //Import_From_Txt(Chemin,Nom_Binaire)

        //Creer_Modifier_Noeud(Nom_Binaire)

        //Ajouter_Contact(Nom_Binaire,Contact)

        //Modifier_Contact(Nom_Binaire,Contact)

        //Sup_Contact(Nom_Binaire,Contact)

        //Rechercher_Contact(Nom_Binaire,Contact_A_Rechercher)

        BinaryTree theTree = new BinaryTree();

        String ligne = "";
        String mot = "";
        int compteurLigne;
        int compteurStagiaire = 0;
        int traineePositionInBinFile = 0;
        int leftChild = 1; // 1 ==> pas de child
        int rightChild = 1; // 1 ==> pas de child

        RandomAccessFile stagiaires;

        File folder = new File(FOLDER);
        folder.mkdir();

        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            File fichierOriginal = new File("C:\\Users\\Formation\\Documents\\Projects\\Trainees_directory\\stagiaires.txt");
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fichierOriginal), "ISO-8859-1"));
            compteurLigne = 0;

            // complete du leftchild
            String convert = String.format("%0" + Integer.numberOfLeadingZeros(leftChild) + "d", leftChild);


            // complete du rightchild
            String conver = String.format("%0" + Integer.numberOfLeadingZeros(rightChild) + "d", rightChild);


            // lecture du fichier txt et recopie dans le fichier bin
            while ((ligne = bf.readLine()) != null) {
                compteurLigne = compteurLigne % 6;
                ligne = stripAccents(ligne);
                mot = ligne;
                switch (compteurLigne) {
                    case 0:
                        stagiaires.writeInt(Integer.parseInt(convert));
                        stagiaires.writeInt(Integer.parseInt(conver));
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
            }


            stagiaires.close();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }


//        /*********************
//         * find nodes children *
//         *********************/
//
//        try {
//            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
//            int focusTrainee = 116;
//            int nbnoeud = 0;
//            stagiaires.seek(focusTrainee);
//            String focusTraineeName;
//
//            while (nbnoeud != ((compteurStagiaire))) {
//                focusTraineeName = "";
//                for (int i = 0; i < 40; i++) {
//                    focusTraineeName += stagiaires.readChar();
//                }
//
//                focusTrainee += 284;
//                stagiaires.seek(focusTrainee);
//                nbnoeud += 1;
//            }
//            stagiaires.close();
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        /************************************
         * write nodes children in binary file *
         *************************************/
        stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
        try {
            int focusTrainee = ADRESSBIGINNINGNAME;
            int nbnoeud = 0;
            stagiaires.seek(focusTrainee);
            String focusTraineeName = "";
            int leftChildAddress = 1;
            int rightChildAddress = 1;

            while (nbnoeud != ((compteurStagiaire))) {
                focusTraineeName = "";


                for (int i = 0; i < NOM; i++) {
                    focusTraineeName += stagiaires.readChar();
                }
                if (theTree.findNode(focusTraineeName).leftChild != null) {
                    leftChildAddress = theTree.findNode(focusTraineeName).leftChild.address;
                }
                if (theTree.findNode(focusTraineeName).rightChild != null) {
                    rightChildAddress = theTree.findNode(focusTraineeName).rightChild.address;
                }


                stagiaires.seek(focusTrainee - ADRESSBIGINNINGNAME);
                int lefttchildCompleted = Integer.parseInt(String.format("%0" + Integer.numberOfLeadingZeros(leftChildAddress) + "d", leftChildAddress));
                stagiaires.writeInt(lefttchildCompleted);
                int rightchildCompleted = Integer.parseInt(String.format("%0" + Integer.numberOfLeadingZeros(rightChildAddress) + "d", rightChildAddress));
                stagiaires.writeInt(rightchildCompleted);

                focusTrainee += LONGUEURSTAGIAIRE;
                stagiaires.seek(focusTrainee);
                nbnoeud += 1;
            }

            stagiaires.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**********************************
         * check if everything went right *
         **********************************/

        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            for (int j = 0; j < 15; j++) {
                System.out.println("Avant lecture le pointeur se situe sur la position : " + stagiaires.getFilePointer());
                System.out.println("Lecture du leftchild : " + stagiaires.readInt());
                System.out.println("Lecture du rightchild : " + stagiaires.readInt());
                for (int i = 0; i < (PROMO + ANNEE + NOM + PRENOM + DEPARTEMENT)
                        ; i++) {
                    System.out.println("Lecture du caractère : " + stagiaires.readChar());
                }

                System.out.println("Maintenant  le pointeur se situe sur la position : " + stagiaires.getFilePointer());

            }
            System.out.println("Lecture du leftchild : " + stagiaires.readInt());
            System.out.println("Lecture du rightchild : " + stagiaires.readInt());
            stagiaires.close();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }


    }

    // Methods
    private static String completer(String mot, int taille) {

        int nbEspace = taille - mot.length();
        for (int i = 0; i < nbEspace; i++) {
            mot += " ";
        }

        return mot;
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }


}


