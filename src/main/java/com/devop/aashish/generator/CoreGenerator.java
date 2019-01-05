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
 * i. Generate core package and class
 * </p>
 */

public class CoreGenerator {

    public static void generateCore() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, String> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContext(versionGradleMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.CORE,
                ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APP_EXECUTOR_FILE
                , TemplateFileConstant.APP_EXECUTOR_LOCATION, velocityContext);
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APP_HOLDER_FILE
                , TemplateFileConstant.APP_HOLDER_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APPLICATION_CLASS_FILE
                , TemplateFileConstant.APPLICATION_CLASS_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.INJECTION_FILE
                , TemplateFileConstant.INJECTION_LOCATION, velocityContext);
    }

    private static Map<String, String> getValues() {
        Map<String, String> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        return param;
    }
}
