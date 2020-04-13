package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is the controller for the main window.
 */

public class MainController {

   @FXML
   private Button btnSignIn, btnSignUp;

   @FXML
   private Label lblSearch, lblFromDate, lblToDate, lblHotels, lblFlights;
   
   public MainController() {        
   } 
    
    @FXML
    public void initialize() {
        btnSignUp.setText("Sign Up");
        btnSignIn.setText("Sign In");        
       
        lblSearch.setText("Search engine");
        lblFromDate.setText("From");
        lblToDate.setText("To");
        lblHotels.setText("Hotel list");
        lblFlights.setText("Flight list");
        
        signInButtonHandler();
        signUpButtonHandler();
    }

    // A handler for the sign in button.
    private void signInButtonHandler() {
        btnSignIn.setOnAction((event) -> {           
            try {
                Parent p = FXMLLoader.load(getClass()
                        .getResource("/fxml/FXMLSignIn.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }          
        });
    }

    // A handler for the sign up button.
    private void signUpButtonHandler() {
        btnSignUp.setOnAction((event) -> {
            try {
                Parent p = FXMLLoader.load(getClass()
                        .getResource("/fxml/FXMLSignUp.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }
        });
    }
}
