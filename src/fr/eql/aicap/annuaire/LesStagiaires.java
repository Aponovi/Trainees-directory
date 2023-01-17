//package fr.eql.aicap.annuaire;
//
//import java.io.*;
//import java.util.List;
//import java.util.StringTokenizer;
//import java.util.Vector;
//
////la classe récupère un fichier txt dans un vecteur
//public class LesStagiaires {
//    private BufferedReader bfr;
//
//    //Constructeurs
//    public LesStagiaires(){
//        bfr=null;
//    }
//
//
//    // Transforme une chaine en un objet de type Stagiaire
//    private Stagiaire fabriqueStagiaire(String chaine){
//        Stagiaire stg=null;
//        StringTokenizer st = new StringTokenizer(chaine, "*");
//        if(st.countTokens()==5){
//            String promo = st.nextToken();
//            String annee = st.nextToken();
//            String nom = st.nextToken();
//            String prenom= st.nextToken();
//            String dpt = st.nextToken();
//            stg = new Stagiaire(promo, annee, nom, prenom, dpt);
//        }
//        return stg;
//    }
//    public LesStagiaires(String nomFichier){
//        try{
//            //Création d'un flux pour le fichier texte
//            //le nom du fichier est passé en argument
//            ObjectOutputStream outPut = new ObjectOutputStream(new FileOutputStream("stagiaires.bin"));
//            outPut.writeObject(fabriqueStagiaire);
//        }catch (IOException e){
//            System.out.println("Pb entrée sortie :" + e.getMessage());
//        }
//    }
//
//    //Transformer le fichier en une collection de stagiaires
//    public List<Stagiaire> fabriqueVecteur(){
//        String chaine;
//        Stagiaire stg;
//        List<Stagiaire> stagiaires = new Vector<Stagiaire>();
//        try{
//            do{
//                chaine = bfr.readLine();
//                if(chaine!=null){
//                    stg=fabriqueStagiaire(chaine);
//                    stagiaires.add(stg);
//                }
//            }while(chaine!=null);
//        }catch(IOException e){
//            System.out.println("Problème de lecture : " +e.getMessage());
//        }
//        return stagiaires;
//    }
//    //Transforme le fichier en une chaine de caractères formée de plusieurs lignes
//    public String fabriqueChaine(){
//        StringBuffer chainebf=new StringBuffer();
//        String chaine;
//        try{
//            do{
//                chaine= bfr.readLine() ;
//                if(chaine!=null){
//                    chainebf.append(chaine +"\n");
//                }
//            }while(chaine!=null);
//        }catch(IOException e){
//            System.out.println("Problème de lecture : " +e.getMessage());
//
//        }
//        return chainebf.toString();
//    }
//
//}
