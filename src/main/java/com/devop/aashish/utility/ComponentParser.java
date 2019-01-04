package com.devop.aashish.utility;

import com.devop.aashish.constant.ApplicationConstant;

public class ComponentParser {

    public static String parseComponentName(String input) {
        return input.substring(1, input.length() - 1);
    }

    public static String getActivityLayoutName(String componentName) {
        return ApplicationConstant.NamingConstant.ACTIVITY_XML + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }

    public static String getFragmentLayoutName(String componentName) {
        return ApplicationConstant.NamingConstant.FRAGMENT_XML + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }


    public static String getActivityName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.ACTIVITY_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getFragmentName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.FRAGMENT_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getRepositoryName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.REPO_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getRepositoryImplName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.REPO_IMPL_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getViewModelName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.VIEW_MODEL_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getClassNameFromFileName(String fileName) {

        return String.valueOf(fileName.split("\\.")[0]);
    }

    public static String getBindingClassNameForActivity(String componentName) {
        return ApplicationConstant.NamingConstant.ACTIVITY_CLASS +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }

    public static String getBindingClassNameForFragment(String componentName) {
        return ApplicationConstant.NamingConstant.FRAGMENT_CLASS +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }

    public static String getEntityClass(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getDaoClass(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + "Dao" +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }


    public static String getItemLayoutName(String componentName) {
        return ApplicationConstant.NamingConstant.ITEM_XML + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }

    public static String getVOName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.VO +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getAdapterName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.ADAPTER_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    public static String getBindingClassNameForAdapter(String componentName) {
        return ApplicationConstant.NamingConstant.ITEM_CLASS +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }
}
