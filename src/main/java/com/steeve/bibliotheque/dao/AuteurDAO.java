package com.steeve.bibliotheque.dao;

import com.steeve.bibliotheque.modele.Auteur;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AuteurDAO {
    Auteur save(Auteur auteur) throws SQLException;
    Optional<Auteur> findById(int id) throws SQLException;
    List<Auteur> findAll() throws SQLException;
}
