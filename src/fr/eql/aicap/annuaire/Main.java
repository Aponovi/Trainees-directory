package src.fr.eql.aicap.annuaire;

import java.io.*;

public class Main {

    private static final String FOLDER = "files/";
    private static final String RAF = "stagiaires.bin";

    //allocation taille des données pour l'arbre

    public static final int PROMODIGIT = 15;
    public static final int PROMO = 15;
    public static final int ANNEE = 4;
    public static final int NOM = 50;
    public static final int PRENOM = 50;
    public static final int DEPARTEMENT = 3;

    public static final int LONGUEURSTAGIAIRE = ((PROMO + PROMODIGIT + ANNEE + NOM + PRENOM + DEPARTEMENT) *2 );


    public static void main(String[] args) throws FileNotFoundException {

        String ligne ="";
        String mot = "";
        int compteurTab= 0;

        RandomAccessFile stagiaires;

        File folder = new File(FOLDER);
        folder.mkdir();

        try {
            stagiaires = new RandomAccessFile(FOLDER + RAF, "rw");
            FileReader fichierOriginal = new FileReader("C:\\Users\\Formation\\Documents\\Projects\\Trainees-directory\\stagiaires.txt");
            BufferedReader bf = new BufferedReader(fichierOriginal);

            while ((ligne = bf.readLine()) != null) {
                compteurTab = 0;
                for (int i = 0; i < ligne.length(); i++){
                    if (ligne.charAt(i) != '    '){
                        mot += ligne.charAt(i);
                    } else {
                        switch (compteurTab){
                            case 0:
                                int nb = Integer.parseInt(mot);
                                stagiaires.writeInt(nb);
                                break;

                            case 1:
                                mot = completer(mot, PROMO);
                                stagiaires.writeChars(mot);
                                break;

                            case 2:
                                int nb2 = Integer.parseInt(mot);
                                stagiaires.writeInt(nb2);
                                break;

                            case 3:
                                mot = completer(mot, NOM);
                                stagiaires.writeChars(mot);
                                break;

                            case 4:
                                mot = completer(mot, PRENOM);
                                stagiaires.writeChars(mot);
                                break;

                            case 5:
                                int nb3 = Integer.parseInt(mot);
                                stagiaires.writeInt(nb3);
                                break;
                        }
                    }
                }


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String completer(String mot, int promo) {
    }
}
