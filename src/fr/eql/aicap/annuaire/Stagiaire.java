package fr.eql.aicap.annuaire;

public class Stagiaire {
    private final String promo;
    private final int annee;
    private String nom;
    private final String prenom;
    private final int dpt;

    //Constructeurs
    public Stagiaire(String promo, int annee, String nom, String prenom, int dpt ){
        this.promo = promo;
        this.annee = annee;
        this.nom = nom;
        this.prenom = prenom;
        this.dpt = dpt;

    }

    //méthode créer un stagiaire

//    public String creationStagiaire()

    //méthodes d'accès aux variables d'instance
    public String getPromo(){
        return promo;
    }

    public int getAnnee(){
        return annee;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public int getDpt(){
        return dpt;
    }


    public String toString(){
        return promo + "," + annee + "," + nom + "," + prenom +"," + dpt;
    }
}
