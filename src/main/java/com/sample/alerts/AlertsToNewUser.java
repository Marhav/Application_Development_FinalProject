package com.sample.alerts;

import javafx.scene.control.Alert;

public class AlertsToNewUser {

    public void newUserConfirmationAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("New user is registered!");
        alert.setContentText("You have been registered as a new user.");
        alert.showAndWait();
    }

    public void registerErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unable to register new user");
        alert.setHeaderText("Can't register new user!");
        alert.setContentText("Can't register new user. Try again later.");
        alert.showAndWait();
    }

    public void programFailAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unexpected error");
        alert.setHeaderText("An unexpected error has occured!");
        alert.setContentText("An unexpected error has occured. Try again later.");
        alert.showAndWait();
    }

    public void loginErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Can't log in");
        alert.setHeaderText("It's not possible to log in!");
        alert.setContentText("Can't log in. Try again later.");
        alert.showAndWait();
    }
}
