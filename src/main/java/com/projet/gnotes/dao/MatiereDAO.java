package com.projet.gnotes.dao;

import com.projet.gnotes.models.Matiere;
import java.sql.*;
import java.util.*;

public class MatiereDAO {

    public static boolean insert(Matiere m) {
        String sql = "INSERT INTO matiere(code, intitule, credits) VALUES(?, ?, ?)";
        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getCode());
            stmt.setString(2, m.getIntitule());
            stmt.setInt(3, m.getCredits());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Matiere m) {
        String sql = "UPDATE matiere SET code=?, intitule=?, credits=? WHERE idM=?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getCode());
            stmt.setString(2, m.getIntitule());
            stmt.setInt(3, m.getCredits());
            stmt.setInt(4, m.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id) {
        String sql = "DELETE FROM matiere WHERE idM=?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Matiere> getAll() {
        List<Matiere> matieres = new ArrayList<>();
        String sql = "SELECT * FROM matiere";

        try (Connection conn = DbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                matieres.add(new Matiere(
                    rs.getInt("idM"),
                    rs.getString("code"),
                    rs.getString("intitule"),
                    rs.getInt("credits")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matieres;
    }
}

