package controllers;

import entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * This is the controller for the login functionality.
 */

public class LoginController{
    
    @FXML
    private TextField tfUserName;
    
    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private Button btnSignIn; 
    
    private UserEntity user;

    public LoginController(){
        user = new UserEntity();
    }

    @FXML
    public void initialize() {
        btnSignIn.setOnAction((event) -> {
            signIn(tfUserName.getText(), pfPassword.getText());
        });       
    }
    
    private void signIn(String userName, String password){
        user.setScreenName(userName);
        user.setPassword(password);  
    }
    
    public UserEntity getUser(){
        return user;
    }
}
