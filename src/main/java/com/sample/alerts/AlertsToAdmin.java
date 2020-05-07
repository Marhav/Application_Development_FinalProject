package com.sample.alerts;

import com.sample.components.Component;
import com.sample.components.ComponentRegister;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class AlertsToAdmin {

    public void idAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Existing ID!");
        alert.setContentText("The ID you have inserted is already registered.");
        alert.showAndWait();
    }

    public void userListAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Can't load list of users");
        alert.setContentText("There is something wrong with the user list file.");
        alert.showAndWait();
    }

    public void userMailAlert(String errormessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong mail!");
        alert.setContentText(errormessage);
        alert.showAndWait();
    }

    public void userFirstNameAlert(String errormessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong first name!");
        alert.setContentText(errormessage);
        alert.showAndWait();
    }

    public void userLastNameAlert(String errormessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong last name!");
        alert.setContentText(errormessage);
        alert.showAndWait();
    }

    public void userPhoneAlert(String errormessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong phone number!");
        alert.setContentText(errormessage);
        alert.showAndWait();
    }

    public void userPasswordAlert(String errormessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong password!");
        alert.setContentText(errormessage);
        alert.showAndWait();
    }

    public void userAdminRightsAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Can not edit!");
        alert.setContentText("You can not edit if the user has admin rights or not.");
        alert.showAndWait();
    }


    public void nameAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong name!");
        alert.setContentText("You have inserted an invalid name. The name must be between 5 and 30 characters.");
        alert.showAndWait();
    }

    public void priceAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong price!");
        alert.setContentText("You have inserted an invalid price.");
        alert.showAndWait();
    }

    public void descriptionAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong description!");
        alert.setContentText("You have inserted an invalid description. The description must be between 5 and 50 characters.");
        alert.showAndWait();
    }

    public void missingInfoAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Information missing!");
        alert.setContentText("You need to fill out all the information.");
        alert.showAndWait();
    }

    public void missingTypeAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Missing type!");
        alert.setContentText("You have to choose a component type.");
        alert.showAndWait();
    }

    public void wrongComponentFileFormatAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("The component list can't be initialized!");
        alert.setContentText("The file you tried to open has incorrect format or doesn't exist.");
        alert.showAndWait();
    }

    public void overwriteAlert(ComponentRegister componentRegister, Component newComponent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error!");
        alert.setHeaderText("Wrong file format!");
        alert.setContentText("The file you are trying to save your components to is formatted incorrectly. " +
                "Do you want overwrite the file?");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> buttonChoice = alert.showAndWait();
        if (buttonChoice.get() == yesButton) {
            componentRegister.addComponentToList(newComponent);
            componentRegister.saveComponentRegisterToProgramFile();
        }
        else if (buttonChoice.get() == noButton){

        }
    }

    public void programFailAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error!");
        alert.setContentText("Try again later.");
        alert.showAndWait();
    }

    public void deleteFailAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Unable to delete component!");
        alert.setContentText("The component can't be deleted. Try again later.");
        alert.showAndWait();
    }

}
