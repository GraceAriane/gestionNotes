package com.projet.gnotes.controllers;

import com.projet.gnotes.dao.UtilisateurDAO;
import com.projet.gnotes.models.Utilisateur;
import com.projet.gnotes.dao.DbConnection;

public class AuthController {
    private UtilisateurDAO utilisateurDAO;

    public AuthController() {
        utilisateurDAO = new UtilisateurDAO(DbConnection.connect());
    }

    public Utilisateur login(String matricule, String role) {
        return utilisateurDAO.findByMatriculeAndRole(matricule, role);
    }
}
