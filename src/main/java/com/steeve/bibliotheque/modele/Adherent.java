package com.steeve.bibliotheque.modele;

import com.steeve.bibliotheque.exception.LivreIndisponibleException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Adherent {
    private String nom;
    private ArrayList<Emprunt> emprunts;

    public Adherent(String nom){
        this.nom = nom;
        this.emprunts = new ArrayList<Emprunt>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(ArrayList<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public void emprunter(Document document) throws LivreIndisponibleException {
        if(!document.isDispo()){
            throw new LivreIndisponibleException("Le livre n'est pas disponible");
        }
        Emprunt emprunt = new Emprunt(document, this, LocalDate.now().plusDays(10));
        this.emprunts.add(emprunt);
        document.setDispo(false);
    }

    public void retourner(Document document){
        Emprunt emprunt = this.emprunts.stream()
                .filter(e -> e.getDocument().equals(document) && e.estEnCours())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Aucun emprunt en cours pour ce livre"));

        emprunt.setDateRetourReelle(LocalDate.now());
        document.setDispo(true);
    }
}
