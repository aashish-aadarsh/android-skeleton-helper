package com.devop.aashish.parser;

import com.devop.aashish.model.ComponentType;

import java.util.Map;
import java.util.Set;

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

    public static String getAppDirectory() {
        return DirectoryConfig.appDirectory;
    }

    public static String getSrcDirectory() {
        return DirectoryConfig.srcDirectory;
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


}
