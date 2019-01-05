package com.devop.aashish.generator;

import com.devop.aashish.constant.PropertyFileConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.PropertyFileUtil;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * i. Generate version.gradle.
 * </p>
 */
public class VersionGradleGenerator {

    public static void generateVersionGradleFile() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, String> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContext(versionGradleMap);
        config.writeFile(
                ConfigValueHelper.getRootDirectory() +
                        File.separator + TemplateFileConstant.VERSION_GRADLE_FILE
                , TemplateFileConstant.VERSION_GRADLE_LOCATION, velocityContext);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getValues() {
        PropertyFileUtil propertyFileUtil = new PropertyFileUtil(ConfigValueHelper.getGeneratorFilePath(),
                PropertyFileConstant.VERSION_PROPERTIES);
        Properties properties = propertyFileUtil.getProp();
        Map<String, String> param = new HashMap(properties);
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        return param;
    }
}
