package com.devop.aashish.generator;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.ComponentParser;
import com.devop.aashish.utility.FileHelper;
import com.devop.aashish.utility.PathUtil;
import com.devop.aashish.utility.RandomDataGenerator;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

class FragmentGenerator {

    public static void generateFragment(String componentName, VelocityConfig config) {
        generateFragmentXML(componentName, config);
        generateFragmentClass(componentName, config);
    }

    private static void generateFragmentXML(String componentName, VelocityConfig config) {

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DIRECTORY_LAYOUT,
                ConfigValueHelper.getMainDirectory());

        String fragmentName = ComponentParser.getFragmentLayoutName(componentName);
        String fragmentNameAdd = ComponentParser.getFragmentLayoutNameAdd(componentName);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);
        boolean generateRV = ConfigValueHelper.getAdapterComponents().contains(componentName) &&
                DatabaseGenerator.entityListName.contains(componentName);
        paramMap.put(TemplateFileConstant.KEY_GENERATE_RV_FRAGMENT, generateRV);


        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        config.writeFile(
                packageDirectory +
                        File.separator + fragmentName
                , TemplateFileConstant.FRAGMENT_LOCATION, velocityContext);

        if (generateRV) {
            //todo... read from excel and parse
            paramMap.put("uiComponents", RandomDataGenerator.getRandomData());
            velocityContext = config.getVelocityContextObject(paramMap);
            config.writeFile(
                    packageDirectory +
                            File.separator + fragmentNameAdd
                    , TemplateFileConstant.FRAGMENT_ADD_LOCATION, velocityContext);
        }
    }

    private static void generateFragmentClass(String componentName, VelocityConfig config) {
        String layoutName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getFragmentLayoutName(ComponentParser.getPascalCaseName(componentName)));

        String fileName = ComponentParser.getFragmentName(componentName);

        String className = ComponentParser.getClassNameFromFileName(fileName);

        String viewModelClassName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getViewModelName(componentName));

        String bindingClassName = ComponentParser.getBindingClassNameForFragment(componentName);

        String adapterClassName =
                ComponentParser.getClassNameFromFileName(ComponentParser.getAdapterName(componentName));

        String itemLayoutBinding = ComponentParser.getBindingClassNameForAdapter(componentName);

        String fileNameAdd = ComponentParser.getFragmentNameAdd(componentName);

        String classNameAdd = ComponentParser.getClassNameFromFileName(fileNameAdd);

        String bindingClassNameAdd = ComponentParser.getBindingClassNameForFragmentAdd(componentName);

        String layoutNameAdd = ComponentParser.getClassNameFromFileName
                (ComponentParser.getFragmentLayoutNameAdd(ComponentParser.getPascalCaseName(componentName)));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_VIEW_MODEL_CLASS_NAME, viewModelClassName);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME, bindingClassName);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME, layoutName);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_ADAPTER_CLASS, adapterClassName);
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);
        paramMap.put(TemplateFileConstant.KEY_ITEM_BINDING_NAME, itemLayoutBinding);
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME_ADD, classNameAdd);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME_ADD, bindingClassNameAdd);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME_ADD, layoutNameAdd);

        boolean generateRV = ConfigValueHelper.getAdapterComponents().contains(componentName) &&
                DatabaseGenerator.entityListName.contains(componentName);
        paramMap.put(TemplateFileConstant.KEY_GENERATE_RV_FRAGMENT, generateRV);

        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.FRAGMENT_CLASS_LOCATION, velocityContext);

        if (generateRV) {
            config.writeFile(
                    packageDirectory +
                            File.separator + fileNameAdd
                    , TemplateFileConstant.FRAGMENT_ADD_CLASS_LOCATION, velocityContext);
        }
    }

}
