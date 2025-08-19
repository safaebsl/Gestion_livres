package com.livres.gestionlivres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GestionLivres;encrypt=true;trustServerCertificate=true;";
    private static final String UTILISATEUR = "SAFFOU";
    private static final String MOT_DE_PASSE = "";

    public static Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
    }
}
