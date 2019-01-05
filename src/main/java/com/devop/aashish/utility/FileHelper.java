package com.devop.aashish.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : Aashish Aadarsh
 * @connect : https://github.com/aashish-aadarsh
 * @createdOn : 1/4/2019
 * <p>
 * This is a utility class which is used to perform file related operation.
 * </p>
 */

public class FileHelper {

    @SuppressWarnings("UnusedReturnValue")
    /**
     *  Create directory at given path
     */
    public static boolean createDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        return dir.exists() || dir.mkdirs();
    }

    /**
     * @param directories create directories of given paths
     */
    public static void createDirectories(String... directories) {
        for (String directory : directories) {
            createDirectory(directory);
        }
    }

    /**
     *
     * @param directories create directories of given paths
     */
    public static void createDirectories(List<String> directories) {
        for (String directory : directories) {
            createDirectory(directory);
        }
    }

    /**
     *
     * @param targetDirectory where files has to be copied
     * @param resourceDirectoryName source directory of files
     * @throws IOException if any system error occurred while copying resource
     */
    public static void copyResources(String targetDirectory, String resourceDirectoryName) throws IOException {
        ResourceReader resourceReader = new ResourceReader();
        resourceReader.getStaticResourceDirectory(targetDirectory, resourceDirectoryName);
    }
}
