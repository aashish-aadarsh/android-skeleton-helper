package com.devop.aashish.parser;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.utility.PathUtil;

/**
 * @author : Aashish Aadarsh
 * @connect : https://github.com/aashish-aadarsh
 * @createdOn : 1/5/2019
 *
 * <p>
 * This utility class is  used to get the name of various android directory path.
 * </p>
 */
public class DirectoryConfig {

    static String rootDirectory;
    static String mainDirectory;
    static String mainJavaDirectory;
    static String testJavaDirectory;
    static String androidTestJavaDirectory;

    public DirectoryConfig() {
    }

    /**
     * Initialize the property reading
     */
    public void initInfo() {
        rootDirectory = PathUtil.rootAppDir(ConfigValueHelper.getAppName(),
                ConfigValueHelper.getAppOutputDirectory());
        String appDirectory = PathUtil.subDirectory(rootDirectory, ApplicationConstant.DirectoryConstant.DIR_APP);
        String srcDirectory = PathUtil.subDirectory(appDirectory, ApplicationConstant.DirectoryConstant.DIR_SRC);
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
