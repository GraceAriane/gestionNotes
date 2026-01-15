package com.projet.gnotes.views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class EtudiantUI extends BaseInterface{
    
    public EtudiantUI(Stage primaryStage, String name){
        super(primaryStage,name);
    }

    @Override
    protected void initSidebar(){
        //image du profil de l'etudiant
        ImageView studentProfile = imageProfil("/img/eleve.png");

        //bienvenue + son nom
        Label welcome = new Label("Bienvenue "+ name);
        welcome.setFont(poppinsRegular);
        welcome.getStyleClass().addAll("font-semibold");
        welcome.setTextFill(Color.rgb(12,12,12));

        Label role = new Label("Etudiant");
        role.setFont(barlow);
        role.setTextFill(Color.rgb(133, 131, 131));

        //box contenant ces deux éléments plus haut
        profil.getChildren().addAll(studentProfile,welcome,role);

        //boutons
        HBox notes = navButton("\uf328", "Notes", fontAwesome2, montserrat,255, "corail");

        HBox bulletin = navButton("\uf02d", "Relevé de notes", fontAwesome3, montserrat, 81,"");

        buttons.getChildren().addAll(notes,bulletin);

        //espace entre les boutons
        space.setPrefHeight(200);
    }

    @Override
    protected VBox DefaultView(){
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(new Label("pas encore d'interfaces pour le moment"));
        return box;
    }
}
