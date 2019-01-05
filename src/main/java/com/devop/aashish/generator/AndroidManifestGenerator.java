package com.devop.aashish.generator;

import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.model.ComponentType;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.ComponentParser;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * Generate AndroidManifest.xml.
 * </p>
 */
public class AndroidManifestGenerator {

    public static void generateManifestFile() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, Object> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContextObject(versionGradleMap);
        config.writeFile(
                ConfigValueHelper.getMainDirectory() +
                        File.separator + TemplateFileConstant.ANDROID_MANIFEST_FILE
                , TemplateFileConstant.MANIFEST_LOCATION, velocityContext);
    }

    private static Map<String, Object> getValues() {
        Map<String, Object> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());

        Map<String, ComponentType> appComponents = ConfigValueHelper.getAppComponents();
        List<String> activityList = new ArrayList<>();
        appComponents.forEach((componentName, componentType) -> {
            if (componentType.equals(ComponentType.ACTIVITY)) {
                String className = ComponentParser.getClassNameFromFileName(
                        ComponentParser.getActivityName(componentName));
                String prefix = componentName.toLowerCase() + ".";
                String activityName = prefix + className;
                activityList.add(activityName);
            }

        });

        param.put(TemplateFileConstant.KEY_ACTIVITY_LIST, activityList);
        return param;
    }
}
