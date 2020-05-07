package com.sample.controllers;

import com.sample.App;
import com.sample.alerts.AlertsToNewUser;
import com.sample.exceptions.user.*;
import com.sample.users.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the view where users can be registered.
 */

public class RegisterUserController {

    UserRegister userRegister = new UserRegister();

    @FXML
    private TextField txtUserEmail, txtAdminUsername, txtUserFirstName, txtUserLastName, txtUserPhone;

    @FXML
    private PasswordField txtPassword, txtRePassword;

    @FXML
    private CheckBox chkAdminUserId;

    @FXML
    private PasswordField txtAdminPassword;

    @FXML
    private Label lblErrorData;

    @FXML
    void btnRegister(ActionEvent event) {
        lblErrorData.setText("");

        try {
            userRegister.openUserRegisterFromProgramFile();
            if(userRegister.userNameExists(txtUserEmail)){
                lblErrorData.setText("This user already exists.");
            }
            else {
                if (chkAdminUserId.isSelected()) {
                    if (txtUserEmail.getText().isEmpty() || txtUserFirstName.getText().isEmpty() ||
                            txtUserLastName.getText().isEmpty() || txtUserPhone.getText().isEmpty() ||
                            txtPassword.getText().isEmpty() || txtRePassword.getText().isEmpty() ||
                            txtAdminUsername.getText().isEmpty() || txtAdminPassword.getText().isEmpty()) {
                        lblErrorData.setText("Missing information. You need to fill out all fields.");
                    }
                    else {
                        if (!txtPassword.getText().equals(txtRePassword.getText())) {
                            lblErrorData.setText("The passwords doesn't match.");
                        }
                        else if (!userRegister.isAdminUser(txtAdminUsername, txtAdminPassword)) {
                            lblErrorData.setText("This is not an admin user.");
                        }
                        else {
                            try {
                                User newUser = new User(txtUserEmail.getText(), txtPassword.getText(), txtUserFirstName.getText(),
                                        txtUserLastName.getText(), txtUserPhone.getText(), true);
                                userRegister.addUser(newUser);
                                userRegister.saveUserRegisterToProgramFile();
                                resetInputFields();
                                new AlertsToNewUser().newUserConfirmationAlert();
                            }
                            catch(InvalidUserEmailException e){
                                lblErrorData.setText("Invalid email");
                            }
                            catch(InvalidUserPasswordException e){
                                lblErrorData.setText("The password must be between 5 and 14 digits and include at least one number between 0 and 9.");
                            }
                            catch(InvalidUserFirstNameException e){
                                lblErrorData.setText("Invalid first name.");
                            }
                            catch(InvalidUserLastNameException e){
                                lblErrorData.setText("Invalid last name.");
                            }
                            catch(InvalidUserPhoneException e){
                                lblErrorData.setText("Invalid phone number");
                            }
                        }
                    }
                }
                else {
                    if (txtUserEmail.getText().isEmpty() || txtUserFirstName.getText().isEmpty() ||
                            txtUserLastName.getText().isEmpty() || txtUserPhone.getText().isEmpty() ||
                            txtPassword.getText().isEmpty() || txtRePassword.getText().isEmpty()) {
                        lblErrorData.setText("Missing information. You need to fill out all fields.");
                    }
                    else {
                        if (!txtPassword.getText().equals(txtRePassword.getText())) {
                            lblErrorData.setText("The passwords doesn't match.");
                        }
                        else {
                            try {
                                User newUser = new User(txtUserEmail.getText(), txtPassword.getText(), txtUserFirstName.getText(),
                                        txtUserLastName.getText(), txtUserPhone.getText(), false);
                                userRegister.addUser(newUser);
                                userRegister.saveUserRegisterToProgramFile();
                                resetInputFields();
                                new AlertsToNewUser().newUserConfirmationAlert();
                            }
                            catch(InvalidUserEmailException e){
                                lblErrorData.setText("Invalid email");
                            }
                            catch(InvalidUserPasswordException e){
                                lblErrorData.setText("The password must be between 5 and 14 digits and include at least one number between 0 and 9.");
                            }
                            catch(InvalidUserFirstNameException e){
                                lblErrorData.setText("Invalid first name.");
                            }
                            catch(InvalidUserLastNameException e){
                                lblErrorData.setText("Invalid last name.");
                            }
                            catch(InvalidUserPhoneException e){
                                lblErrorData.setText("Invalid phone number");
                            }
                        }
                    }
                }
            }
        }
        catch(IOException | ClassNotFoundException e){
            new AlertsToNewUser().registerErrorAlert();
            try {
                App.changeView("loginview.fxml");
            }
            catch(IOException e2){
                new AlertsToNewUser().programFailAlert();
            }
        }
    }


    @FXML
    void chkAdminUser(ActionEvent event) {
        if(chkAdminUserId.isSelected()){
            txtAdminUsername.setDisable(false);
            txtAdminPassword.setDisable(false);
        } else {
            txtAdminUsername.setDisable(true);
            txtAdminPassword.setDisable(true);
        }
    }

    @FXML
    public void btnBack(ActionEvent actionEvent) throws IOException {
        App.changeView("loginview.fxml");
    }

    @FXML
    public void resetInputFields(){
        txtUserEmail.setText("");
        txtUserFirstName.setText("");
        txtUserLastName.setText("");
        txtUserPhone.setText("");
        txtPassword.setText("");
        txtRePassword.setText("");
        txtAdminUsername.setText("");
        txtAdminPassword.setText("");
    }

}


