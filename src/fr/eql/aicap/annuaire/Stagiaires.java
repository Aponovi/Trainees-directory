package fr.eql.aicap.annuaire;

import com.sun.org.apache.xpath.internal.objects.XString;

public class Stagiaires {
    private final String promo;
    private final int annee;
    private String nom;
    private final String prenom;
    private final int dpt;

    public Stagiaires(String promo, int annee, String nom, String prenom, int dpt ){
        this.promo = promo;
        this.annee = annee;
        this.nom = nom;
        this.prenom = prenom;
        this.dpt = dpt;
    }


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
        return promo + annee + nom + prenom + dpt;
    }
}
