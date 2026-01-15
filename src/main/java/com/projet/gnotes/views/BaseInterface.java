package com.projet.gnotes.views;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.projet.gnotes.App;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class BaseInterface extends HBox{
    
    protected VBox sidebar;
    protected ScrollPane contentPane;
    protected VBox buttons;
    protected VBox profil;
    protected VBox space;
    protected Stage primaryStage;
    protected String name;
    //ajout des icones
    protected  Font fontAwesome = Font.loadFont(getClass().getResourceAsStream("/fonts/font_awesome/Font Awesome 6 Free-Solid-900.otf"),30);
    protected  Font fontAwesome3 = Font.loadFont(getClass().getResourceAsStream("/fonts/font_awesome/Font Awesome 6 Free-Solid-900.otf"),20);
    protected Font fontAwesome2 = Font.loadFont(getClass().getResourceAsStream("/fonts/font_awesome/Font Awesome 6 Free-Regular-400.otf"),20);


    //Creation des fonts
    protected  Font poppinsBold = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Poppins-Bold.ttf"),30);
    protected  Font inter = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Inter-VariableFont_opsz,wght.ttf"),17);
    protected  Font montserrat = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Montserrat-Regular.ttf"),15);
    protected  Font interBold = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Inter_18pt-SemiBold.ttf"),15);
    protected  Font poppinsRegular = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Poppins-Regular.ttf"),15);
    protected  Font poppinsRegularTitle = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/Poppins-Regular.ttf"),30);

    protected  Font barlow = Font.loadFont(getClass().getResourceAsStream("/fonts/google_fonts/BarlowSemiCondensed-Bold.ttf"),15);

    public BaseInterface(Stage primaryStage, String name){
        this.primaryStage = primaryStage;
        this.name = name;

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
        logo.setPadding(new Insets(20,0,15.0,0));
        // logo.getStyleClass().add("logo");
        logo.getChildren().addAll(icone, nom);

        //container de la photo de profil et du nom
        profil = new VBox(10);
        profil.setAlignment(Pos.CENTER);

        buttons = new VBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20,0,0,0));

        //espace
        space = new VBox();
        
        //Création du bouton logOut
        Label icoLogOut = new Label("\uf2f5");
        icoLogOut.setFont(fontAwesome3);
        icoLogOut.setTextFill(Color.rgb(12,12,12));


        Label logText = new Label("Se déconnecter");
        logText.setFont(inter);
        logText.setTextFill(Color.rgb(12,12,12));
        logText.getStyleClass().add("font-semibold");

        HBox logOut = new HBox(10);
        logOut.setAlignment(Pos.CENTER);
        logOut.getChildren().addAll(icoLogOut,logText);

        // Action lors du clic sur "Se Deconnecter"
        logOut.setOnMouseClicked(e -> {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Déconnexion");
            alert.setHeaderText("Confirmer la déconnexion");
            alert.setContentText("Voulez-vous vraiment vous déconnecter ?");

            // Affiche la boîte de dialogue et attend la réponse
            Optional<ButtonType> result = alert.showAndWait();

            // Vérifie si l'utilisateur a cliqué sur "OK"
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("L'utilisateur a confirme la deconnexion.");
                returnToHomePage(primaryStage);
            } else {
                System.out.println("Deconnexion annulee.");
            }

        });

        sidebar = new VBox(15);
        sidebar.setPrefWidth(0.3*1000);
        sidebar.getStyleClass().add("sidebar");
        sidebar.getChildren().addAll(logo,profil,buttons,space,logOut);

        contentPane = new ScrollPane();
        contentPane.setPrefWidth(0.7*1000);
        contentPane.getStyleClass().add("white-back");

        this.setMinWidth(300);
        this.setMinHeight(400);
        this.setWidth(700);
        this.setHeight(400);
        this.setHgrow(sidebar, Priority.ALWAYS);
        this.setHgrow(contentPane, Priority.ALWAYS);
        this.getChildren().addAll(sidebar, contentPane);  

        initSidebar();
        setContentIn(DefaultView());


        this.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

    }

    protected void setContentIn(VBox content){
        content.setPadding(new Insets(30,0,0,20));
        contentPane.setContent(content);
    }

    protected ImageView imageProfil(String cheminImage){
        Image img = new Image(getClass().getResource(cheminImage).toExternalForm());
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        imgView.setSmooth(true);
        imgView.setCache(true);
        return imgView;
    }

    protected HBox navButton(String iconeText, String texString, Font fonticoneText, Font fontText, Integer icoText, String colorDiv){
        Label nomIcone = new Label(iconeText);
        nomIcone.setFont(fonticoneText);
        nomIcone.setTextFill(Color.rgb(icoText,icoText,icoText));

        Label Text = new Label(texString);
        Text.setFont(fontText);
        Text.setTextFill(Color.rgb(icoText,icoText,icoText));

        HBox box = new HBox(10);
        box.setPadding(new Insets(15));
        box.getStyleClass().addAll("border-radius",colorDiv,"font-semibold");
        box.setAlignment(Pos.CENTER);
        box.setMaxWidth(200);
        box.getChildren().addAll(nomIcone,Text);

        // box.setOnMouseEntered(event ->{
        //     box.getStyleClass().add("eval");
        //     nomIcone.setTextFill(Color.rgb(7,88,153));
        //     Text.setTextFill(Color.rgb(7,88,153));
        // });
        // box.setOnMouseExited(event ->{
        //     box.getStyleClass().remove("eval");
        //     nomIcone.setTextFill(Color.rgb(255, 255, 255, 0.88));
        //     Text.setTextFill(Color.rgb(255, 255, 255, 0.88));
        // });


        return box;
    }

    private void returnToHomePage(Stage primaryStage) {
        App mainApp = new App(); // Créer une nouvelle instance de la classe Main
        try {
            mainApp.start(primaryStage); // Appeler la méthode start pour rediriger vers la page d'accueil
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions si nécessaire
        }
    }

    protected abstract void initSidebar();
    protected abstract VBox DefaultView();

}
