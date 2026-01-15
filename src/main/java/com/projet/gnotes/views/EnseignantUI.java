package com.projet.gnotes.views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnseignantUI extends BaseInterface{
    
    public EnseignantUI(Stage primaryStage, String name){
        super(primaryStage,name);
    }

    @Override
    protected void initSidebar(){

        //image du profil de l'enseigant
        ImageView profProfile = imageProfil("/img/prof.png");

        //bienvenue + son nom
        Label welcome = new Label("Bienvenue "+ name);
        welcome.setFont(poppinsRegular);
        welcome.getStyleClass().addAll("font-semibold");
        welcome.setTextFill(Color.rgb(12,12,12));

        Label role = new Label("Enseignant");
        role.setFont(barlow);
        role.setTextFill(Color.rgb(133, 131, 131));

        //box contenant ces deux éléments plus haut
        profil.getChildren().addAll(profProfile,welcome,role);

        //boutons
        HBox notes = navButton("\uf328", "Notes", fontAwesome2, montserrat,255, "corail");

        HBox etudiantButton = navButton("\uf02d", "Etudiants", fontAwesome3, montserrat, 81,"");

        buttons.getChildren().addAll(notes,etudiantButton);

        space.setPrefHeight(200);

    }

    @Override
    protected VBox DefaultView(){
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(new Label("pas encore d'interface pour le moment"));
        return box;
    }

}
