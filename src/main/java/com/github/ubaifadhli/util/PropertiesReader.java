package com.github.ubaifadhli.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    Properties properties;

    public PropertiesReader(String fileName) {
        InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);

        properties = new Properties();
        try {
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyAsString(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public static String getFolderAbsolutePath(String folderRelativePath) {
        return new File(folderRelativePath).getAbsolutePath();
    }
}
