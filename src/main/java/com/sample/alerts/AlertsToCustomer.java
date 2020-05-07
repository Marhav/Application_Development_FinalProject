package com.sample.alerts;

import com.sample.App;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class AlertsToCustomer {
    public void componentListAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Can't load list of components!");
        alert.setContentText("Components are not available. Try again later.");
        alert.showAndWait();
    }

    public void confirmedOrderAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order is confirmed!");
        alert.setHeaderText("Confirmed order!");
        alert.setContentText("Thank you for you order!");
        alert.showAndWait();
    }

    public void logOutAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cart is not empty!");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Your shopping cart is not empty. If you log out, the current cart items will get lost.");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> buttonChoice = alert.showAndWait();
        if (buttonChoice.get() == yesButton) {
            App.changeView("loginview.fxml");
        }
        else if (buttonChoice.get() == noButton){

        }
    }
    public void orderNotConfirmedAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Order is NOT confirmed!");
        alert.setHeaderText("Order NOT yet confirmed!");
        alert.setContentText("Please select directory to confirm!");
        alert.showAndWait();
    }

    public void noSelectedFileAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No file is chosen!");
        alert.setHeaderText("No file is chosen!");
        alert.setContentText("You have to chose a file to see the order!");
        alert.showAndWait();
    }

    public void wrongOrderFileFormatAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("The order can't be opened!");
        alert.setContentText("The order file you tried to open has incorrect format.");
        alert.showAndWait();
    }

    public void loadFailAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("The order page can't be loaded!");
        alert.setContentText("The order page can't be loaded. Try again later.");
        alert.showAndWait();
    }

    public boolean cartIsNotEmptyAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cart not empty!");
        alert.setHeaderText("Your shopping cart isn't empty!");
        alert.setContentText("If you choose to open a previous order you current cart items will get lost!");
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        Optional<ButtonType> buttonChoice = alert.showAndWait();
        if (buttonChoice.get() == okButton) {
           return true;
        }
        else{
            return false;
        }
    }
}
