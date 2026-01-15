package com.projet.gnotes.views;

import com.projet.gnotes.models.Utilisateur;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.Label;

public class ModaleEtudiant {
        public static Utilisateur afficherFormulaire(Utilisateur e) {
        Dialog<Utilisateur> dialog = new Dialog<>();
        dialog.setTitle(e == null ? "Ajouter un etudiant" : "Modifier un etudiant");

        Label lblMat = new Label("Matricule:");
        Label lblNom = new Label("Nom:");
        Label lblPrenom = new Label("Prénom:");
        Label lblSpecialite = new Label("Specialite:");
        

        TextField tfMat = new TextField();
        TextField tfNom = new TextField();
        TextField tfPrenom = new TextField();
        TextField tfSpecialite = new TextField();


        if (e != null) {
            tfMat.setText(e.getMatricule());
            tfNom.setText(e.getNom());
            tfPrenom.setText(e.getPrenom());
            tfSpecialite.setText(e.getSpecialite());
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.add(lblMat, 0, 0); grid.add(tfMat, 1, 0);
        grid.add(lblNom, 0, 1); grid.add(tfNom, 1, 1);
        grid.add(lblPrenom, 0, 2); grid.add(tfPrenom, 1, 2);
        grid.add(lblSpecialite, 0, 3); grid.add(tfSpecialite, 1, 3);


        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(bt -> {
            if (bt == ButtonType.OK) {
                return new Utilisateur(e != null ? e.getId() : 0, tfMat.getText(), tfNom.getText(), tfPrenom.getText(),"etudiant", tfSpecialite.getText());
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
