package com.projet.gnotes.controllers;

import com.projet.gnotes.dao.EnseignantDAO;
import com.projet.gnotes.models.Utilisateur;
import com.projet.gnotes.views.ModaleEnseignant;
import javafx.collections.*;
import javafx.scene.control.*;

public class EnseignantController {
    private EnseignantDAO dao = new EnseignantDAO();
    private ObservableList<Utilisateur> data = FXCollections.observableArrayList();
    private TableView<Utilisateur> table;

    public EnseignantController(TableView<Utilisateur> table) {
        this.table = table;
        chargerDonnees();
    }

    public void chargerDonnees() {
        data.setAll(dao.getAllEnseignants());
        table.setItems(data);
    }

    public void ajouter() {
        Utilisateur nouveau = ModaleEnseignant.afficherFormulaire(null);
        if (nouveau != null) {
            dao.ajouter(nouveau);
            chargerDonnees();
        }
    }

    public void modifier(Utilisateur selected) {
        Utilisateur modif = ModaleEnseignant.afficherFormulaire(selected);
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
