package com.devop.aashish.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHelper {

    @SuppressWarnings("UnusedReturnValue")
    public static boolean createDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        return dir.exists() || dir.mkdirs();
    }

    public static void createDirectories(String... directories) {
        for (String directory : directories) {
            createDirectory(directory);
        }
    }

    public static void createDirectories(List<String> directories) {
        for (String directory : directories) {
            createDirectory(directory);
        }
    }

    public static void copyResources(String targetDirectory, String resourceDirectoryName) throws IOException {
        ResourceReader resourceReader = new ResourceReader();
        resourceReader.getStaticResourceDirectory(targetDirectory, resourceDirectoryName);
    }
}
