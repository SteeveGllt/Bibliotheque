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
    private List<Document> documents;
    private List<Adherent> adherents;

    public Bibliotheque(){
        this.documents = new ArrayList<>();
        this.adherents = new ArrayList<>();
    }

    public void ajouterLivre(Document document){
        if (!documents.contains(document)){
            this.documents.add(document);
        }
    }

    public void inscrireAdherent(Adherent adherent){
        if (!adherents.contains(adherent)){
            this.adherents.add(adherent);
        }
    }

    public Optional<Document> rechercherParIsbn(String isbn){
        return this.documents.stream()
                .filter(document -> document.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Livre> rechercherParAuteur(String nomAuteur) {
        // stream + filter + collect
        return this.documents.stream()
                .filter(document -> document instanceof Livre)
                .map(document -> (Livre) document)
                .filter(livre -> livre.getAuteur().getNom().equals(nomAuteur))
                .toList();
    }

    public List<Document> rechercherParCategorie(String categorie) {
        return this.documents.stream()
                .filter(document -> document.getCategorie().equals(categorie))
                .toList();
    }

    public List<Document> listerLivresDisponibles() {
        return this.documents.stream()
                .filter(Document::isDispo)
                .toList();
    }

    public List<Emprunt> listerEmpruntsEnRetard(){
        return this.adherents.stream()
                .flatMap(adherent -> adherent.getEmprunts().stream())
                .filter(emprunt -> LocalDate.now().isAfter(emprunt.getDateRetourPrevu()))
                .toList();
    }

    public Optional<Document> documentLePlusEmprunte(){
        return this.adherents.stream()
                .flatMap(adherent -> adherent.getEmprunts().stream())
                .collect(Collectors.groupingBy(Emprunt::getDocument, Collectors.counting()))
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
