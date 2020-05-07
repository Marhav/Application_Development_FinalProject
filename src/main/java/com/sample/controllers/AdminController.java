package com.sample.controllers;

import com.sample.App;
import com.sample.alerts.AlertsToAdmin;
import com.sample.alerts.AlertsToCustomer;
import com.sample.components.*;
import com.sample.converters.DoubleStringConverter;
import com.sample.exceptions.component.*;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the view of admin users.
 */

public class AdminController implements Initializable {

    ComponentRegister componentRegister = new ComponentRegister();
    ComponentRegister newRegister = new ComponentRegister();

    @FXML
    private TableView componentView;

    @FXML
    private TableColumn itemIdColumn, itemNameColumn, itemTypeColumn, itemPriceColumn, itemDescriptionColumn;

    @FXML
    private Button btnLogout, btnAddItem, deleteButton, btnShowAllItems, btnManageUsers;

    @FXML
    private ChoiceBox chooseComponent, filterChoice;

    @FXML
    private TextField txtItemID, txtItemName, txtItemDescription, txtItemPrice, txtSearchComponent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       chooseComponent.setItems(new ComponentList().listOfComponents);
       filterChoice.setItems(new ComponentFilter().listOfFilterChoices);

       itemIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       itemTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       itemDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       itemPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    @FXML
    private void logOut() {
        try {
            App.changeView("loginview.fxml");
        }
        catch(IOException e){
            new AlertsToAdmin().programFailAlert();
        }
    }

    @FXML
    public void changeToManageUsersView() throws IOException {
        try {
            App.changeView("manageUsersview.fxml");
        }
        catch (IOException e){
            new AlertsToAdmin().programFailAlert();
        }
    }

    @FXML
    private void addItemToList2() {
        String selectedComponent = chooseComponent.getSelectionModel().getSelectedItem().toString();
        if (selectedComponent.equals("Choose component..")) {
            new AlertsToAdmin().missingTypeAlert();
        }
        else if (txtItemID.getText().isEmpty() || txtItemName.getText().isEmpty() || txtItemDescription.getText().isEmpty() || txtItemPrice.getText().isEmpty()) {
            new AlertsToAdmin().missingInfoAlert();
        }
        else {
            String ID = txtItemID.getText();
            String componentName = txtItemName.getText();
            String componentDescription = txtItemDescription.getText();
            if (componentRegister.checkID(ID)){
                new AlertsToAdmin().idAlert();
            }
            else {
                try {
                    double componentPrice = Double.parseDouble(txtItemPrice.getText());
                    Component newComponent = new Component(ID, selectedComponent, componentName, componentDescription, componentPrice);
                    newRegister.addComponentToList(newComponent);
                    newRegister.attachToTableView(componentView);
                    componentView.setItems(newRegister.makeFilteredRegister(txtSearchComponent, componentView, filterChoice));
                    txtItemID.setText("");
                    txtItemName.setText("");
                    txtItemDescription.setText("");
                    txtItemPrice.setText("");
                    chooseComponent.setValue("Choose component..");

                    try {
                        componentRegister.openRegisterFromProgramFile();
                        componentRegister.addComponentToList(newComponent);
                        componentRegister.saveComponentRegisterToProgramFile();

                    }
                    catch (IOException | ClassNotFoundException e) {
                        new AlertsToAdmin().overwriteAlert(componentRegister, newComponent);
                    }

                } catch (NumberFormatException e) {
                    new AlertsToAdmin().priceAlert();
                } catch (InvalidProductNrException e) {
                    new AlertsToAdmin().idAlert();
                } catch (InvalidNameException e) {
                    new AlertsToAdmin().nameAlert();
                } catch (InvalidDescriptionException e) {
                    new AlertsToAdmin().descriptionAlert();
                } catch (InvalidPriceException e) {
                    new AlertsToAdmin().priceAlert();
                } catch (IOException e) {
                    new AlertsToAdmin().programFailAlert();
                }
            }
        }
    }

    @FXML
    private void addItemToList3() {
        String selectedComponent = chooseComponent.getSelectionModel().getSelectedItem().toString();
        if (selectedComponent.equals("Choose component..")) {
            new AlertsToAdmin().missingTypeAlert();
        }
        else if (txtItemID.getText().isEmpty() || txtItemName.getText().isEmpty() || txtItemDescription.getText().isEmpty() || txtItemPrice.getText().isEmpty()) {
            new AlertsToAdmin().missingInfoAlert();
        }
        else {
            String ID = txtItemID.getText();
            String componentName = txtItemName.getText();
            String componentDescription = txtItemDescription.getText();
            if (componentRegister.checkID(ID)){
                new AlertsToAdmin().idAlert();
            }
            else {
                try {
                    double componentPrice = Double.parseDouble(txtItemPrice.getText());
                    Component newComponent = new Component(ID, selectedComponent, componentName, componentDescription, componentPrice);
                    newRegister.addComponentToList(newComponent);
                    newRegister.attachToTableView(componentView);
                    componentView.setItems(newRegister.makeFilteredRegister(txtSearchComponent, componentView, filterChoice));
                    txtItemID.setText("");
                    txtItemName.setText("");
                    txtItemDescription.setText("");
                    txtItemPrice.setText("");
                    chooseComponent.setValue("Choose component..");

                    try {
                        componentRegister.openRegisterFromProgramFile();
                        componentRegister.addComponentToList(newComponent);
                        componentRegister.saveComponentRegisterToBackupFile();
                    }
                    catch (IOException | ClassNotFoundException e) {
                        new AlertsToAdmin().overwriteAlert(componentRegister, newComponent);
                    }

                } catch (NumberFormatException e) {
                    new AlertsToAdmin().priceAlert();
                } catch (InvalidProductNrException e) {
                    new AlertsToAdmin().idAlert();
                } catch (InvalidNameException e) {
                    new AlertsToAdmin().nameAlert();
                } catch (InvalidDescriptionException e) {
                    new AlertsToAdmin().descriptionAlert();
                } catch (InvalidPriceException e) {
                    new AlertsToAdmin().priceAlert();
                } catch (IOException e) {

                }
            }
        }
    }

    @FXML
    private void showComponentRegister() {
        componentRegister.setOnSucceeded(this::threadSucceeded);
        componentRegister.setOnFailed(this::threadFailed);

        Thread showRegister = new Thread(componentRegister);

        componentView.setDisable(true);
        chooseComponent.setDisable(true);
        txtItemID.setDisable(true);
        txtItemName.setDisable(true);
        txtItemName.setDisable(true);
        txtItemDescription.setDisable(true);
        txtItemPrice.setDisable(true);
        txtSearchComponent.setDisable(true);
        btnAddItem.setDisable(true);
        deleteButton.setDisable(true);
        btnShowAllItems.setDisable(true);
        filterChoice.setDisable(true);
        btnLogout.setDisable(true);
        btnManageUsers.setDisable(true);

        showRegister.start();
    }

    private void threadSucceeded(WorkerStateEvent e){
        newRegister = componentRegister;
        componentView.setItems(newRegister.makeFilteredRegister(txtSearchComponent,componentView, filterChoice));
        componentView.setDisable(false);
        chooseComponent.setDisable(false);
        txtItemID.setDisable(false);
        txtItemName.setDisable(false);
        txtItemName.setDisable(false);
        txtItemDescription.setDisable(false);
        txtItemPrice.setDisable(false);
        txtSearchComponent.setDisable(false);
        btnAddItem.setDisable(false);
        filterChoice.setDisable(false);
        deleteButton.setDisable(false);
        //btnShowAllItems.setDisable(false);
        btnLogout.setDisable(false);
        btnManageUsers.setDisable(false);
    }

    private void threadFailed(WorkerStateEvent e){
        new AlertsToAdmin().wrongComponentFileFormatAlert();
        componentView.setDisable(false);
        chooseComponent.setDisable(false);
        txtItemID.setDisable(false);
        txtItemName.setDisable(false);
        txtItemName.setDisable(false);
        txtItemDescription.setDisable(false);
        txtItemPrice.setDisable(false);
        txtSearchComponent.setDisable(false);
        btnAddItem.setDisable(false);
        filterChoice.setDisable(false);
        deleteButton.setDisable(false);
        //btnShowAllItems.setDisable(false);
        btnLogout.setDisable(false);
        btnManageUsers.setDisable(false);
    }

    @FXML
    private void deleteComponentFromTable() {
        try {
            newRegister.attachToTableView(componentView);
            Component selected = (Component) componentView.getSelectionModel().getSelectedItem();
            componentView.getItems().removeAll(selected);
            newRegister.deleteComponentFromList(selected);
            componentRegister.deleteComponentFromList(selected);
            componentRegister.saveComponentRegisterToProgramFile();
            componentView.setItems(newRegister.makeFilteredRegister(txtSearchComponent, componentView, filterChoice));
        }
        catch(IOException e){
            new AlertsToAdmin().deleteFailAlert();
        }
    }

    @FXML
    private void componentIDEdited(TableColumn.CellEditEvent<Component,String> event){
        try {
            if(!componentRegister.checkID(event.getNewValue())){
                event.getRowValue().setComponentID(event.getNewValue());
                componentRegister.saveComponentRegisterToProgramFile();
            }
            else {
                new AlertsToAdmin().idAlert();
                event.getRowValue().setComponentID(event.getOldValue());
            }
        }
        catch (IOException e) {
            new AlertsToAdmin().wrongComponentFileFormatAlert();
        }

        componentView.refresh();
    }

    @FXML
    private void componentTypeEdited(TableColumn.CellEditEvent<Component,String> event){
        try {
            event.getRowValue().setComponentType(event.getNewValue());
            componentRegister.saveComponentRegisterToProgramFile();
        }
        catch (InvalidTypeException | IOException e) {
            new AlertsToAdmin().missingTypeAlert();
            event.getRowValue().setComponentType(event.getOldValue());
        }

        componentView.refresh();
    }

    @FXML
    private void componentNameEdited(TableColumn.CellEditEvent<Component,String> event){
        try {
            event.getRowValue().setComponentName(event.getNewValue());
            componentRegister.saveComponentRegisterToProgramFile();
        }
        catch (InvalidNameException | IOException e) {
            new AlertsToAdmin().nameAlert();
            event.getRowValue().setComponentName(event.getOldValue());
        }

        componentView.refresh();
    }

    @FXML
    private void componentDescriptionEdited(TableColumn.CellEditEvent<Component,String> event){
        try {
            event.getRowValue().setComponentDescription(event.getNewValue());
            componentRegister.saveComponentRegisterToProgramFile();
        }
        catch (InvalidDescriptionException | IOException e){
            new AlertsToAdmin().nameAlert();
            event.getRowValue().setComponentDescription(event.getOldValue());
        }

        componentView.refresh();
    }

    @FXML
    private void componentPriceEdited(TableColumn.CellEditEvent<Component,Double> event){
        try {
            event.getRowValue().setComponentPrice(event.getNewValue());
            componentRegister.saveComponentRegisterToProgramFile();
        }
        catch(NumberFormatException | IOException e) {
            new AlertsToAdmin().priceAlert();
            event.getRowValue().setComponentPrice(event.getOldValue());
        }
        catch(InvalidPriceException e){
            new AlertsToAdmin().priceAlert();
            event.getRowValue().setComponentPrice(event.getOldValue());
        }

        componentView.refresh();
    }

}