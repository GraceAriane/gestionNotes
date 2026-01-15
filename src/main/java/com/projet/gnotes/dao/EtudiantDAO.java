package com.projet.gnotes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projet.gnotes.models.Utilisateur;

public class EtudiantDAO {
        private Connection conn;

    public EtudiantDAO() {
        this.conn = DbConnection.connect();
    }

    public List<Utilisateur> getAllEtudiants() {
        List<Utilisateur> list = new ArrayList<>();
        String query = "SELECT * FROM utilisateur WHERE role = 'etudiant'";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Utilisateur(
                    rs.getInt("idU"),
                    rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("role"),
                    rs.getString("specialite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void ajouter(Utilisateur etudiant) {
        String sql = "INSERT INTO utilisateur(matricule, nom, prenom, role, specialite) VALUES (?, ?, ?,'etudiant', ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, etudiant.getMatricule());
            stmt.setString(2, etudiant.getNom());
            stmt.setString(3, etudiant.getPrenom());
            stmt.setString(4,etudiant.getSpecialite());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Utilisateur e) {
        String sql = "UPDATE utilisateur SET matricule=?, nom=?, prenom=?, specialite=? WHERE idU=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getMatricule());
            stmt.setString(2, e.getNom());
            stmt.setString(3, e.getPrenom());
            stmt.setString(4, e.getSpecialite());
            stmt.setInt(6, e.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void supprimer(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM utilisateur WHERE idU=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
