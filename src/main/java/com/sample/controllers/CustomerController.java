package com.sample.controllers;

import com.sample.App;
import com.sample.alerts.AlertsToCustomer;
import com.sample.components.Component;
import com.sample.components.ComponentCustomerCart;
import com.sample.components.ComponentRegister;
import com.sample.converters.DoubleStringConverter;
import com.sample.exceptions.component.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Controller for the view of customer users.
 */

public class CustomerController implements Initializable {

    private ComponentRegister componentRegister = new ComponentRegister();
    private ComponentCustomerCart customerCart = new ComponentCustomerCart();
    private String chcProcessorTypePattern, chcGraphicsCardTypePattern, chcMotherboardTypePattern, chcRAMTypePattern, chcPowerSupplyTypePattern,
            chcHddSsdTypePattern, chcCabinetTypePattern, chcMonitorTypePattern, chcMouseTypePattern, chcKeyboardTypePattern;

    @FXML
    private TableView cartView;

    @FXML
    private TableColumn cartTypeColumn, cartNameColumn, cartDescriptionColumn, cartPriceColumn;

    @FXML
    private Label txtTotalPrice, txtTotalOrderPrice, lblCartHeader, lblChooseComponents, lblTotalOrderPrice, lblOrderPrice,
    lblProcessor, lblMotherboard, lblMemory, lblGraphicsCard, lblPowersupply, lblHDD, lblCabinet, lblMonitor, lblMouse, lblKeyboard, lblProgramHeader;

    @FXML
    private ComboBox chcProcessor, chcGraphicsCard, chcMotherboard, chcRAM, chcPowerSupply, chcHddSsd, chcCabinet, chcMonitor, chcMouse,chcKeyboard;

    @FXML
    private Button btnDeleteFromCart, btnConfirmOrder, btnAddToCart, btnNewOrder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblProgramHeader.setText("Customize your new computer");
        lblCartHeader.setText("YOUR SHOPPING CART");
        btnConfirmOrder.setText("CONFIRM ORDER");
        btnNewOrder.setVisible(false);
        lblOrderPrice.setVisible(false);
        lblTotalOrderPrice.setVisible(false);

        try {
            componentRegister.openRegisterFromProgramFile();
        } catch (IOException | ClassNotFoundException e) {
            new AlertsToCustomer().componentListAlert();
        }
        chcProcessorTypePattern = "Processor";
        chcGraphicsCardTypePattern = "Graphics card";
        chcMotherboardTypePattern = "Motherboard";
        chcRAMTypePattern = "RAM";
        chcPowerSupplyTypePattern = "Power supply";
        chcHddSsdTypePattern = "HDD/SSD";
        chcCabinetTypePattern = "Cabinet";
        chcMonitorTypePattern = "Monitor";
        chcMouseTypePattern = "Mouse";
        chcKeyboardTypePattern = "Keyboard";

        chcProcessor.setItems((ObservableList) componentRegister.separateRegisterByType(chcProcessorTypePattern));
        chcGraphicsCard.setItems((ObservableList) componentRegister.separateRegisterByType(chcGraphicsCardTypePattern));
        chcMotherboard.setItems((ObservableList) componentRegister.separateRegisterByType(chcMotherboardTypePattern));
        chcRAM.setItems((ObservableList) componentRegister.separateRegisterByType(chcRAMTypePattern));
        chcPowerSupply.setItems((ObservableList) componentRegister.separateRegisterByType(chcPowerSupplyTypePattern));
        chcHddSsd.setItems((ObservableList) componentRegister.separateRegisterByType(chcHddSsdTypePattern));
        chcCabinet.setItems((ObservableList) componentRegister.separateRegisterByType(chcCabinetTypePattern));
        chcMonitor.setItems((ObservableList) componentRegister.separateRegisterByType(chcMonitorTypePattern));
        chcMouse.setItems((ObservableList) componentRegister.separateRegisterByType(chcMouseTypePattern));
        chcKeyboard.setItems((ObservableList) componentRegister.separateRegisterByType(chcKeyboardTypePattern));

        cartTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cartNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cartDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cartPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    @FXML
    private void addComponentsToCart(){
        customerCart.addComponentToOrder(
                (Component)chcProcessor.getSelectionModel().getSelectedItem(),
                (Component)chcGraphicsCard.getSelectionModel().getSelectedItem(),
                (Component) chcMotherboard.getSelectionModel().getSelectedItem(),
                (Component) chcRAM.getSelectionModel().getSelectedItem(),
                (Component) chcPowerSupply.getSelectionModel().getSelectedItem(),
                (Component) chcHddSsd.getSelectionModel().getSelectedItem(),
                (Component) chcCabinet.getSelectionModel().getSelectedItem(),
                (Component) chcMonitor.getSelectionModel().getSelectedItem(),
                (Component) chcMouse.getSelectionModel().getSelectedItem(),
                (Component) chcKeyboard.getSelectionModel().getSelectedItem()
        );

        customerCart.removeEmptyFieldsFromOrder();
        customerCart.attachToTableView(cartView);

        txtTotalPrice.setText(customerCart.getTotalOrderPrice());
        chcProcessor.getSelectionModel().clearSelection();
        chcGraphicsCard.getSelectionModel().clearSelection();
        chcMotherboard.getSelectionModel().clearSelection();
        chcRAM.getSelectionModel().clearSelection();
        chcPowerSupply.getSelectionModel().clearSelection();
        chcHddSsd.getSelectionModel().clearSelection();
        chcCabinet.getSelectionModel().clearSelection();
        chcMonitor.getSelectionModel().clearSelection();
        chcMouse.getSelectionModel().clearSelection();
        chcKeyboard.getSelectionModel().clearSelection();
    }

    @FXML
    private void confirmOrder() throws IOException {
        customerCart.saveCustomerOrderToFile();
    }

    @FXML
    private void changeToLoginView() {
        try {
            if (btnNewOrder.isVisible()) {
                App.changeView("loginview.fxml");
            } else {
                if (!txtTotalPrice.getText().equals("") && customerCart.totalOrderPriceConverter() != 0.0) {
                    new AlertsToCustomer().logOutAlert();
                } else {
                    App.changeView("loginview.fxml");
                }
            }
        }
        catch(IOException e){
            new AlertsToCustomer().loadFailAlert();
        }
    }

    @FXML
    private void deleteComponentFromCart() {
        Component componentToDelete = (Component) cartView.getSelectionModel().getSelectedItem();
        customerCart.deleteComponentFromOrder(componentToDelete);
        customerCart.attachToTableView(cartView);
        txtTotalPrice.setText(customerCart.getTotalOrderPrice());
    }

    @FXML
    private void showOrderInTV() throws ParseException {
        if(!txtTotalPrice.getText().equals("") && customerCart.totalOrderPriceConverter() != 0.0) {
            if (new AlertsToCustomer().cartIsNotEmptyAlert()){
                try {
                    if (customerCart.openCustomerOrderFromFile()) {
                        customerCart.attachToTableView(cartView);
                        setCartView();
                        lblOrderPrice.setText(customerCart.getTotalOrderPrice());

                    }
                } catch (IOException | InvalidTypeException | InvalidNameException | InvalidDescriptionException | InvalidPriceException | InvalidProductNrException e) {
                    new AlertsToCustomer().wrongOrderFileFormatAlert();
                }
            }
        }
        else {
            try {
                if (customerCart.openCustomerOrderFromFile()) {
                    customerCart.attachToTableView(cartView);
                    setCartView();
                    lblOrderPrice.setText(customerCart.getTotalOrderPrice());

                }
            } catch (IOException | InvalidTypeException | InvalidNameException | InvalidDescriptionException | InvalidPriceException | InvalidProductNrException e) {
                new AlertsToCustomer().wrongOrderFileFormatAlert();
            }
        }
    }

    @FXML
    private void setCartView(){
        btnNewOrder.setVisible(true);
        lblTotalOrderPrice.setVisible(true);
        lblOrderPrice.setVisible(true);
        lblProgramHeader.setText("Order History");
        lblCartHeader.setText("YOUR ORDER");
        lblChooseComponents.setVisible(false);
        btnAddToCart.setVisible(false);
        chcProcessor.setVisible(false);
        chcMotherboard.setVisible(false);
        chcRAM.setVisible(false);
        chcGraphicsCard.setVisible(false);
        chcPowerSupply.setVisible(false);
        chcHddSsd.setVisible(false);
        chcCabinet.setVisible(false);
        chcMonitor.setVisible(false);
        chcMouse.setVisible(false);
        chcKeyboard.setVisible(false);
        txtTotalPrice.setVisible(false);
        txtTotalOrderPrice.setVisible(false);
        btnDeleteFromCart.setVisible(false);
        lblProcessor.setVisible(false);
        lblMotherboard.setVisible(false);
        lblMemory.setVisible(false);
        lblGraphicsCard.setVisible(false);
        lblPowersupply.setVisible(false);
        lblHDD.setVisible(false);
        lblCabinet.setVisible(false);
        lblMonitor.setVisible(false);
        lblMouse.setVisible(false);
        lblKeyboard.setVisible(false);

    }

    @FXML
    private void newOrder(){
        try {
            App.changeView("customerview.fxml");
        }
        catch(IOException e){
            new AlertsToCustomer().loadFailAlert();
        }
    }
}
