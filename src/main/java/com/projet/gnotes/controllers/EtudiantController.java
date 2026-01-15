package com.projet.gnotes.controllers;

import com.projet.gnotes.dao.EtudiantDAO;
import com.projet.gnotes.models.Utilisateur;
import com.projet.gnotes.views.ModaleEtudiant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.scene.control.*;



public class EtudiantController {
    private EtudiantDAO dao = new EtudiantDAO();
    private ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    private TableView<Utilisateur> table;

    public EtudiantController(TableView<Utilisateur> table) {
        this.table = table;
        chargerDonnees();
    }

    public void chargerDonnees() {
        data.setAll(dao.getAllEtudiants());
        table.setItems(data);
    }

    public void ajouter() {
        Utilisateur nouveau = ModaleEtudiant.afficherFormulaire(null);
        if (nouveau != null) {
            dao.ajouter(nouveau);
            chargerDonnees();
        }
    }

    public void modifier(Utilisateur selected) {
        Utilisateur modif = ModaleEtudiant.afficherFormulaire(selected);
        if (modif != null) {
            dao.modifier(modif);
            chargerDonnees();
        }
    }

    public void supprimer(Utilisateur selected) {
        dao.supprimer(selected.getId());
        chargerDonnees();
    } 
}
