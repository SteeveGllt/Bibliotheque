package com.steeve.bibliotheque.modele;

import com.steeve.bibliotheque.exception.LivreIndisponibleException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BibliothequeTest {
    @Test
    void rechercherParIsbn_devraitRetournerLeLivre() {
        Bibliotheque bibliotheque = new Bibliotheque();
        Auteur auteur = new Auteur("Hugo", "Victor", "Francaise");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        bibliotheque.ajouterLivre(livre);

        Optional<Livre> resultat = bibliotheque.rechercherParIsbn("ISBN001");

        assertTrue(resultat.isPresent());
        assertEquals(livre, resultat.get());
    }

    @Test
    void rechercherParAuteur_devraitRetournerLeLivre() {
        Bibliotheque bibliotheque = new Bibliotheque();
        Auteur auteur = new Auteur("Hugo", "Victor", "Francaise");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        bibliotheque.ajouterLivre(livre);

        List<Livre> resultat = bibliotheque.rechercherParAuteur("Hugo");

        assertFalse(resultat.isEmpty());
        assertEquals(livre, resultat.getFirst());
    }

    @Test
    void listerLivresDispo_devraitRetournerLeLivre() throws LivreIndisponibleException {
        Bibliotheque bibliotheque = new Bibliotheque();
        Auteur auteur = new Auteur("Hugo", "Victor", "Francaise");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        Livre livre2 = new Livre("test", auteur, "ISBN002", "Roman");
        Adherent adherent = new Adherent("Ronaldo");
        bibliotheque.ajouterLivre(livre);
        bibliotheque.ajouterLivre(livre2);

        adherent.emprunter(livre);
        List<Livre> resultat = bibliotheque.listerLivresDisponibles();

        assertFalse(resultat.isEmpty());
    }

    @Test
    void listerEmpruntsEnRetard() throws LivreIndisponibleException {
        Bibliotheque bibliotheque = new Bibliotheque();
        Adherent adherent = new Adherent("Jean");
        Auteur auteur = new Auteur("Hugo", "Victor", "Française");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");

        adherent.emprunter(livre);
        bibliotheque.inscrireAdherent(adherent);

        Emprunt emprunt = adherent.getEmprunts().get(0);
        emprunt.setDateRetourPrevu(LocalDate.now().minusDays(5));

        List<Emprunt> emprunts = bibliotheque.listerEmpruntsEnRetard();

        assertEquals(1, emprunts.size());
        assertEquals(emprunt, emprunts.get(0));
    }

    @Test
    void livreLePlusEmprunte_devraitRetournerLeLivreLePlusEmprunte() throws LivreIndisponibleException {
        Bibliotheque bibliotheque = new Bibliotheque();

        Adherent adherent1 = new Adherent("Jean");
        Adherent adherent2 = new Adherent("Paul");

        Auteur auteur = new Auteur("Hugo", "Victor", "Française");

        Livre livre1 = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        Livre livre2 = new Livre("Notre-Dame de Paris", auteur, "ISBN002", "Roman");

        bibliotheque.inscrireAdherent(adherent1);
        bibliotheque.inscrireAdherent(adherent2);

        adherent1.emprunter(livre1);
        livre1.setDispo(true);

        adherent2.emprunter(livre1);

        adherent1.emprunter(livre2);

        Optional<Livre> resultat = bibliotheque.livreLePlusEmprunte();

        assertTrue(resultat.isPresent());
        assertEquals(livre1, resultat.get());
    }

    @Test
    void topAdherentsActifs_devraitRetournerLesAdherentsLesPlusActifs() throws LivreIndisponibleException {
        Bibliotheque bibliotheque = new Bibliotheque();

        Adherent adherent1 = new Adherent("Jean");
        Adherent adherent2 = new Adherent("Paul");
        Adherent adherent3 = new Adherent("Pierre");

        Auteur auteur = new Auteur("Hugo", "Victor", "Française");

        Livre livre1 = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        Livre livre2 = new Livre("Notre-Dame de Paris", auteur, "ISBN002", "Roman");
        Livre livre3 = new Livre("Le Dernier Jour", auteur, "ISBN003", "Roman");

        bibliotheque.inscrireAdherent(adherent1);
        bibliotheque.inscrireAdherent(adherent2);
        bibliotheque.inscrireAdherent(adherent3);

        // Jean : 3 emprunts
        adherent1.emprunter(livre1);
        livre1.setDispo(true);

        adherent1.emprunter(livre1);
        livre1.setDispo(true);

        adherent1.emprunter(livre1);

        // Paul : 2 emprunts
        adherent2.emprunter(livre2);
        livre2.setDispo(true);

        adherent2.emprunter(livre2);

        // Pierre : 1 emprunt
        adherent3.emprunter(livre3);

        List<Adherent> resultat = bibliotheque.topAdherentsActifs(2);

        assertEquals(2, resultat.size());
        assertEquals(adherent1, resultat.get(0));
        assertEquals(adherent2, resultat.get(1));
    }
}