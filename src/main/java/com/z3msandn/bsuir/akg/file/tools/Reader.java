package com.z3msandn.bsuir.akg.file.tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Logger;

public class Reader {
    private static final Logger LOGGER = Logger.getLogger(Reader.class.getName());

    public String read(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource != null) {
                String url = resource.getFile();
                File file = new File(url);
                return new String(Files.readAllBytes(file.toPath()));
            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return null;
    }
}
