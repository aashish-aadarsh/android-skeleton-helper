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

public class NetworkGenerator {

    public static void generateNetwork() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, String> versionGradleMap = getValues();
        VelocityContext velocityContext = config.getVelocityContext(versionGradleMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DATA_SOURCE_NETWORK,
                ConfigValueHelper.getMainJavaDirectory());
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.NETWORK_BOUND_FILE
                , TemplateFileConstant.NETWORK_BOUND_LOCATION, velocityContext);
        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.RATE_LIMITER_FILE
                , TemplateFileConstant.RATE_LIMITER_LOCATION, velocityContext);

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.URL_INJECTOR_FILE
                , TemplateFileConstant.URL_INJECTOR_LOCATION, velocityContext);

        String packageDirectoryRetrofit = PathUtil.getFilePathFromPackage
                (ApplicationConstant.PackageConstant.DATA_SOURCE_NETWORK_RETROFIT,
                        ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectoryRetrofit +
                        File.separator + TemplateFileConstant.API_INTERFACE_FILE
                , TemplateFileConstant.API_INTERFACE_LOCATION, velocityContext);

        config.writeFile(
                packageDirectoryRetrofit +
                        File.separator + TemplateFileConstant.CLIENT_INTERCEPTOR_FILE
                , TemplateFileConstant.CLIENT_INTERCEPTOR_LOCATION, velocityContext);

        config.writeFile(
                packageDirectoryRetrofit +
                        File.separator + TemplateFileConstant.RETROFIT_CLIENT_FILE
                , TemplateFileConstant.RETROFIT_CLIENT_LOCATION, velocityContext);

    }

    private static Map<String, String> getValues() {
        Map<String, String> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        param.put(TemplateFileConstant.AUTHOR, ApplicationConstant.AUTHOR_AASHISH);
        return param;
    }
}
