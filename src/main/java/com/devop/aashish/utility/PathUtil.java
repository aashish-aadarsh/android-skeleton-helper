package com.devop.aashish.utility;


import com.devop.aashish.constant.ApplicationConstant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/4/2019
 * <p>
 * This utility class is  used to get the various path directory structure of android app
 */

public class PathUtil {

    public static String rootAppDir(String appName, String appOutputDirectory) {
        return appOutputDirectory + File.separator + appName;
    }

    public static String subDirectory(String prevPath, String newDirectoryName) {
        return prevPath + File.separator + newDirectoryName;
    }

    public static String packageFolder(String folderName, String applicationId) {
        return folderName + File.separator + ApplicationConstant.
                DirectoryConstant.DIR_JAVA + File.separator + getPathFromPackageName(applicationId);
    }

    private static String getPathFromPackageName(String packageName) {
        return packageName.replace(".", File.separator);
    }

    public static String getPackageNameFromPath(String packageName) {
        return packageName.replace("/", ".");
    }

    public static List<String> androidPackages(String mainJavaDirectory) {
        List<String> list = new ArrayList<>();
        list.add(ApplicationConstant.PackageConstant.CORE);
        list.add(ApplicationConstant.PackageConstant.UTILITY);
        list.add(ApplicationConstant.PackageConstant.VIEW_COMMON);
        list.add(ApplicationConstant.PackageConstant.VIEW_SPLASH);
        list.add(ApplicationConstant.PackageConstant.VO);

        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_DB_CONVERTER);
        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_DB_DAO);
        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_DB_ENTITY);
        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_DB_HELPER);

        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_NETWORK_RETROFIT);

        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_REPOSITORY);
        list.add(ApplicationConstant.PackageConstant.DATA_SOURCE_REPOSITORY_IMPL);

        return list.stream().map(s -> mainJavaDirectory + File.separator + s).collect(Collectors.toList());
    }

    public static String getFilePathFromPackage(String packageName, String mainJavaDirectory) {
        return mainJavaDirectory + File.separator + packageName;
    }
}

