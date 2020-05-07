package com.sample.fileManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFromFileJobj<T> implements ReadFromFile {

    @Override
    public List<T> read(Path path) throws IOException, ClassNotFoundException {
        ObservableList<T> list = FXCollections.observableArrayList();

        try (InputStream in = Files.newInputStream(path);
             ObjectInputStream oin = new ObjectInputStream(in)) {

            List<T> inList = (List<T>) oin.readObject();
            list.addAll(inList);
            return list;
        }
    }

}
