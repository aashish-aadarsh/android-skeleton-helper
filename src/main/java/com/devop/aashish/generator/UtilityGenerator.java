package com.devop.aashish.generator;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.PathUtil;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * i. Generate utility module.
 * </p>
 */

public class UtilityGenerator {

    public static void generateUtilityClass() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, String> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContext(versionGradleMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.UTILITY,
                ConfigValueHelper.getMainJavaDirectory());
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.ACTIVITY_UTIL_FILE
                , TemplateFileConstant.ACTIVITY_UTIL_LOCATION, velocityContext);
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APP_UTIL_FILE
                , TemplateFileConstant.APP_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.CALLBACK_CUSTOM_FILE
                , TemplateFileConstant.CALLBACK_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.CONNECTION_UTIL_FILE
                , TemplateFileConstant.CONNECTION_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.PREFERENCE_UTIL_FILE
                , TemplateFileConstant.PREFERENCE_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.DATE_TIME_FILE
                , TemplateFileConstant.DATE_TIME_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APP_VALIDATOR_FILE
                , TemplateFileConstant.APP_VALIDATOR_LOCATION, velocityContext);
    }

    private static Map<String, String> getValues() {
        Map<String, String> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        return param;
    }
}
