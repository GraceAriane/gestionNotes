package com.projet.gnotes.views;


import com.projet.gnotes.controllers.EtudiantController;
import com.projet.gnotes.models.Utilisateur;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import com.projet.gnotes.models.Utilisateur;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AdminEtudiantTab extends VBox{
        public AdminEtudiantTab() {

        TableView<Utilisateur> table = new TableView<>();
        table.setPrefWidth(650);
        EtudiantController controller = new EtudiantController(table);

        TableColumn<Utilisateur, String> colMat = new TableColumn<>("Matricule");
        colMat.setCellValueFactory(e -> new javafx.beans.property.SimpleStringProperty(e.getValue().getMatricule()));

        TableColumn<Utilisateur, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(e -> new javafx.beans.property.SimpleStringProperty(e.getValue().getNom()));

        TableColumn<Utilisateur, String> colPrenom = new TableColumn<>("Prenom");
        colPrenom.setCellValueFactory(e -> new javafx.beans.property.SimpleStringProperty(e.getValue().getPrenom()));

        TableColumn<Utilisateur, String> colSpecialite = new TableColumn<>("Specialite");
        colSpecialite.setCellValueFactory(e -> new javafx.beans.property.SimpleStringProperty(e.getValue().getSpecialite()));

        TableColumn<Utilisateur, Void> colActions = new TableColumn<>("Actions");
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button btnModif = new Button("Modifier");
            private final Button btnSuppr = new Button("Supprimer");

            {
                btnModif.getStyleClass().addAll("edit");
                btnSuppr.getStyleClass().addAll("delete");
                btnModif.setOnAction(e -> controller.modifier(getTableView().getItems().get(getIndex())));
                btnSuppr.setOnAction(e -> controller.supprimer(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else {
                    HBox box = new HBox(30, btnModif, btnSuppr);
                    box.setPrefWidth(250);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });
        table.getColumns().clear();
        table.getColumns().addAll(colMat, colNom, colPrenom,colSpecialite, colActions);

        Button btnAjouter = new Button("Ajouter");
        btnAjouter.getStyleClass().addAll("add","padding","border-radius");
        btnAjouter.setOnAction(e -> controller.ajouter());

        getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        setSpacing(15);
        getChildren().addAll(table, btnAjouter);
    }
}
