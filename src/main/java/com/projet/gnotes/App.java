package com.projet.gnotes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import com.projet.gnotes.views.*;
import com.projet.gnotes.controllers.AuthController;
import com.projet.gnotes.database.*;
import com.projet.gnotes.models.Utilisateur;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage primaryStage){

        //ajout des icones
        Font fontAwesome = Font.loadFont(getClass().getResourceAsStream("/fonts/font_awesome/Font Awesome 6 Free-Solid-900.otf"),30);

        //Creation des fonts
        Font poppinsBold = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Poppins-Bold.ttf"),30);
        Font inter = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Inter-VariableFont_opsz,wght.ttf"),17);
        Font montserrat = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Montserrat-Regular.ttf"),18);
        Font interBold = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Inter_18pt-SemiBold.ttf"),15);
    
        //le logo de l'application
        Label icone = new Label("\ue594");
        icone.setFont(fontAwesome);
        icone.setTextFill(Color.BLACK);

        Label nom = new Label("GNotes");
        nom.setFont(poppinsBold);
        nom.setTextFill(Color.BLACK);

        //BOX qui contient ces deux éléments
        HBox logo = new HBox(10);
        logo.setAlignment(Pos.CENTER);
        logo.setPadding(new Insets(10.0,0,15.0,0));
        // logo.getStyleClass().add("logo");
        logo.getChildren().addAll(icone, nom);

        //petite intro
        Label intro = new Label("Bienvenue sur GNotes, votre application de gestion de notes.\n                        Commencez par vous connecter");
        intro.setFont(montserrat);
        intro.getStyleClass().add("intro");
        intro.setTextFill(Color.rgb(19, 19, 19));

        HBox iBox = new HBox();
        iBox.setAlignment(Pos.CENTER);
        iBox.getChildren().add(intro);
        iBox.getStyleClass().add("ibox");

        //matricule box
        Label mLabel = new Label("Matricule :");
        mLabel.setFont(inter);

        TextField mField = new TextField();
        mField.setStyle("-fx-background-color: #f9f9f9;");
        mField.setPrefWidth(300); 
        mField.setPrefHeight(50);

        HBox matriculeBox = new HBox(30);
        matriculeBox.getChildren().addAll(mLabel, mField);
        matriculeBox.setAlignment(Pos.CENTER);

        //nom box
        Label nLabel = new Label("Nom :");
        nLabel.setFont(inter);

        TextField nField = new TextField();
        nField.setStyle("-fx-background-color: #f9f9f9;");
        nField.setPrefWidth(300); 
        nField.setPrefHeight(50);

        HBox nomBox = new HBox(70);
        nomBox.getChildren().addAll(nLabel, nField);
        nomBox.setAlignment(Pos.CENTER);

        //role box
        Label rLabel = new Label("Profil :");
        rLabel.setFont(inter);

        ComboBox<String> listBox = new ComboBox<>();
        listBox.setStyle("-fx-font-family: 'Inter'; -fx-font-size:40px" );
        listBox.getItems().addAll("administrateur", "etudiant", "enseignant");
        listBox.setPromptText("Choisissez votre profil");
        listBox.setMinSize(330, 50);
        listBox.setStyle("-fx-focus-color:rgb(255, 255, 255); -fx-background-color: #f9f9f9;");

        HBox listHbox = new HBox(60);
        listHbox.setAlignment(Pos.CENTER);
        listHbox.getChildren().addAll(rLabel, listBox);

        //button de connexion
        Button connexion = new Button("Connexion");
        connexion.setFont(interBold);
        connexion.setMinSize(200, 50);
        connexion.setTextFill(Color.WHITE);
        connexion.getStyleClass().add("connexion");

        Button enseignant = new Button("enseignant");
        enseignant.setFont(interBold);
        enseignant.setMinSize(150, 50);
        enseignant.setTextFill(Color.WHITE);
        enseignant.getStyleClass().add("connexion");

        Button admin = new Button("admin");
        admin.setFont(interBold);
        admin.setMinSize(200, 50);
        admin.setTextFill(Color.WHITE);
        admin.getStyleClass().add("connexion");

        Button etudiant = new Button("etudiant");
        etudiant.setFont(interBold);
        etudiant.setMinSize(200, 50);
        etudiant.setTextFill(Color.WHITE);
        etudiant.getStyleClass().add("connexion");


        //box qui contient les différents zones de textes
        VBox containerInfos = new VBox(40);        
        containerInfos.setPadding(new Insets(10,20,30,20));
        containerInfos.setAlignment(Pos.CENTER);
        containerInfos.setPrefHeight(550);
        containerInfos.setMaxWidth(600);
        containerInfos.getStyleClass().add("containerInfos");
        containerInfos.getChildren().addAll(logo, iBox, matriculeBox,nomBox, listHbox, connexion);

        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-background-color: #F2E9E0;");
        container.getChildren().addAll(containerInfos);



        Scene sc = new Scene(container,1000, 720);
        sc.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setResizable(true);
        primaryStage.setScene(sc);
        primaryStage.setTitle("GNotes");

        connexion.setOnAction(event->{
            AuthController auth = new AuthController();
            Utilisateur utilisateur = auth.login(mField.getText(), listBox.getValue());

            if (utilisateur != null) {
                String role = utilisateur.getRole();
                switch (role) {
                    case "etudiant":
                        primaryStage.getScene().setRoot(new EtudiantUI(primaryStage, nField.getText()));

                    break;
                    case "enseignant":
                        primaryStage.getScene().setRoot(new EnseignantUI(primaryStage, nField.getText()));

                    break;
                    case "administrateur":
                        primaryStage.getScene().setRoot(new AdminUI(primaryStage, nField.getText()));
                    break;
                }
            } else {
                // afficher une alerte de profil non reconnu
                showError("Profil non reconnu ou matricule invalide !");
            }


            // primaryStage.getScene().setRoot(new AdminUI(primaryStage, "dfdfd"));

        });


        primaryStage.show();

    }

    private void showError(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}
