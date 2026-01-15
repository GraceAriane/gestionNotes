package com.projet.gnotes.dao;

import com.projet.gnotes.models.Utilisateur;
import java.sql.*;
import java.util.*;

public class EnseignantDAO {
    private Connection conn;

    public EnseignantDAO() {
        this.conn = DbConnection.connect();
    }

    public List<Utilisateur> getAllEnseignants() {
        List<Utilisateur> list = new ArrayList<>();
        String query = "SELECT * FROM utilisateur WHERE role = 'enseignant'";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Utilisateur(
                    rs.getInt("idU"),
                    rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("role"),
                    rs.getString("discipline"),
                    rs.getString("grade")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void ajouter(Utilisateur enseignant) {
        String sql = "INSERT INTO utilisateur(matricule, nom, prenom, role, discipline, grade) VALUES (?, ?, ?,'enseignant', ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, enseignant.getMatricule());
            stmt.setString(2, enseignant.getNom());
            stmt.setString(3, enseignant.getPrenom());
            stmt.setString(4, enseignant.getDiscipline());
            stmt.setString(5, enseignant.getGrade());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Utilisateur e) {
        String sql = "UPDATE utilisateur SET matricule=?, nom=?, prenom=?, discipline=?, grade=? WHERE idU=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getMatricule());
            stmt.setString(2, e.getNom());
            stmt.setString(3, e.getPrenom());
            stmt.setString(4, e.getDiscipline());
            stmt.setString(5, e.getGrade());
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
