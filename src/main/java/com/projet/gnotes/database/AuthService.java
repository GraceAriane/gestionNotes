package com.projet.gnotes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AuthService {
    private static final String URL = "jdbc:mysql://localhost:3306/universite";
    private static final String USER = "root";
    private static final String PASSWORD = "root237";

    public static Connection connect() {
        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean verifierUtilisateur(String matricule,String role) {
        String query = "SELECT * FROM utilisateur WHERE matricule = ? and role = ?";
 
        try{
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, matricule);
            stmt.setString(2, role);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retourne true si l'étudiant existe
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

// String matricule = mField.getText();
// String role = listBox.getValue();

// if (AuthService.verifierUtilisateur(matricule,role)  && "etudiant".equals(role) ) {
//     primaryStage.getScene().setRoot(new EtudiantUI(primaryStage, nField.getText()));

// } else  if (AuthService.verifierUtilisateur(matricule,role)  && "enseignant".equals(role) ) {
//     primaryStage.getScene().setRoot(new EnseignantUI(primaryStage, nField.getText()));

// } else if (AuthService.verifierUtilisateur(matricule,role)  && "administrateur".equals(role) ) {
//     primaryStage.getScene().setRoot(new AdminUI(primaryStage, nField.getText()));

// } else {
//     System.out.println("Profil non reconnu ou matricule invalide !");
// }
