package com.sample.controllers;

import com.sample.App;
import com.sample.alerts.AlertsToAdmin;
import com.sample.exceptions.user.*;
import com.sample.users.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BooleanStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the view where users are managed.
 */

public class ManageUsersController implements Initializable {

    private UserRegister userRegister = new UserRegister();
    private ObservableList<String> cBox = FXCollections.observableArrayList();

    @FXML
    private TableView tvUsers;

    @FXML
    private TextField txtSearch;

    @FXML
    private ChoiceBox searchChoiceBox, isAdminChoiceBox;

    @FXML
    private TableColumn usernameCol, passwordCol, firstnameCol, lastnameCol, phoneCol, adminRightsCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchChoiceBox.setItems(new UserFilter().listOfFilterChoices);

        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        adminRightsCol.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

        String cbox1 = "Show all users";
        String cbox2 = "Admin";
        String cbox3 = "User";

        cBox.addAll(cbox1,cbox2,cbox3);
        isAdminChoiceBox.setItems(cBox);
        isAdminChoiceBox.setValue("Show all users");


        try {
            userRegister.openUserRegisterFromProgramFile();
            tvUsers.setItems(userRegister.makeFilteredRegister(txtSearch,tvUsers,isAdminChoiceBox));

        }
        catch (IOException | ClassNotFoundException e) {
            new AlertsToAdmin().userListAlert();
        }
    }

    @FXML
    void btnDeleteUser(ActionEvent event) throws IOException {
        userRegister.attachToTableView(tvUsers);

        User selected = (User) tvUsers.getSelectionModel().getSelectedItem();

        tvUsers.getItems().removeAll(selected);
        userRegister.deleteUser(selected);

        userRegister.saveUserRegisterToProgramFile();

        tvUsers.setItems(userRegister.makeFilteredRegister(txtSearch,tvUsers,isAdminChoiceBox));
    }


    public void btnGoToLoginView(ActionEvent actionEvent) throws IOException {
        App.changeView("loginview.fxml");
    }

    public void btnGoBackToAdminView(ActionEvent actionEvent) throws IOException {
        App.changeView("adminview.fxml");
    }


    @FXML
    private void phoneEdited(TableColumn.CellEditEvent<User, String> event) {
        try {
            event.getRowValue().setPhone(event.getNewValue());
            userRegister.saveUserRegisterToProgramFile();
        }

        catch (InvalidUserPhoneException e){
            new AlertsToAdmin().userPhoneAlert(e.getMessage());
            event.getRowValue().setPhone(event.getOldValue());
        }
        catch (IOException e) {
            new AlertsToAdmin().userListAlert();
        }

        tvUsers.refresh();
    }


    @FXML
    private void lastnameEdited(TableColumn.CellEditEvent<User, String> event) {
        try {
            event.getRowValue().setLastName(event.getNewValue());
            userRegister.saveUserRegisterToProgramFile();
        }

        catch (InvalidUserLastNameException e){
            new AlertsToAdmin().userLastNameAlert(e.getMessage());
            event.getRowValue().setLastName(event.getOldValue());
        }
        catch (IOException e) {
            new AlertsToAdmin().userListAlert();
        }
        tvUsers.refresh();
    }

    @FXML
    private void firstnameEdited(TableColumn.CellEditEvent<User, String> event) {
        try {
            event.getRowValue().setFirstName(event.getNewValue());
            userRegister.saveUserRegisterToProgramFile();
        }

        catch (InvalidUserFirstNameException e){
            new AlertsToAdmin().userFirstNameAlert(e.getMessage());
            event.getRowValue().setFirstName(event.getOldValue());
        }
        catch (IOException e) {
            new AlertsToAdmin().userListAlert();
        }
        tvUsers.refresh();
    }

    @FXML
    private void passwordEdited(TableColumn.CellEditEvent<User, String> event) {
        try {
            event.getRowValue().setPassword(event.getNewValue());
            userRegister.saveUserRegisterToProgramFile();
        }

        catch (InvalidUserPasswordException e){
            new AlertsToAdmin().userPasswordAlert(e.getMessage());
            event.getRowValue().setPassword(event.getOldValue());
        }
        catch (IOException e) {
            new AlertsToAdmin().userListAlert();
        }

        tvUsers.refresh();
    }

    @FXML
    private void usernameEdited(TableColumn.CellEditEvent<User, String> event) {
        try {
            event.getRowValue().setUserMail(event.getNewValue());
            userRegister.saveUserRegisterToProgramFile();
        }
        catch (InvalidUserEmailException e){
            new AlertsToAdmin().userMailAlert(e.getMessage());
            event.getRowValue().setUserMail(event.getOldValue());
        }
        catch (IOException e) {
            new AlertsToAdmin().userListAlert();
        }
        tvUsers.refresh();
    }

    @FXML
    private void adminRightsEdited(TableColumn.CellEditEvent<User, Boolean> event){
        try {
            event.getRowValue().setAdminRights(event.getNewValue());
            userRegister.saveUserRegisterToProgramFile();
        }
        catch(InvalidUserRightException e){
            new AlertsToAdmin().userAdminRightsAlert();
            event.getRowValue().setAdminRights(event.getOldValue());
        }
        catch(IOException e){
            new AlertsToAdmin().userListAlert();
        }
        tvUsers.refresh();
    }
}
