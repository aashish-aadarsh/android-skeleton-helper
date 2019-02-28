package com.devop.aashish.generator;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.model.ComponentType;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.ComponentParser;
import com.devop.aashish.utility.FileHelper;
import com.devop.aashish.utility.PathUtil;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 * <p>
 * <p>
 * i. Generate XML files.
 * ii. Generate Activites, Fragmnet
 * iii. Generate Repo, RepoImpl, ViewModel
 * iv. Generate Adapter and VO
 * </p>
 */
public class ComponentGenerator {
    private static VelocityConfig config;

    public static void generateComponents() {
        config = new VelocityConfig();
        config.initInfo();

        Map<String, ComponentType> appComponents = ConfigValueHelper.getAppComponents();

        appComponents.forEach((componentName, componentType) -> {
            if (componentType.equals(ComponentType.ACTIVITY)) {
                generateXMLFiles(componentName, true);
                generateActivity(componentName);
                generateFragments(componentName);
            } else {
                generateXMLFiles(componentName, false);
                generateFragments(componentName);
            }

            generateRepo(componentName);
            generateRepoImpl(componentName);
            generateViewModel(componentName);

        });

        ConfigValueHelper.getAdapterComponents().forEach(componentName -> {
            generateAdapterVO(componentName.trim());
            generateAdapterItemXML(componentName.trim());
            generateAdapterClass(componentName.trim());
        });
    }


    private static void generateXMLFiles(String componentName, boolean generateActivity) {
        Map<String, String> paramMap = new HashMap<>();
        VelocityContext velocityContext = config.getVelocityContext(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DIRECTORY_LAYOUT,
                ConfigValueHelper.getMainDirectory());
        FileHelper.createDirectory(packageDirectory);

        componentName = ComponentParser.getPascalCaseName(componentName);
        if (generateActivity) {
            String activityName = ComponentParser.getActivityLayoutName(componentName);
            config.writeFile(
                    packageDirectory +
                            File.separator + activityName
                    , TemplateFileConstant.ACTIVITY_LOCATION, velocityContext);
        }

        String fragmentName = ComponentParser.getFragmentLayoutName(componentName);
        config.writeFile(
                packageDirectory +
                        File.separator + fragmentName
                , TemplateFileConstant.FRAGMENT_LOCATION, velocityContext);


    }


    private static void generateRepo(String componentName) {
        String fileName = ComponentParser.getRepositoryName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);

        VelocityContext velocityContext = config.getVelocityContext(paramMap);
        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DATA_SOURCE_REPOSITORY,
                ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.REPO_LOCATION, velocityContext);
    }

    private static void generateRepoImpl(String componentName) {
        String fileName = ComponentParser.getRepositoryImplName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);
        String classNameRepo = ComponentParser.getClassNameFromFileName
                (ComponentParser.getRepositoryName(componentName));

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_REPO_CLASS_NAME, classNameRepo);

        VelocityContext velocityContext = config.getVelocityContext(paramMap);
        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.DATA_SOURCE_REPOSITORY_IMPL,
                ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.REPO_IMPL_LOCATION, velocityContext);
    }

    private static void generateViewModel(String componentName) {
        String fileName = ComponentParser.getViewModelName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);
        String classNameRepo = ComponentParser.getClassNameFromFileName
                (ComponentParser.getRepositoryName(componentName));
        String classNameRepoImpl = ComponentParser.getClassNameFromFileName
                (ComponentParser.getRepositoryImplName(componentName));

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_REPO_CLASS_NAME, classNameRepo);
        paramMap.put(TemplateFileConstant.KEY_REPO_IMPL_CLASS_NAME, classNameRepoImpl);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());

        VelocityContext velocityContext = config.getVelocityContext(paramMap);
        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.VIEW_MODEL_LOCATION, velocityContext);
    }

    private static void generateActivity(String componentName) {

        String fileName = ComponentParser.getActivityName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);

        String viewModelClassName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getViewModelName(componentName));

        String bindingClassName = ComponentParser.getBindingClassNameForActivity(componentName);

        String layoutName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getActivityLayoutName(ComponentParser.getPascalCaseName(componentName)));

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_VIEW_MODEL_CLASS_NAME, viewModelClassName);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME, bindingClassName);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME, layoutName);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());

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

    private static void generateFragments(String componentName) {
        String layoutName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getFragmentLayoutName(ComponentParser.getPascalCaseName(componentName)));

        String fileName = ComponentParser.getFragmentName(componentName);

        String className = ComponentParser.getClassNameFromFileName(fileName);

        String viewModelClassName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getViewModelName(componentName));

        String bindingClassName = ComponentParser.getBindingClassNameForFragment(componentName);


        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_VIEW_MODEL_CLASS_NAME, viewModelClassName);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME, bindingClassName);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME, layoutName);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());

        VelocityContext velocityContext = config.getVelocityContext(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.FRAGMENT_CLASS_LOCATION, velocityContext);
    }

    private static void generateAdapterItemXML(String componentName) {
        Map<String, String> paramMap = new HashMap<>();

        String classNameVO = ComponentParser.getClassNameFromFileName(
                ComponentParser.getVOName(componentName));

        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_ITEM, componentName.toLowerCase() + "vo");
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_VO_PATH,
                ApplicationConstant.PackageConstant.VIEW + "." + componentName.toLowerCase() + "." + classNameVO);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DIRECTORY_LAYOUT,
                ConfigValueHelper.getMainDirectory());
        FileHelper.createDirectory(packageDirectory);

        componentName = ComponentParser.getPascalCaseName(componentName);

        String layoutName = ComponentParser.getItemLayoutName(componentName);
        VelocityContext velocityContext = config.getVelocityContext(paramMap);

        config.writeFile(
                packageDirectory +
                        File.separator + layoutName
                , TemplateFileConstant.ADAPTER_ITEM_LOCATION, velocityContext);


    }

    private static void generateAdapterVO(String componentName) {

        String fileName = ComponentParser.getVOName(componentName);

        String className = ComponentParser.getClassNameFromFileName(fileName);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        Map<String, String> paramMap = new HashMap<>();

        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_ITEM,
                ApplicationConstant.PackageConstant.VIEW + "." + componentName.toLowerCase() + className);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());

        VelocityContext velocityContext = config.getVelocityContext(paramMap);

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.ADAPTER_ITEM_VO, velocityContext);
    }

    private static void generateAdapterClass(String componentName) {
        String layoutName = ComponentParser.getClassNameFromFileName
                (ComponentParser.getItemLayoutName(ComponentParser.getPascalCaseName(componentName)));

        String fileName = ComponentParser.getAdapterName(componentName);

        String className = ComponentParser.getClassNameFromFileName(fileName);

        String bindingClassName = ComponentParser.getBindingClassNameForAdapter(componentName);

        String classNameVO = ComponentParser.getClassNameFromFileName(
                ComponentParser.getVOName(componentName));


        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_BINDING_CLASS_NAME, bindingClassName);
        paramMap.put(TemplateFileConstant.KEY_LAYOUT_FILE_NAME, layoutName);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_ITEM, componentName.toLowerCase() + "vo");
        paramMap.put(TemplateFileConstant.KEY_ITEM_ADAPTER_VO_NAME, classNameVO);

        VelocityContext velocityContext = config.getVelocityContext(paramMap);

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
