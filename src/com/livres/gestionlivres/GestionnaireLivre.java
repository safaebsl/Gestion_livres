package com.livres.gestionlivres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireLivre {

    // Ajouter un livre
    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO Livres (titre, auteur, isbn, anneePublication, genre) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getIsbn());
            pstmt.setInt(4, livre.getAnneePublication());
            pstmt.setString(5, livre.getGenre());
            pstmt.executeUpdate();
            System.out.println("Livre ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Afficher tous les livres
    public List<Livre> getTousLesLivres() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livres";
        try (Connection conn = ConnexionBDD.getConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livre livre = new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getString("isbn"),
                    rs.getInt("anneePublication"),
                    rs.getString("genre")
                );
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Rechercher un livre par titre
    public Livre rechercherLivreParTitre(String titre) {
        String sql = "SELECT * FROM Livres WHERE titre = ?";
        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getString("isbn"),
                    rs.getInt("anneePublication"),
                    rs.getString("genre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Supprimer un livre par ID
    public void supprimerLivre(int id) {
        String sql = "DELETE FROM Livres WHERE id = ?";
        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Livre supprimé avec succès !");
            } else {
                System.out.println("Aucun livre trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Modifier un livre
    public void modifierLivre(Livre livre) {
        String sql = "UPDATE Livres SET titre = ?, auteur = ?, isbn = ?, anneePublication = ?, genre = ? WHERE id = ?";
        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getIsbn());
            pstmt.setInt(4, livre.getAnneePublication());
            pstmt.setString(5, livre.getGenre());
            pstmt.setInt(6, livre.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Livre modifié avec succès !");
            } else {
                System.out.println("Aucun livre trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
