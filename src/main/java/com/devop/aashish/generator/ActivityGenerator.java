package com.devop.aashish.generator;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.ComponentParser;
import com.devop.aashish.utility.FileHelper;
import com.devop.aashish.utility.PathUtil;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ActivityGenerator {

    public static void generateActivity(String componentName, VelocityConfig config) {
        generateActivityXML(componentName, config);
        generateActivityClass(componentName, config);
        FragmentGenerator.generateFragment(componentName, config);
    }

    private static void generateActivityXML(String componentName, VelocityConfig config) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);

        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DIRECTORY_LAYOUT,
                ConfigValueHelper.getMainDirectory());
        FileHelper.createDirectory(packageDirectory);

        componentName = ComponentParser.getPascalCaseName(componentName);

        String activityName = ComponentParser.getActivityLayoutName(componentName);
        config.writeFile(
                packageDirectory +
                        File.separator + activityName
                , TemplateFileConstant.ACTIVITY_LOCATION, velocityContext);
    }

    private static void generateActivityClass(String componentName, VelocityConfig config) {

        String fileName = ComponentParser.getActivityName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);

        String viewModelClassName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getViewModelName(componentName));

        String bindingClassName = ComponentParser.getBindingClassNameForActivity(componentName);

        String layoutName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getActivityLayoutName(ComponentParser.getPascalCaseName(componentName)));
        String fragmentName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getFragmentName(componentName));

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_VIEW_MODEL_CLASS_NAME, viewModelClassName);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME, bindingClassName);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME, layoutName);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_FRAGMENT_NAME, fragmentName);

        VelocityContext velocityContext = config.getVelocityContext(paramMap);
        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.ACTIVITY_CLASS_LOCATION, velocityContext);

    }

}
