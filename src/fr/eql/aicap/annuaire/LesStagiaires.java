package fr.eql.aicap.annuaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

//la classe récupère un fichier txt dans un vecteur
public class LesStagiaires {
    private BufferedReader bfr;

    //Constructeurs
    public LesStagiaires(){
        bfr=null;
    }
    public LesStagiaires(String nomFichier){
        try{
            //Création d'un flux pour le fichier texte
            //le nom du fichier est passé en argument
            FileReader in = new FileReader(nomFichier);
            bfr= new BufferedReader(in);
        }catch (IOException e){
            System.out.println("Pb entrée sortie :" + e.getMessage());
        }
    }

    // Transforme une chaine en un objet de type Employe
    //format de la chaine : 1*BARBE*Rue des Vignes Paris*0123546789*10000
    private Stagiaires fabriqueStagiaire(String chaine){
        Stagiaires stg=null;
        StringTokenizer st = new StringTokenizer(chaine, "*");
        if(st.countTokens()==5){
            String promo = st.nextToken();
            Integer annee = Integer.parseInt(st.nextToken());
            String nom = st.nextToken();
            String prenom= st.nextToken();
            Integer dpt = Integer.parseInt(st.nextToken());
            stg = new Stagiaires(promo, annee, nom, prenom, dpt);
        }
        return stg;
    }
    //Transformer le fichier en une collection d'employés
    public List<Stagiaires> fabriqueVecteur(){
        String chaine;
        Stagiaires stg;
        List<Stagiaires> stagiaires = new Vector<Stagiaires>();
        try{
            do{
                chaine = bfr.readLine();
                if(chaine!=null){
                    stg=fabriqueStagiaire(chaine);
                    stagiaires.add(stg);
                }
            }while(chaine!=null);
        }catch(IOException e){
            System.out.println("Problème de lecture : " +e.getMessage());
        }
        return stagiaires;
    }
    //Transforme le fichier en une chaine de caractères formée de plusieurs lignes
    public String fabriqueChaine(){
        StringBuffer chainebf=new StringBuffer();
        String chaine;
        try{
            do{
                chaine= bfr.readLine() ;
                if(chaine!=null){
                    chainebf.append(chaine +"\n");
                }
            }while(chaine!=null);
        }catch(IOException e){
            System.out.println("Problème de lecture : " +e.getMessage());

        }
        return chainebf.toString();
    }
}
