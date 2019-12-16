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
                ActivityGenerator.generateActivity(componentName, config);
            } else {
                FragmentGenerator.generateFragment(componentName, config);
            }

            boolean generateRV = ConfigValueHelper.getAdapterComponents().contains(componentName) &&
                    DatabaseGenerator.entityListName.contains(componentName);

            generateRepo(componentName, generateRV);
            generateRepoImpl(componentName, generateRV);
            generateViewModel(componentName, generateRV);

            if (generateRV) {
                AdapterGenerator.generateAdapter(componentName, config);
            }
        });
    }

    private static void generateRepo(String componentName, boolean generateRV) {
        String fileName = ComponentParser.getRepositoryName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);
        paramMap.put(TemplateFileConstant.KEY_GENERATE_RV_FRAGMENT, generateRV);
        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);
        String packageDirectory =
                PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DATA_SOURCE_REPOSITORY,
                        ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.REPO_LOCATION, velocityContext);
    }

    private static void generateRepoImpl(String componentName, boolean generateRV) {
        String fileName = ComponentParser.getRepositoryImplName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);
        String classNameRepo = ComponentParser.getClassNameFromFileName
                (ComponentParser.getRepositoryName(componentName));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_REPO_CLASS_NAME, classNameRepo);
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);
        paramMap.put(TemplateFileConstant.KEY_GENERATE_RV_FRAGMENT, generateRV);
        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);
        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.DATA_SOURCE_REPOSITORY_IMPL,
                ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.REPO_IMPL_LOCATION, velocityContext);
    }

    private static void generateViewModel(String componentName, boolean generateRV) {
        String fileName = ComponentParser.getViewModelName(componentName);
        String className = ComponentParser.getClassNameFromFileName(fileName);
        String classNameRepo = ComponentParser.getClassNameFromFileName
                (ComponentParser.getRepositoryName(componentName));
        String classNameRepoImpl = ComponentParser.getClassNameFromFileName
                (ComponentParser.getRepositoryImplName(componentName));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_CLASS_NAME, className);
        paramMap.put(TemplateFileConstant.KEY_REPO_CLASS_NAME, classNameRepo);
        paramMap.put(TemplateFileConstant.KEY_REPO_IMPL_CLASS_NAME, classNameRepoImpl);
        paramMap.put(TemplateFileConstant.KEY_COMPONENT, componentName.toLowerCase());
        paramMap.put(TemplateFileConstant.KEY_ENTITY_NAME, componentName);
        paramMap.put(TemplateFileConstant.KEY_GENERATE_RV_FRAGMENT, generateRV);
        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.VIEW + File.separator + componentName.toLowerCase(),
                ConfigValueHelper.getMainJavaDirectory());

        FileHelper.createDirectory(packageDirectory);

        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.VIEW_MODEL_LOCATION, velocityContext);
    }
}
