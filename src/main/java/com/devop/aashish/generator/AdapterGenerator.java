package com.devop.aashish.generator;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.model.UIComponent;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.ComponentParser;
import com.devop.aashish.utility.ExcelDataHelper;
import com.devop.aashish.utility.FileHelper;
import com.devop.aashish.utility.PathUtil;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterGenerator {

    public static void generateAdapter(String componentName, VelocityConfig config) throws IOException {
        generateAdapterItemXML(componentName, config);
        generateAdapterClass(componentName, config);
    }

    private static void generateAdapterItemXML(String componentName, VelocityConfig config) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        String entityPackageDirectory = PathUtil.getPackageNameFromPath(ApplicationConstant.
                PackageConstant.DATA_SOURCE_DB_ENTITY);
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_ITEM, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_VO_PATH,
                entityPackageDirectory + "." + componentName);
        List<UIComponent> uiComponents = ExcelDataHelper.readExcelData(componentName);
        paramMap.put("uiComponents", uiComponents);
        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DIRECTORY_LAYOUT,
                ConfigValueHelper.getMainDirectory());
        FileHelper.createDirectory(packageDirectory);

        componentName = ComponentParser.getPascalCaseName(componentName);

        String layoutName = ComponentParser.getItemLayoutName(componentName);
        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        config.writeFile(
                packageDirectory +
                        File.separator + layoutName
                , TemplateFileConstant.ADAPTER_ITEM_LOCATION, velocityContext);


    }

    private static void generateAdapterClass(String componentName, VelocityConfig config) {
        String layoutName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getItemLayoutName(ComponentParser.getPascalCaseName(componentName)));

        String fileName = ComponentParser.getAdapterName(componentName);

        String className = ComponentParser.getClassNameFromFileName(fileName);

        String bindingClassName = ComponentParser.getBindingClassNameForAdapter(componentName);

        String classNameVO = ComponentParser.getClassNameFromFileName(componentName);


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME, bindingClassName);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME, layoutName);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_ITEM, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_VO_NAME, classNameVO);
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);


        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.ADAPTER_CLASS_LOCATION, velocityContext);
    }

}
