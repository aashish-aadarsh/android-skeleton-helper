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

public class VOGenerator {

    public static void generateVO() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, String> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContext(versionGradleMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.VO,
                ConfigValueHelper.getMainJavaDirectory());
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.RESOURCE_FILE
                , TemplateFileConstant.VO_RESOURCE_LOCATION, velocityContext);
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.STATUS_FILE
                , TemplateFileConstant.VO_STATUS_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APP_CONSTANT_FILE
                , TemplateFileConstant.VO_APP_CONST_LOCATION, velocityContext);

    }

    private static Map<String, String> getValues() {
        Map<String, String> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        return param;
    }
}
