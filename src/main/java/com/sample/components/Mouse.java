package com.sample.components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Mouse extends Component implements Serializable {
    private transient SimpleDoubleProperty width;
    private transient SimpleDoubleProperty height;
    private transient SimpleDoubleProperty weigth;
    private transient SimpleDoubleProperty dpi;
    private transient SimpleStringProperty color;
    private transient SimpleBooleanProperty wireless;

    public Mouse(String componentID, String componentType, String componentName, String componentDescription, double componentPrice, double width, double height, double weight, double dpi, String color, boolean wireless) {
        super(componentID, componentType, componentName, componentDescription, componentPrice);
        setWidth(width);
        setHeight(height);
        setWeigth(weight);
        setDpi(dpi);
        setColor(color);
        setWireless(wireless);
    }

    public void setWidth(double width) {
        this.width = new SimpleDoubleProperty(width);
    }

    public double getWidth() {
        return this.width.getValue();
    }

    public void setHeight(double height) {
        this.height = new SimpleDoubleProperty(height);
    }

    public double getHeight() {
        return this.height.doubleValue();
    }

    public void setWeigth(double weigth) {
        this.weigth = new SimpleDoubleProperty(weigth);
    }

    public double getWeight() {
        return this.weigth.getValue();
    }

    public void setDpi(double dpi) {
        this.dpi = new SimpleDoubleProperty(dpi);
    }

    public double getDpi() {
        return this.dpi.getValue();
    }

    public void setColor(String color) {
        this.color = new SimpleStringProperty(color);
    }

    public String getColor() {
        return this.color.getValue();
    }

    public void setWireless(boolean wireless) {
        this.wireless = new SimpleBooleanProperty(wireless);
    }

    public boolean getWireless() {
        return this.wireless.getValue();
    }

    public ObservableList<String> listOfMouseAttributes(){
        ObservableList<String> mouseAttributes = FXCollections.observableArrayList();
        mouseAttributes.add("Width");
        mouseAttributes.add("Height");
        mouseAttributes.add("Weight");
        mouseAttributes.add("DPI");
        mouseAttributes.add("Color");
        mouseAttributes.add("Wireless");
        return mouseAttributes;
    }

    public String writeDetails(){
        String ut = "Width: " + getWidth() + "\n" +
                "Height: " + getHeight() + "\n" +
                "Weight: " + getWeight() + "\n" +
                "DPI: " + getDpi() + "\n" +
                "Color: " + getColor() + "\n" +
                "Wireless: " + getWireless();

        return ut;
    }
}
