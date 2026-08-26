package com.steeve.bibliotheque.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Auteur {
    private int id;
    private String nom, prenom, nationalite;
    private ArrayList<Livre> livres;

    public Auteur(String nom, String prenom, String nationalite){
        this(0, nom, prenom, nationalite);
    }
    public Auteur(int id, String nom, String prenom, String nationalite){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.livres = new ArrayList<Livre>();
    }

    public int getId() { return id; }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public ArrayList<Livre> getLivres(){
        return this.livres;
    }

    public void ajouterLivre(Livre livre){
        if (!this.livres.contains(livre)) {
            this.livres.add(livre);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return Objects.equals(nom, auteur.nom) && Objects.equals(prenom, auteur.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nationalite='" + nationalite + '\'' +
                '}';
    }
}
