package com.sample.fileManagement;

import com.sample.components.ComponentFormatter;
import com.sample.components.ComponentParser;
import com.sample.exceptions.component.InvalidComponentFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFromFileTxt<T> implements ReadFromFile {

    @Override
    public List<T> read(Path path) throws IOException {
        ObservableList<T> orderList = FXCollections.observableArrayList();
        BufferedReader reader = Files.newBufferedReader(Paths.get(String.valueOf(path)));
        String lineOne = reader.readLine();
        if (lineOne.equals("Component_ID" + ComponentFormatter.DELIMITER + "Component_Type" + ComponentFormatter.DELIMITER + "Component_Name" +
                ComponentFormatter.DELIMITER + "Component_Description" + ComponentFormatter.DELIMITER + "Component_Price")) {
            String components;
            while ((components = reader.readLine()) != null) {
                orderList.add((T) ComponentParser.parseComponent(components));
            }

            return orderList;
        }
        else {
            throw new InvalidComponentFormatException();
        }
    }
}
