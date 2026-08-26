package com.steeve.bibliotheque.modele;

public class Livre extends Document {
    private Auteur auteur;

    public Livre(String titre, Auteur auteur, String isbn, String categorie){
        super(titre, isbn, categorie);
        this.auteur = auteur;
        auteur.ajouterLivre(this);
    }
    public Auteur getAuteur(){
        return this.auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }


}
