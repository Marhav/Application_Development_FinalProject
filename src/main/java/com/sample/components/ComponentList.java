package com.sample.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Component List Class to be able to add list of all components to a ChoiceBox in the AdminView.
 * Default value is included to be able to set this value to the ChoiceBox default.
 */

public class ComponentList {
    public ObservableList<String> listOfComponents = FXCollections.observableArrayList();

    public ComponentList(){
        listOfComponents.add("Choose component..");
        listOfComponents.add("Processor");
        listOfComponents.add("Motherboard");
        listOfComponents.add("RAM");
        listOfComponents.add("Graphics card");
        listOfComponents.add("Power supply");
        listOfComponents.add("HDD/SSD");
        listOfComponents.add("Cabinet");
        listOfComponents.add("Monitor");
        listOfComponents.add("Mouse");
        listOfComponents.add("Keyboard");
    }
}
