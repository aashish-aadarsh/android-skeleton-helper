package com.devop.aashish.parser;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.utility.PathUtil;

public class DirectoryConfig {

    static String rootDirectory;
    static String appDirectory;
    static String srcDirectory;
    static String mainDirectory;
    static String mainJavaDirectory;
    static String testJavaDirectory;
    static String androidTestJavaDirectory;

    public DirectoryConfig() {
    }

    public void initInfo() {
        rootDirectory = PathUtil.rootAppDir(ConfigValueHelper.getAppName(),
                ConfigValueHelper.getAppOutputDirectory());
        appDirectory = PathUtil.subDirectory(rootDirectory, ApplicationConstant.DirectoryConstant.DIR_APP);
        srcDirectory = PathUtil.subDirectory(appDirectory, ApplicationConstant.DirectoryConstant.DIR_SRC);
        mainDirectory = PathUtil.subDirectory(srcDirectory, ApplicationConstant.DirectoryConstant.DIR_MAIN);
        mainJavaDirectory = PathUtil.subDirectory(srcDirectory,
                PathUtil.packageFolder(ApplicationConstant.DirectoryConstant.DIR_MAIN,
                        ConfigValueHelper.getApplicationId()));
        testJavaDirectory = PathUtil.subDirectory(srcDirectory,
                PathUtil.packageFolder(ApplicationConstant.DirectoryConstant.DIR_TEST,
                        ConfigValueHelper.getApplicationId()));
        androidTestJavaDirectory = PathUtil.subDirectory(srcDirectory,
                PathUtil.packageFolder(ApplicationConstant.DirectoryConstant.DIR_ANDROID_TEST,
                        ConfigValueHelper.getApplicationId()));
    }


}
