package com.sample.components;

import com.sample.exceptions.component.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Component2 {

    // private static final long serialVersionUID = 1L;

    private transient SimpleStringProperty componentID;
    private transient SimpleStringProperty componentType;
    private transient SimpleStringProperty componentName;
    private transient SimpleDoubleProperty componentPrice;
    private transient SimpleStringProperty componentDescription;

    public Component2(String componentID, String componentType, String componentName, double componentPrice){
        setComponentID(componentID);
        setComponentName(componentName);
        setComponentType(componentType);
        setComponentPrice(componentPrice);
    }

    public void setComponentID(String productNr){
        if(ComponentValidation.checkComponentId(productNr)) {
            this.componentID = new SimpleStringProperty(productNr);
        }
        else {
            throw new InvalidProductNrException("The product number is already existing.");
        }
    }

    public String getComponentID(){
        return this.componentID.getValue();
    }

    public void setComponentType(String componentType){
        if(ComponentValidation.checkType(componentType)){
            this.componentType = new SimpleStringProperty(componentType);
        }
        else {
            throw new InvalidTypeException("The component type is invalid.");
        }
    }

    public String getComponentType(){
        return this.componentType.getValue();
    }

    public void setComponentName(String componentName){
        if (ComponentValidation.checkComponentName(componentName)){
            this.componentName = new SimpleStringProperty(componentName);
        }
        else {
            throw new InvalidNameException("The component name is invalid. Must be between 5 and 30 characters.");
        }
    }

    public String getComponentName(){
        return this.componentName.getValue();
    }


    public void setComponentPrice(double componentPrice){
        if (ComponentValidation.checkComponentPrice(componentPrice)){
            this.componentPrice = new SimpleDoubleProperty(componentPrice);
        }
        else {
            throw new InvalidPriceException("The price is invalid.");
        }
    }

    public double getComponentPrice(){
        return this.componentPrice.getValue();
    }

    abstract void setComponentDescription(String componentDescription);

    abstract String getComponentDescription();

    @Override
    public String toString(){
        return getComponentType() + " " + getComponentName() + " " + getComponentDescription() + " " + getComponentPrice() + " NOK";
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(componentID.getValue());
        s.writeUTF(componentType.getValue());
        s.writeUTF(componentName.getValue());
        s.writeUTF(componentDescription.getValue());
        s.writeDouble(componentPrice.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException {
        String componentID = s.readUTF();
        String componentType = s.readUTF();
        String componentName = s.readUTF();
        String componentDescription = s.readUTF();
        double componentPrice = s.readDouble();

        this.componentID = new SimpleStringProperty(componentID);
        this.componentType = new SimpleStringProperty(componentType);
        this.componentName = new SimpleStringProperty(componentName);
        this.componentDescription = new SimpleStringProperty(componentDescription);
        this.componentPrice = new SimpleDoubleProperty(componentPrice);

        setComponentID(componentID);
        setComponentType(componentType);
        setComponentName(componentName);
        setComponentDescription(componentDescription);
        setComponentPrice(componentPrice);
    }
}
