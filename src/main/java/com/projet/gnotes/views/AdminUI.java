package com.projet.gnotes.views;

import com.projet.gnotes.controllers.MatiereController;
import com.projet.gnotes.models.Matiere;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminUI extends BaseInterface{
    
    public AdminUI(Stage primaryStage,String name){
        super(primaryStage,name);
    }
    @Override
    protected void initSidebar(){
        
        //image du profil de l'admin
        ImageView adProfile = imageProfil("/img/admin.png");

        //bienvenue + son nom
        Label welcome = new Label("Bienvenue "+ name);
        welcome.setFont(poppinsRegular);
        welcome.getStyleClass().addAll("font-semibold");
        welcome.setTextFill(Color.rgb(12,12,12));

        Label role = new Label("Administrateur");
        role.setFont(barlow);
        role.setTextFill(Color.rgb(133, 131, 131));

        //box contenant ces deux éléments plus haut
        profil.getChildren().addAll(adProfile,welcome,role);

        //boutons
        HBox matiere = navButton("\uf51c", "Matiere", fontAwesome3, montserrat,255, "corail");
        matiere.setOnMouseClicked(e -> setContentIn(creerMatiereVue()));

        HBox dashboard = navButton("\uf51c", "Dashboard", fontAwesome3, montserrat,81, "");

        HBox enseignant = navButton("\uf508", "Enseignants", fontAwesome3, montserrat,81, "");
        enseignant.setOnMouseClicked(e -> setContentIn(creerEnseignantVue()));

        HBox etudiantButton = navButton("\uf501", "Etudiants", fontAwesome3, montserrat, 81,"");
        etudiantButton.setOnMouseClicked(e -> setContentIn(creerEtudiantVue()));

        HBox bulletin = navButton("\uf328", "Relévé de notes", fontAwesome2, montserrat,81, "");

        buttons.setPadding(new Insets(10,0,0,0));

        buttons.getChildren().addAll(dashboard,bulletin,matiere,enseignant,etudiantButton);

        space.setPrefHeight(8);
    }

    @Override
    protected VBox DefaultView(){
        return creerMatiereVue();
    }

    private VBox creerMatiereVue(){

        Label nom = new Label("Matières");
        nom.setFont(poppinsRegularTitle);

        VBox box = new VBox(10);
        box.getChildren().addAll(nom,new AdminMatiereTab());
        return box;
    }

    private VBox creerEnseignantVue(){
        
        Label nom = new Label("Enseignants");
        nom.setFont(poppinsRegularTitle);
        
        VBox box = new VBox(10);
        box.getChildren().addAll(nom,new AdminEnseignantTab());
        return box;

    }

    private VBox creerEtudiantVue(){
        
        Label nom = new Label("Etudiants");
        nom.setFont(poppinsRegularTitle);
        
        VBox box = new VBox(10);
        box.getChildren().addAll(nom,new AdminEtudiantTab());
        return box;

    }


}
