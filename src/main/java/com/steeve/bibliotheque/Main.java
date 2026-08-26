package com.steeve.bibliotheque;

import com.steeve.bibliotheque.modele.*;

import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Bibliotheque bibliotheque = new Bibliotheque();

        Auteur auteur = new Auteur("Test", "test", "france");
        Auteur auteur2 = new Auteur("Test", "test", "Allemand");

        Document document = new Livre("Oui", auteur, "ISB001", "Roman");

        bibliotheque.ajouterLivre(document);

        System.out.println("\n--- Test listerLivresDisponibles (avant emprunt) ---");
        System.out.println("Disponibles : " + bibliotheque.listerLivresDisponibles().size() + " (attendu: 4)");
        // Test livres disponibles après emprunt
        Adherent alice = new Adherent("Alice");
        try {
            alice.emprunter(document);
        } catch (Exception e) {
            System.out.println("Erreur emprunt : " + e.getMessage());
        }

        System.out.println("\n--- Test listerLivresDisponibles (après emprunt) ---");
        System.out.println("Disponibles : " + bibliotheque.listerLivresDisponibles().size() + " (attendu: 3)");
    }
}
