package com.steeve.bibliotheque.modele;

import java.time.LocalDate;

public class Emprunt {
    private Livre livre;
    private Adherent adherent;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;

    public Emprunt(Livre livre, Adherent adherent, LocalDate dateRetourPrevu){
        this.livre = livre;
        this.adherent = adherent;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
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
}
