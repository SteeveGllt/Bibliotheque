package com.steeve.bibliotheque.modele;

import java.time.LocalDate;

public class Emprunt {
    private Document document;
    private Adherent adherent;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private LocalDate dateRetourReelle;

    public Emprunt(Document document, Adherent adherent, LocalDate dateRetourPrevu){
        this.document = document;
        this.adherent = adherent;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public void setDateRetourPrevu(LocalDate dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public LocalDate getDateRetourReelle(){
        return this.dateRetourReelle;
    }

    public void setDateRetourReelle(LocalDate dateRetourReelle) {
        this.dateRetourReelle = dateRetourReelle;
    }

    public boolean estEnCours(){
        return dateRetourReelle == null;
    }
}
