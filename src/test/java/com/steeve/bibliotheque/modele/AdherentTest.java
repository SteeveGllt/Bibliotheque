package com.steeve.bibliotheque.modele;

import com.steeve.bibliotheque.exception.LivreIndisponibleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdherentTest {
    @Test
    void emprunter_devraitMarquerLivreCommeIndisponible() throws LivreIndisponibleException {
        Auteur auteur = new Auteur("Hugo", "Victor", "Francaise");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        Adherent adherent = new Adherent("Ronaldo");

        adherent.emprunter(livre);

        assertFalse(livre.isDispo());
    }

    @Test
    void emprunter_devraitLeverExceptionSiLivreDejaEmprunte() throws LivreIndisponibleException {
        // Arrange : crée un livre déjà emprunté une première fois
        Auteur auteur = new Auteur("Hugo", "Victor", "Francaise");
        Livre livre = new Livre("Les Misérables", auteur, "ISBN001", "Roman");
        Adherent adherent = new Adherent("Ronaldo");

        adherent.emprunter(livre);

        assertThrows(LivreIndisponibleException.class, () -> adherent.emprunter(livre));
        // Act + Assert : assertThrows(LivreIndisponibleException.class, () -> ...)
    }
}