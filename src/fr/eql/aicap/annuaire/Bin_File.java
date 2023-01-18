package fr.eql.aicap.annuaire;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Bin_File {
    /******************************
     * tree data size allocation
     *******************************/
    public static final int PROMO = 50;
    public static final int ANNEE = 4;
    public static final int NOM = 40;
    public static final int PRENOM = 40;
    public static final int DEPARTEMENT = 4;
    public static final int LEFTCHILD = 1;
    public static final int RIGHTCHILD = 1;
    public static final int LONGUEURSTAGIAIRE = (PROMO * 2 + ANNEE * 2 + NOM * 2 + PRENOM * 2 + DEPARTEMENT * 2 + LEFTCHILD * 4 + RIGHTCHILD * 4);
    public static final int ADRESSBIGINNINGNAME = (PROMO * 2 + ANNEE * 2 + LEFTCHILD * 4 + RIGHTCHILD * 4);
    private static String ligne = "";
    private static String mot = "";
    private static int compteurLigne;
    public static int compteurStagiaire = 0;
    private static int traineePositionInBinFile = 0;
    private static int leftChild = 1; // 1 ==> pas de child
    private static int rightChild = 1; // 1 ==> pas de child
    RandomAccessFile RandomAccessFile;

    public BinaryTree fromTxtToBin(String fichierTxt, String fichierBinaire) {
        try {
            BinaryTree binaryTree = new BinaryTree();
            RandomAccessFile = new RandomAccessFile(fichierBinaire, "rw");
            //BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fichier_txt), "ISO-8859-1"));
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fichierTxt), StandardCharsets.ISO_8859_1));
            compteurLigne = 0;
            // complete du leftchild
            String _leftChild = String.format("%0" + Integer.numberOfLeadingZeros(leftChild) + "d", leftChild);
            // complete du rightchild
            String _rightChild = String.format("%0" + Integer.numberOfLeadingZeros(rightChild) + "d", rightChild);
            // lecture du fichier txt et recopie dans le fichier bin
            while ((ligne = bf.readLine()) != null) {
                compteurLigne = compteurLigne % 6;
                //ligne = stripAccents(ligne);
                mot = ligne;
                switch (compteurLigne) {
                    case 0:
                        RandomAccessFile.writeInt(Integer.parseInt(_leftChild));
                        RandomAccessFile.writeInt(Integer.parseInt(_rightChild));
                        mot = completer(mot, PROMO);
                        RandomAccessFile.writeChars(mot);
                        break;
                    case 1:
                        mot = completer(mot, ANNEE);
                        RandomAccessFile.writeChars(mot);
                        break;
                    case 2:
                        mot = completer(mot, NOM);
                        RandomAccessFile.writeChars(mot);
                        binaryTree.addNode(mot, traineePositionInBinFile);
                        traineePositionInBinFile += LONGUEURSTAGIAIRE;
                        break;
                    case 3:
                        mot = completer(mot, PRENOM);
                        RandomAccessFile.writeChars(mot);
                        break;
                    case 4:
                        mot = completer(mot, DEPARTEMENT);
                        RandomAccessFile.writeChars(mot);
                        compteurStagiaire += 1;
                        break;
                }
                compteurLigne += 1;
            }
            RandomAccessFile.close();
            return binaryTree;
            //System.out.println("nb stagiaires : " + compteurStagiaire);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    /************************************
     * write nodes children in binary file *
     *************************************/
    public void addChildrenAddressesIntoParentData(String fichierBinaire, BinaryTree binaryTree) {
        try {
            RandomAccessFile = new RandomAccessFile(fichierBinaire, "rw");
            int focusTrainee = ADRESSBIGINNINGNAME;
            int nbnoeud = 0;
            String focusTraineeName = "";
            int leftChildAddress = 1;
            int rightChildAddress = 1;
            RandomAccessFile.seek(focusTrainee);
            while (nbnoeud != ((compteurStagiaire))) {
                focusTraineeName = "";
                for (int i = 0; i < NOM; i++) {
                    focusTraineeName += RandomAccessFile.readChar();
                }
                if (binaryTree.findNode(focusTraineeName).leftChild != null) {
                    leftChildAddress = binaryTree.findNode(focusTraineeName).leftChild.address;
                }
                if (binaryTree.findNode(focusTraineeName).rightChild != null) {
                    rightChildAddress = binaryTree.findNode(focusTraineeName).rightChild.address;
                }
                RandomAccessFile.seek(focusTrainee - ADRESSBIGINNINGNAME);
                int lefttchildCompleted = Integer.parseInt(String.format("%0" + Integer.numberOfLeadingZeros(leftChildAddress) + "d", leftChildAddress));
                RandomAccessFile.writeInt(lefttchildCompleted);
                int rightchildCompleted = Integer.parseInt(String.format("%0" + Integer.numberOfLeadingZeros(rightChildAddress) + "d", rightChildAddress));
                RandomAccessFile.writeInt(rightchildCompleted);
                focusTrainee += LONGUEURSTAGIAIRE;
                RandomAccessFile.seek(focusTrainee);
                nbnoeud += 1;
            }
            RandomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void visualCheckOfBinaryTree(String fichierBinaire) {
        try {
            RandomAccessFile = new RandomAccessFile(fichierBinaire, "rw");
            for (int j = 0; j < 30; j++) {
                System.out.println("Avant lecture le pointeur se situe sur la position : " + RandomAccessFile.getFilePointer());
                RandomAccessFile.seek(395044);
                System.out.println("Avant lecture le pointeur se situe sur la position : " + RandomAccessFile.getFilePointer());
                System.out.println("Lecture du leftchild : " + RandomAccessFile.readInt());
                System.out.println("Lecture du rightchild : " + RandomAccessFile.readInt());
                for (int i = 0; i < (PROMO + ANNEE + NOM + PRENOM + DEPARTEMENT)
                        ; i++) {
                    System.out.println("Lecture du caractère : " + RandomAccessFile.readChar());
                }
                System.out.println("Maintenant  le pointeur se situe sur la position : " + RandomAccessFile.getFilePointer());
            }
            // System.out.println("Lecture du leftchild : " + RandomAccessFile.readInt());
            // System.out.println("Lecture du rightchild : " + RandomAccessFile.readInt());
            RandomAccessFile.close();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String completer(String mot, int taille) {
        int nbEspace = taille - mot.length();
        for (int i = 0; i < nbEspace; i++) {
            mot += " ";
        }
        return mot;
    }
}
