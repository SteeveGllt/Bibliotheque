package com.steeve.bibliotheque.modele;

public class Livre {
    private String titre;
    private Auteur auteur;
    private String isbn;
    private boolean dispo;
    private String categorie;

    public Livre(String titre, Auteur auteur, String isbn, String categorie){
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.dispo = true;
        this.categorie = categorie;
        auteur.ajouterLivre(this);
    }

    public String getTitre(){
        return this.titre;
    }
    public Auteur getAuteur(){
        return this.auteur;
    }
    public String getIsbn(){
        return this.isbn;
    }
    public boolean isDispo(){
        return this.dispo;
    }
    public String getCategorie() {
        return categorie;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur=" + auteur +
                ", isbn='" + isbn + '\'' +
                ", dispo=" + dispo +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
