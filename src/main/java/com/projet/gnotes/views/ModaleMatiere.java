package com.projet.gnotes.views;

import com.projet.gnotes.models.Matiere;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ModaleMatiere {

    public static Matiere afficherFormulaire(Matiere m) {
        Dialog<Matiere> dialog = new Dialog<>();
        dialog.setTitle(m == null ? "Ajouter une matière" : "Modifier une matière");

        Label lblCode = new Label("Code:");
        Label lblIntitule = new Label("Intitulé:");
        Label lblCredits = new Label("Crédits:");

        TextField tfCode = new TextField();
        TextField tfIntitule = new TextField();
        TextField tfCredits = new TextField();

        if (m != null) {
            tfCode.setText(m.getCode());
            tfIntitule.setText(m.getIntitule());
            tfCredits.setText(String.valueOf(m.getCredits()));
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.add(lblCode, 0, 0); grid.add(tfCode, 1, 0);
        grid.add(lblIntitule, 0, 1); grid.add(tfIntitule, 1, 1);
        grid.add(lblCredits, 0, 2); grid.add(tfCredits, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(bt -> {
            if (bt == ButtonType.OK) {
                try {
                    int credits = Integer.parseInt(tfCredits.getText().trim());
                    return new Matiere(m != null ? m.getId() : 0, tfCode.getText(), tfIntitule.getText(), credits);
                } catch (NumberFormatException e) {
                    // on peut afficher une erreur ou un null
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}

