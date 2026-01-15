package com.projet.gnotes.dao;

import com.projet.gnotes.models.Utilisateur;
import java.sql.*;

public class UtilisateurDAO {
    private Connection conn;

    public UtilisateurDAO(Connection conn) {
        this.conn = conn;
    }

    public Utilisateur findByMatriculeAndRole(String matricule, String role) {
        String query = "SELECT * FROM utilisateur WHERE matricule = ? AND role = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, matricule);
            stmt.setString(2, role);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("idU"));
                u.setMatricule(rs.getString("matricule"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

