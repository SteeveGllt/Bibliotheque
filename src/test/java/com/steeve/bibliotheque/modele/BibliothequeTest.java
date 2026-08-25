package com.steeve.bibliotheque.modele;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BibliothequeTest {
    @Test
    void rechercherParIsbn_devraitRetournerLeLivre(){
        Bibliotheque bibliotheque = new Bibliotheque();
        Auteur auteur = new Auteur("Hugo", "Victor", "Francaise");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        bibliotheque.ajouterLivre(livre);

        Optional<Livre> resultat = bibliotheque.rechercherParIsbn("ISBN001");

        assertTrue(resultat.isPresent());
        assertEquals(livre, resultat.get());
    }
}