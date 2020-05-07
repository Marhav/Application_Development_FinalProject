package com.sample.fileManagement;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WriteToFileJobj<T> implements WriteToFile {

    @Override
    public <T> void writeFile(List<T> objects, Path path) throws IOException {

        try (OutputStream os = Files.newOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(os)) {
            ArrayList<T> list = new ArrayList<>();
            list.addAll(objects);
            out.writeObject(list);
        }
    }
}
