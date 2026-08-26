package com.steeve.bibliotheque;

import com.steeve.bibliotheque.dao.AuteurDAO;
import com.steeve.bibliotheque.dao.AuteurDAOImpl;
import com.steeve.bibliotheque.modele.*;
import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws SQLException {
        /*Auteur nouvelAuteur = new Auteur("Hugo", "Victor", "Française"); // id = 0
        AuteurDAO auteurDAO = new AuteurDAOImpl();
        Auteur auteurEnBase = auteurDAO.save(nouvelAuteur);
        System.out.println(auteurEnBase.getId()); // affiche le vrai id*/
        AuteurDAO auteurDAO = new AuteurDAOImpl();
        List<Auteur> auteurs = auteurDAO.findAll();
        System.out.println(auteurs);
    }
}
