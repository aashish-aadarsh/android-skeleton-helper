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

public class ViewGenerator {

    public static void generateView() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, String> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContext(versionGradleMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.VIEW_COMMON,
                ConfigValueHelper.getMainJavaDirectory());
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.BINDING_ADAPTER_FILE
                , TemplateFileConstant.BINDING_ADAPTER_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.DATA_BOUND_ADAPTER_FILE
                , TemplateFileConstant.DATA_BOUND_ADAPTER_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.DATA_VIEW_HOLDER_FILE
                , TemplateFileConstant.DATA_VIEW_HOLDER_LOCATION, velocityContext);

    }

    private static Map<String, String> getValues() {
        Map<String, String> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        return param;
    }
}
