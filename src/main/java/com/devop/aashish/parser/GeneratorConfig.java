package com.devop.aashish.parser;

import com.devop.aashish.constant.PropertyFileConstant;
import com.devop.aashish.model.ComponentType;
import com.devop.aashish.utility.ComponentParser;
import com.devop.aashish.utility.PropertyFileUtil;

import java.util.*;


/**
 * @author : Aashish Aadarsh
 * @connect : https://github.com/aashish-aadarsh
 * @createdOn : 1/5/2019
 *
 * <p>
 * This utility class is  used to initialize property file reading, parsing the key from file.
 * </p>
 */
public class GeneratorConfig {

    static String appName;
    static String appOutputDirectory;
    static String applicationId;
    static String generatorFilePath;
    static String appDBName;
    static Map<String, ComponentType> appComponents = new HashMap<>();
    static Set<String> adapterComponents = new HashSet<>();

    public GeneratorConfig(String generatorFilePath) {
        GeneratorConfig.generatorFilePath = generatorFilePath;
    }


    /**
     * Initialize the property reading
     */
    public void initInfo() {
        PropertyFileUtil propertyFileUtil = new PropertyFileUtil(generatorFilePath, PropertyFileConstant.GENERATOR_PROPERTIES);
        appName = propertyFileUtil.getProperty(PropertyFileConstant.APPLICATION_NAME);
        appOutputDirectory = propertyFileUtil.getProperty(PropertyFileConstant.APPLICATION_OUTPUT_DIRECTORY);
        applicationId = propertyFileUtil.getProperty(PropertyFileConstant.APPLICATION_ID);
        appDBName = propertyFileUtil.getProperty(PropertyFileConstant.APP_DB_NAME);
        parseAppComponents(propertyFileUtil);
    }

    /**
     * @param propertyFileUtil for parsing the app component for activity, fragment, adapters
     */
    private void parseAppComponents(PropertyFileUtil propertyFileUtil) {
        String appComponentInput = propertyFileUtil.getProperty(PropertyFileConstant.APP_COMPONENT);
        String appComponentAdapterInput = propertyFileUtil.getProperty(PropertyFileConstant.ADAPTER_FOR_COMPONENT);

        if (appComponentInput != null) {
            String[] components = appComponentInput.split(PropertyFileConstant.APP_COMPONENT_SEPARATOR);
            Arrays.stream(components).map(String::trim).forEach(component -> {
                if (component.startsWith(PropertyFileConstant.APP_COMPONENT_ACTIVITY))
                    appComponents.put(ComponentParser.parseComponentName(component), ComponentType.ACTIVITY);
                else if (component.startsWith(PropertyFileConstant.APP_COMPONENT_FRAGMENT)) {
                    appComponents.put(ComponentParser.parseComponentName(component), ComponentType.FRAGMENT);
                }
            });

        }

        if (appComponentAdapterInput != null) {
            String[] adapterComponent = appComponentAdapterInput.split(PropertyFileConstant.APP_COMPONENT_SEPARATOR);
            adapterComponents.addAll(Arrays.asList(adapterComponent));

        }

    }

}
