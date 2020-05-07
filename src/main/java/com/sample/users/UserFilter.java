package com.sample.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserFilter {
    public ObservableList<String> listOfFilterChoices = FXCollections.observableArrayList();

    public UserFilter() {
        listOfFilterChoices.add("All categories");
        listOfFilterChoices.add("Mail");
        listOfFilterChoices.add("First name");
        listOfFilterChoices.add("Last name");
        listOfFilterChoices.add("Phone number");
    }
}
