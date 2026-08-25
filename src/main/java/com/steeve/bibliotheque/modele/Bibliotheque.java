package com.steeve.bibliotheque.modele;

import com.steeve.bibliotheque.exception.LivreNonTrouveException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bibliotheque {
    private List<Livre> livres;
    private List<Adherent> adherents;

    public Bibliotheque(){
        this.livres = new ArrayList<>();
        this.adherents = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre){
        if (!livres.contains(livre)){
            this.livres.add(livre);
        }
    }

    public void inscrireAdherent(Adherent adherent){
        if (!adherents.contains(adherent)){
            this.adherents.add(adherent);
        }
    }

    public Optional<Livre> rechercherParIsbn(String isbn){
        return this.livres.stream()
                .filter(livre -> livre.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Livre> rechercherParAuteur(String nomAuteur) {
        // stream + filter + collect
        List<Livre> auteurLivres = this.livres.stream()
                .filter(livre -> livre.getAuteur().getNom().equals(nomAuteur))
                .toList();

        return auteurLivres;
    }

    public List<Livre> rechercherParCategorie(String categorie) {
        List<Livre> categories = this.livres.stream()
                .filter(livre -> livre.getCategorie().equals(categorie))
                .toList();

        return categories;
    }

    public List<Livre> listerLivresDisponibles() {
        List<Livre> livresDispo = this.livres.stream()
                .filter(Livre::isDispo)
                .toList();

        return livresDispo;
    }

    public List<Emprunt> listerEmpruntsEnRetard(){
        return this.adherents.stream()
                .flatMap(adherent -> adherent.getEmprunts().stream())
                .filter(emprunt -> LocalDate.now().isAfter(emprunt.getDateRetourPrevu()))
                .toList();
    }

    public Optional<Livre> livreLePlusEmprunte(){
        return this.adherents.stream()
                .flatMap(adherent -> adherent.getEmprunts().stream())
                .collect(Collectors.groupingBy(Emprunt::getLivre, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public List<Adherent> topAdherentsActifs(int nombre) {
        return this.adherents.stream()
                .flatMap(adherent -> adherent.getEmprunts().stream())
                .collect(Collectors.groupingBy(Emprunt::getAdherent, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Adherent, Long>comparingByValue().reversed())
                .limit(nombre)
                .map(Map.Entry::getKey)
                .toList();
    }
}
