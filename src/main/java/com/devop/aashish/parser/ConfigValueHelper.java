package com.devop.aashish.parser;

import com.devop.aashish.model.ComponentType;

import java.util.Map;
import java.util.Set;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * This utility class is  used to get the name of various application variables.
 * </p>
 */

public class ConfigValueHelper {

    static String getAppName() {
        return GeneratorConfig.appName;
    }

    static String getAppOutputDirectory() {
        return GeneratorConfig.appOutputDirectory;
    }

    public static String getApplicationId() {
        return GeneratorConfig.applicationId;
    }

    public static String getAppDatabaseName() {
        return GeneratorConfig.appDBName;
    }

    public static Map<String, ComponentType> getAppComponents() {
        return GeneratorConfig.appComponents;
    }

    public static Set<String> getAdapterComponents() {
        return GeneratorConfig.adapterComponents;
    }

    public static String getGeneratorFilePath() {
        return GeneratorConfig.generatorFilePath;
    }

    public static String getRootDirectory() {
        return DirectoryConfig.rootDirectory;
    }

    public static String getMainDirectory() {
        return DirectoryConfig.mainDirectory;
    }

    public static String getMainJavaDirectory() {
        return DirectoryConfig.mainJavaDirectory;
    }

    public static String getTestJavaDirectory() {
        return DirectoryConfig.testJavaDirectory;
    }

    public static String getAndroidTestJavaDirectory() {
        return DirectoryConfig.androidTestJavaDirectory;
    }

    public static String getLauncher() {
        return GeneratorConfig.launcher;
    }
}
