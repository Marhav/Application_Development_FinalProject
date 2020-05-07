package com.sample.components;

import com.sample.App;
import com.sample.alerts.AlertsToCustomer;
import com.sample.fileManagement.ReadFromFileTxt;
import com.sample.fileManagement.WriteToFileTxt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Class representing the customer cart when customer places new order.
 */

public class ComponentCustomerCart {

    private transient ObservableList<Component> customerOrder = FXCollections.observableArrayList();

    public String getTotalOrderPrice(){
        double totalPrice = 0;

        for (int i = 0; i < customerOrder.size(); i++){
            if (customerOrder.get(i) != null) {
                totalPrice += customerOrder.get(i).getComponentPrice();
            }
        }

        NumberFormat priceFormatter = NumberFormat.getCurrencyInstance();
        String formattedPrice = priceFormatter.format(totalPrice);

        return formattedPrice;
    }

    public double totalOrderPriceConverter() {
        String price = getTotalOrderPrice();
        double priceConverted;
        try {
            priceConverted = NumberFormat.getCurrencyInstance().parse(price).doubleValue();
        }
        catch(ParseException e){
            priceConverted = 0.0;
        }

        return priceConverted;
    }

    public void removeEmptyFieldsFromOrder(){
        while (customerOrder.remove(null));
    }

    public void attachToTableView(TableView tableview){
        tableview.setItems(customerOrder);
    }

    public void addComponentToOrder(Component... newComponent) {
        for (int i = 0; i < newComponent.length; i++){
            customerOrder.add(newComponent[i]);
        }
    }

    public void deleteComponentFromOrder(Component newComponent){
        customerOrder.remove(newComponent);
    }

    public void saveCustomerOrderToFile() throws IOException {
            WriteToFileTxt<Component> orderSaver = new WriteToFileTxt();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./src/main/resources/OrderHistoryFiles"));
            fileChooser.setInitialFileName("Order_" + LocalDate.now());
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Txt files", "*.txt"));
            File selectedFile = fileChooser.showSaveDialog(new Stage());
            if(selectedFile != null) {
                orderSaver.writeFile(customerOrder, selectedFile.toPath());
                new AlertsToCustomer().confirmedOrderAlert();
                App.changeView("customerview.fxml");
            } else  {
                new AlertsToCustomer().orderNotConfirmedAlert();
            }
    }

    public boolean openCustomerOrderFromFile() throws IOException {
        ReadFromFileTxt<Component> orderOpener = new ReadFromFileTxt();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./src/main/resources/OrderHistoryFiles"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Txt files","*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile != null) {
            customerOrder = (ObservableList<Component>) orderOpener.read(selectedFile.toPath());
            return true;
        }
        else {
            new AlertsToCustomer().noSelectedFileAlert();
            return false;
        }
    }
}
