package com.steeve.bibliotheque.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnexionBD {

    private static final String FICHIER_CONFIG = "config.properties";

    public static Connection getConnection() throws SQLException, IOException {
        Properties proprietes = new Properties();

        try (InputStream input = ConnexionBD.class.getClassLoader().getResourceAsStream(FICHIER_CONFIG)) {
            if (input == null) {
                throw new IOException("Fichier " + FICHIER_CONFIG + " introuvable dans le classpath");
            }
            proprietes.load(input);
        }

        String url = proprietes.getProperty("db.url");
        String utilisateur = proprietes.getProperty("db.user");
        String motDePasse = proprietes.getProperty("db.password");

        return DriverManager.getConnection(url, utilisateur, motDePasse);
    }
}