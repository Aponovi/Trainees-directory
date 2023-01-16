package fr.eql.aicap.annuaire;


public class Stagiaire {
    private final String promo;
    private final String annee;
    private final String nom;
    private final String prenom;
    private final String dpt;

    //Constructeurs
    public Stagiaire(String promo, String annee, String nom, String prenom, String dpt ){
        this.promo = promo;
        this.annee = annee;
        this.nom = nom;
        this.prenom = prenom;
        this.dpt = dpt;

    }

    //méthodes d'accès aux variables d'instance
    public String getPromo(){
        return promo;
    }

    public String getAnnee(){
        return annee;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public String getDpt(){
        return dpt;
    }


//    public String setPromo(){}

    public String toString(){
        return promo + " " + annee + "" + nom + "" + prenom +"" + dpt;
    }
}
