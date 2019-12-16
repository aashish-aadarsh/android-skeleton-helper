package com.devop.aashish;

import com.devop.aashish.constant.PropertyFileConstant;
import com.devop.aashish.generator.*;
import com.devop.aashish.parser.DirectoryConfig;
import com.devop.aashish.parser.GeneratorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


/**
 * @author : Aashish Aadarsh
 * Follow Me:  <a>"https://github.com/aashish-aadarsh"</a>
 * Created Date: 1/4/2019
 * <p>
 * This is the main class for invocation.
 * Pass the location of properties folder through command line args
 * If command line args is null, application assumes the properties directory in current context
 * of execution.
 *</p>
 * <code>
 * java -jar AndroidSkeletonHelper.jar 'D://PathToPropertiesFolder//'
 *
 * java -jar AndroidSkeletonHelper.jar
 * </code>
 */

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) throws IOException {

        logger.info("\n === Execution Starts ====\n");

        StringBuilder inputFilePath = new StringBuilder();

        if (args.length == 1) {
            inputFilePath.append(args[0]);
        } else {
            inputFilePath.append(System.getProperty("user.dir"));
        }

        inputFilePath.append(File.separator).append(PropertyFileConstant.DIR_NAME_PROPERTY);

        logger.debug("@@ Input file path for properties...{}", inputFilePath.toString());

        new GeneratorConfig(inputFilePath.toString()).initInfo();
        new DirectoryConfig().initInfo();

        DirectoryGeneration.createDirectorySkeleton();

        VersionGradleGenerator.generateVersionGradleFile();

        AndroidManifestGenerator.generateManifestFile();

        VOGenerator.generateVO();

        UtilityGenerator.generateUtilityClass();

        ViewGenerator.generateCommonView();

        NetworkGenerator.generateNetwork();

        CoreGenerator.generateCore();

        DatabaseGenerator.generateDBComponent(PropertyFileConstant.DIR_NAME_PROPERTY.
                concat(File.separator).concat(PropertyFileConstant.ENTITY_DIRECTORY));

        ComponentGenerator.generateComponents();

        logger.info("\n === Execution Ends ====\n");


    }


}
