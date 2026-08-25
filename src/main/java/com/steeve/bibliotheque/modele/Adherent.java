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

    public void emprunter(Livre livre) throws LivreIndisponibleException {
        if(!livre.isDispo()){
            throw new LivreIndisponibleException("Le livre n'est pas disponible");
        }
        Emprunt emprunt = new Emprunt(livre, this, LocalDate.now().plusDays(10));
        this.emprunts.add(emprunt);
        livre.setDispo(false);
    }
}
