package com.sample.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Component Filter Class to be able to add list of filter choices to a ChoiceBox in the AdminView.
 * Default value is included to be able to set this value to the ChoiceBox default.
 */

public class ComponentFilter {
    public ObservableList<String> listOfFilterChoices = FXCollections.observableArrayList();

    public ComponentFilter(){
        listOfFilterChoices.add("Search for...");
        listOfFilterChoices.add("Item ID");
        listOfFilterChoices.add("Item type");
        listOfFilterChoices.add("Item name");
        listOfFilterChoices.add("Item description");
        listOfFilterChoices.add("Item price");
    }
}
