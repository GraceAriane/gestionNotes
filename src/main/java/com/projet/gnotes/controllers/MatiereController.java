package com.projet.gnotes.controllers;


import com.projet.gnotes.dao.MatiereDAO;
import com.projet.gnotes.models.Matiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MatiereController {

    public static ObservableList<Matiere> chargerToutesLesMatieres() {
        return FXCollections.observableArrayList(MatiereDAO.getAll());
    }

    public static boolean ajouterMatiere(Matiere m) {
        return MatiereDAO.insert(m);
    }

    public static boolean modifierMatiere(Matiere m) {
        return MatiereDAO.update(m);
    }

    public static boolean supprimerMatiere(int id) {
        return MatiereDAO.delete(id);
    }
}

