package com.steeve.bibliotheque.dao;

import com.steeve.bibliotheque.modele.Auteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuteurDAOImpl implements AuteurDAO {

    @Override
    public Auteur save(Auteur auteur) throws SQLException {
        String sql = "INSERT INTO auteur (nom, prenom, nationalite) VALUES (?, ?, ?)";
        Auteur auteurSauvegarde = auteur;

        try (Connection connection = ConnexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, auteur.getNom());
            statement.setString(2, auteur.getPrenom());
            statement.setString(3, auteur.getNationalite());

            statement.executeUpdate();

            try (ResultSet clesGenerees = statement.getGeneratedKeys()) {
                if (clesGenerees.next()) {
                    int idGenere = clesGenerees.getInt(1);
                    auteurSauvegarde = new Auteur(idGenere, auteur.getNom(), auteur.getPrenom(), auteur.getNationalite());
                }

            }
        } catch (Exception e) {
            throw new SQLException("Erreur lors de la connexion ou de l'insertion", e);

        }
        return auteurSauvegarde;
    }

    @Override
    public Optional<Auteur> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Auteur> findAll() throws SQLException {
        String sql = "SELECT * FROM auteur";
        List<Auteur> auteurs = new ArrayList<>();

        try (Connection connection = ConnexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultat = statement.executeQuery()){

            while (resultat.next()){
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String nationalite = resultat.getString("nationalite");

                Auteur auteur = new Auteur(id, nom, prenom, nationalite);
                auteurs.add(auteur);
            }
        } catch (Exception e){
            throw new SQLException("Erreur lors de la récupération des auteurs", e);
        }

        return auteurs;
    }


}
