package com.steeve.bibliotheque.modele;

public abstract class Document {
    private String titre;
    private String isbn;
    private boolean dispo;
    private String categorie;

    public Document(String titre, String isbn, String categorie) {
        this.titre = titre;
        this.isbn = isbn;
        this.dispo = true;
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
