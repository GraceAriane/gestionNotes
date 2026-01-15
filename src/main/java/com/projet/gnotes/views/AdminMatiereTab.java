package com.projet.gnotes.views;

import com.projet.gnotes.models.Matiere;
import com.projet.gnotes.controllers.MatiereController;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class AdminMatiereTab extends VBox {

    private TableView<Matiere> table;
    private ObservableList<Matiere> data;

    public AdminMatiereTab() {
        table = new TableView<>();
        table.setPrefWidth(650);
        table.getColumns().clear();
        // Charger les données
        data = FXCollections.observableArrayList(MatiereController.chargerToutesLesMatieres());
        table.setItems(data);

        // Colonnes
        TableColumn<Matiere, String> codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setPrefWidth(100);

        TableColumn<Matiere, String> intituleCol = new TableColumn<>("Intitulé");
        intituleCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        intituleCol.setPrefWidth(200);

        TableColumn<Matiere, Integer> creditsCol = new TableColumn<>("Crédits");
        creditsCol.setCellValueFactory(new PropertyValueFactory<>("credits"));
        creditsCol.setPrefWidth(50);

        TableColumn<Matiere, Void> colActions = new TableColumn<>("Actions");
        colActions.setPrefWidth(290);
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button btnModif = new Button("Modifier");
            private final Button btnSuppr = new Button("Supprimer");

            {
                btnModif.getStyleClass().addAll("edit");
                btnSuppr.getStyleClass().addAll("delete");
                btnModif.setOnAction(e -> modifier(getTableView().getItems().get(getIndex())));
                btnSuppr.setOnAction(e -> supprimer(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(10, btnModif, btnSuppr);
                    box.setAlignment(Pos.CENTER);
                    setGraphic(box);
                }
            }
        });

        table.getColumns().addAll(codeCol, intituleCol, creditsCol, colActions);

        // Bouton ajouter
        Button btnAjouter = new Button("Ajouter");
        btnAjouter.getStyleClass().addAll("add","padding","border-radius");
        btnAjouter.setOnAction(e -> ajouter());

        // Layout
        getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        setPadding(new Insets(10,0,0,0));
        setSpacing(15);
        getChildren().addAll(table, btnAjouter);
    }

    private void ajouter() {
        Matiere nouvelle = ModaleMatiere.afficherFormulaire(null);
        if (nouvelle != null && MatiereController.ajouterMatiere(nouvelle)) {
            data.add(nouvelle);
        }
    }

    private void modifier(Matiere m) {
        Matiere modifiee = ModaleMatiere.afficherFormulaire(m);
        if (modifiee != null && MatiereController.modifierMatiere(modifiee)) {
            int index = data.indexOf(m);
            data.set(index, modifiee);
        }
    }

    private void supprimer(Matiere m) {
        if (MatiereController.supprimerMatiere(m.getId())) {
            data.remove(m);
        }
    }
}

