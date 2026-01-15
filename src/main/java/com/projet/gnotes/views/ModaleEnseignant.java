package com.projet.gnotes.views;

import com.projet.gnotes.models.Utilisateur;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ModaleEnseignant {
    public static Utilisateur afficherFormulaire(Utilisateur e) {
        Dialog<Utilisateur> dialog = new Dialog<>();
        dialog.setTitle(e == null ? "Ajouter un enseignant" : "Modifier un enseignant");

        Label lblMat = new Label("Matricule:");
        Label lblNom = new Label("Nom:");
        Label lblPrenom = new Label("Prénom:");
        Label lblDiscipline = new Label("Discipline:");
        Label lblGrade = new Label("Grade:");

        TextField tfMat = new TextField();
        TextField tfNom = new TextField();
        TextField tfPrenom = new TextField();
        TextField tfDiscipline = new TextField();
        TextField tfGrade = new TextField();


        if (e != null) {
            tfMat.setText(e.getMatricule());
            tfNom.setText(e.getNom());
            tfPrenom.setText(e.getPrenom());
            tfDiscipline.setText(e.getDiscipline());
            tfGrade.setText(e.getGrade());
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.add(lblMat, 0, 0); grid.add(tfMat, 1, 0);
        grid.add(lblNom, 0, 1); grid.add(tfNom, 1, 1);
        grid.add(lblPrenom, 0, 2); grid.add(tfPrenom, 1, 2);
        grid.add(lblDiscipline, 0, 3); grid.add(tfDiscipline, 1, 3);
        grid.add(lblGrade, 0, 4); grid.add(tfGrade, 1, 4);


        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(bt -> {
            if (bt == ButtonType.OK) {
                return new Utilisateur(e != null ? e.getId() : 0, tfMat.getText(), tfNom.getText(), tfPrenom.getText(),"enseignant", tfDiscipline.getText(), tfGrade.getText());
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
