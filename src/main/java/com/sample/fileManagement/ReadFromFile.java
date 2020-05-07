package com.sample.fileManagement;

import com.sample.components.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReadFromFile {

    public abstract <T extends List<T>> T read(Path path) throws IOException, ClassNotFoundException;
}
