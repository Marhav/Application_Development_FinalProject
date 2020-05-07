package com.sample.fileManagement;

import com.sample.components.Component;
import com.sample.components.ComponentFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteToFileTxt<T> implements WriteToFile {

    @Override
    public <T> void writeFile(List<T> objects, Path path) throws IOException {

        String formattedObjects = ComponentFormatter.formatComponentList((List<Component>) objects);
        Files.write(path, formattedObjects.getBytes());
    }
}
