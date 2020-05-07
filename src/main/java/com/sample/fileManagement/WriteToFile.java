package com.sample.fileManagement;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface WriteToFile {

    public abstract <T> void writeFile(List<T> objects, Path path) throws IOException;
}