package com.sample.controllers;

import com.sample.App;
import com.sample.alerts.AlertsToNewUser;
import com.sample.users.UserRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the login view.
 */

public class LoginController {

    private UserRegister userRegister = new UserRegister();

    //MÅ SLETTES SENERE, KUN HURTIGKNAPP TIL CUSTOMER VIEW
    public void changeToCustomer() throws IOException {
        App.changeView("customerview.fxml");
    }

    //MÅ SLETTES SENERE, KUN HURTIGKNAPP TIL ADMIN VIEW
    public void changeToAdmin() throws IOException {
        App.changeView("adminview.fxml");
    }

    @FXML
    private PasswordField txtLoginPassword;

    @FXML
    private TextField txtLoginUsername;

    @FXML
    private Label lblLoginError;

    @FXML
    void btnLogin(ActionEvent event) {
        lblLoginError.setText("");

        if(txtLoginUsername.getText().equals("admin") && txtLoginPassword.getText().equals("admin")){
            try {
                App.changeView("adminview.fxml");
            }
            catch(IOException e){
                new AlertsToNewUser().programFailAlert();
            }
        }
        else if(txtLoginUsername.getText().equals("user") && txtLoginPassword.getText().equals("user")){
            try {
                App.changeView("customerview.fxml");
            }
            catch(IOException e){
                new AlertsToNewUser().programFailAlert();
            }
        }
        else {
            try {
                userRegister.openUserRegisterFromProgramFile();
                if (userRegister.userNameExists(txtLoginUsername)) {
                    if (userRegister.isAdminUser(txtLoginUsername, txtLoginPassword)) {
                        try {
                            App.changeView("adminview.fxml");
                        }
                        catch(IOException e){
                            new AlertsToNewUser().programFailAlert();
                        }
                    } else if (userRegister.isUser(txtLoginUsername, txtLoginPassword)) {
                        try {
                            App.changeView("customerview.fxml");
                        }
                        catch(IOException e){
                            new AlertsToNewUser().programFailAlert();
                        }
                    } else {
                        lblLoginError.setText("Username and password mismatch");
                    }
                } else {
                    lblLoginError.setText("This username doesn't exist");
                }

            }
            catch (IOException | ClassNotFoundException e) {
                new AlertsToNewUser().loginErrorAlert();
            }
        }
    }

    @FXML
    void btnNewUser(ActionEvent event) throws IOException {
        App.changeView("registerUserview.fxml");
    }
}
