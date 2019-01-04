package com.devop.aashish.generator;

import com.devop.aashish.constant.PropertyFileConstant;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.utility.FileHelper;
import com.devop.aashish.utility.PathUtil;

import java.io.IOException;

public class DirectoryGeneration {


    public static void createDirectorySkeleton() throws IOException {

        String rootDirectory = ConfigValueHelper.getRootDirectory();

        FileHelper.createDirectory(rootDirectory);

        String mainJavaDirectory = ConfigValueHelper.getMainJavaDirectory();
        String testJavaDirectory = ConfigValueHelper.getTestJavaDirectory();
        String androidTestJavaDirectory = ConfigValueHelper.getAndroidTestJavaDirectory();

        FileHelper.copyResources(rootDirectory, PropertyFileConstant.STATIC_RESOURCE);

        FileHelper.createDirectories(rootDirectory,
                mainJavaDirectory,
                testJavaDirectory,
                androidTestJavaDirectory);

        FileHelper.createDirectories(PathUtil.androidPackages(mainJavaDirectory));
    }

}
