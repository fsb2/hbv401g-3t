package controllers;

import entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This is the controller for the login functionality.
 * @author hjortur
 */

public class LoginController{
 
    @FXML
    private GridPane gridPane;
    
    @FXML
    private TextField tfUserName;
    
    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private Button btnSignIn; 
    
    private List<UserEntity> users; // This is not used a the moment.

    public LoginController(){
        users = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        btnSignIn.setOnAction((event) -> {
            signIn(tfUserName.getText(), pfPassword.getText());
        });       
    }
    
    private void signIn(String userName, String password){
        UserEntity user = new UserEntity();
        user.setScreenName(userName);
        user.setPassword(password);
         
        if(user.getScreenName() != null && user.getPassword() != null){
               System.out.println(user.getScreenName() + "  " + user.getPassword());
            users.add(user);
        }    
    }    
}
