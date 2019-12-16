package com.devop.aashish.utility;

import com.devop.aashish.constant.ApplicationConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * This utility class is  used to get the name of various android component.
 * </p>
 */
public class ComponentParser {

    public static String parseComponentName(String input) {
        return input.substring(1, input.length() - 1);
    }

    /**
     * @param componentName for which activity layout name is to be returned
     * @return the name of activity xml file
     *
     * <code>activity_login.xml</code>
     */
    public static String getActivityLayoutName(String componentName) {
        return ApplicationConstant.NamingConstant.ACTIVITY_XML + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }


    /**
     * @param componentName for which fragment layout name is to be returned
     * @return the name of fragment xml file
     *
     * <code>fragment_login.xml</code>
     */
    public static String getFragmentLayoutName(String componentName) {
        return ApplicationConstant.NamingConstant.FRAGMENT_XML + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }

    /**
     * @param componentName for which fragment layout name is to be returned
     * @return the name of fragment xml file
     *
     * <code>fragment_add_login.xml</code>
     */
    public static String getFragmentLayoutNameAdd(String componentName) {
        return ApplicationConstant.NamingConstant.FRAGMENT_XML + ApplicationConstant.NamingConstant.UNDERSCORE
                + ApplicationConstant.NamingConstant.ADD.toLowerCase() + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }

    /**
     * @param componentName for which Activity class name is to be returned
     * @return the name of activity class file
     *
     * <code>LoginActivity.kt</code>
     */
    public static String getActivityName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.ACTIVITY_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which Fragment class name is to be returned
     * @return the name of fragment class file
     *
     * <code>LoginFragment.kt</code>
     */
    public static String getFragmentName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.FRAGMENT_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which Fragment class name is to be returned
     * @return the name of fragment class file
     *
     * <code>AddLoginFragment.kt</code>
     */
    public static String getFragmentNameAdd(String componentName) {
        return ApplicationConstant.NamingConstant.ADD + String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.FRAGMENT_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which Repository Interface name is to be returned
     * @return the name of RepositoryInterface class file
     *
     * <code>LoginRepository.kt</code>
     */
    public static String getRepositoryName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.REPO_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which Repository Impl class name is to be returned
     * @return the name of RepositoryImpl class file
     *
     * <code>LoginRepositoryImpl.kt</code>
     */
    public static String getRepositoryImplName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.REPO_IMPL_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which ViewModel class name is to be returned
     * @return the name of ViewModel class file
     *
     * <code>LoginViewModel.kt</code>
     */
    public static String getViewModelName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.VIEW_MODEL_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }


    /**
     * @param componentName for which ActivityBinding class name is to be returned
     * @return the name of ActivityBinding class file
     *
     * <code>ActivityLoginBinding</code>
     */
    public static String getBindingClassNameForActivity(String componentName) {
        return ApplicationConstant.NamingConstant.ACTIVITY_CLASS +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }

    /**
     * @param componentName for which FragmentBinding class name is to be returned
     * @return the name of FragmentBinding class file
     *
     * <code>FragmentLoginBinding</code>
     */

    public static String getBindingClassNameForFragment(String componentName) {
        return ApplicationConstant.NamingConstant.FRAGMENT_CLASS +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }

    /**
     * @param componentName for which FragmentBinding class name is to be returned
     * @return the name of FragmentBinding class file
     *
     * <code>FragmentLoginBinding</code>
     */

    public static String getBindingClassNameForFragmentAdd(String componentName) {
        return ApplicationConstant.NamingConstant.FRAGMENT_CLASS +
                ApplicationConstant.NamingConstant.ADD +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }

    /**
     * @param componentName for which Entity class name is to be returned
     * @return the name of Entity class file
     *
     * <code>Login.kt</code>
     */
    public static String getEntityClass(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which Dao class name is to be returned
     * @return the name of Dao class file
     *
     * <code>LoginDao.kt</code>
     */
    public static String getDaoClass(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + "Dao" +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which item xml name is to be returned
     * @return the name of item xml file
     *
     * <code>item_login.xml</code>
     */
    public static String getItemLayoutName(String componentName) {
        return ApplicationConstant.NamingConstant.ITEM_XML + ApplicationConstant.NamingConstant.UNDERSCORE +
                componentName.toLowerCase() +
                ApplicationConstant.NamingConstant.EXTENSION_XML;
    }

    /**
     * @param componentName for which item vo name is to be returned
     * @return the name of item vo file
     *
     * <code>LoginVO.kt</code>
     */
    public static String getVOName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.VO +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which  adapter class is to be returned
     * @return the name of item adapter file
     *
     * <code>LoginAdapter.kt</code>
     */
    public static String getAdapterName(String componentName) {
        return String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) +
                ApplicationConstant.NamingConstant.ADAPTER_CLASS +
                ApplicationConstant.NamingConstant.EXTENSION_KOTLIN;
    }

    /**
     * @param componentName for which item binding class is to be returned
     * @return the name of item adapter binding
     *
     * <code>ItemLoginBinding</code>
     */
    public static String getBindingClassNameForAdapter(String componentName) {
        return ApplicationConstant.NamingConstant.ITEM_CLASS +
                String.valueOf(componentName.charAt(0)).toUpperCase() +
                componentName.substring(1) + ApplicationConstant.NamingConstant.BINDING;
    }

    /**
     * @param fileName for which class name is to be parsed
     * @return class name
     *
     * <code>LoginViewMode..kt translates to LoginViewModel</code>
     */
    public static String getClassNameFromFileName(String fileName) {

        return String.valueOf(fileName.split("\\.")[0]);
    }

    /**
     * @param resourceName Name of resource
     * @return The xml name for a resource file.
     *
     * <eg>
     * if a resource file name is UserDemographicAddress,
     * the table name generated would be user_demographic_address
     * </eg>
     */
    public static String getPascalCaseName(String resourceName) {
        if (null == resourceName || resourceName.trim().isEmpty()) {
            return "";
        }
        String lowerCasedResource = String.valueOf(resourceName.charAt(0)).toLowerCase()
                + resourceName.substring(1);
        Matcher m = Pattern.compile("(?<=[a-z])[A-Z]").matcher(lowerCasedResource);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "_" + m.group().toLowerCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
