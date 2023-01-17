package fr.eql.aicap.annuaire;

public class Node {

    String key;
    int address;
    Node leftChild;
    Node rightChild;

    Node(String key, int position) {
        this.key = key;
        this.address = position;
    }

    public String toString() {
        return  key + " has the adresse " + address;
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



}