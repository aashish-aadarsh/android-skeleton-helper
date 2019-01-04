package com.devop.aashish.parser;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class VelocityConfig {
    private Logger logger = LoggerFactory.getLogger(VelocityConfig.class.getName());

    public void initInfo() {
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

    public VelocityContext getVelocityContext(Map<String, String> paramMap) {
        VelocityContext context = new VelocityContext();
        paramMap.forEach(context::put);
        return context;
    }

    public VelocityContext getVelocityContextObject(Map<String, Object> paramMap) {
        VelocityContext context = new VelocityContext();
        paramMap.forEach(context::put);
        return context;
    }


    public void writeFile(String outputFileLocation, String templateFilePath, VelocityContext context) {
        logger.debug("Writing file...{},{},{}", outputFileLocation, templateFilePath, context.internalGetKeys());
        try {
            Writer writer = new FileWriter(new File(outputFileLocation));
            Velocity.mergeTemplate(templateFilePath, "UTF-8", context, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("Unable to write file {},{},{}", outputFileLocation, templateFilePath, context.internalGetKeys());
            e.printStackTrace();
        }

    }
}
