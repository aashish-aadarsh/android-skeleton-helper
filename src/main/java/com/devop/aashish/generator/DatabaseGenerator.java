package com.devop.aashish.generator;

import com.devop.aashish.constant.ApplicationConstant;
import com.devop.aashish.constant.TemplateFileConstant;
import com.devop.aashish.model.JsonEntity;
import com.devop.aashish.parser.ConfigValueHelper;
import com.devop.aashish.parser.VelocityConfig;
import com.devop.aashish.utility.AttributeHelper;
import com.devop.aashish.utility.ComponentParser;
import com.devop.aashish.utility.JsonEntityParser;
import com.devop.aashish.utility.PathUtil;
import org.apache.velocity.VelocityContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * i. Generate Database class.
 * ii. Generate Entity, Dao
 * </p>
 */
public class DatabaseGenerator {

    public static Set<String> entityListName = new HashSet<>();
    public static Set<JsonEntity> daoList = new HashSet<>();
    public static Set<String> daoListName = new HashSet<>();
    private static Logger logger = LoggerFactory.getLogger(DatabaseGenerator.class);
    private static VelocityConfig config;

    public static void generateDBComponent(String inputDirectory) throws IOException {
        config = new VelocityConfig();
        config.initInfo();

        generateBaseDao();

        File entityDirectory = new File(inputDirectory);
        if (entityDirectory.exists() && entityDirectory.isDirectory()) {
            File[] files = entityDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    JSONParser jsonParser = new JSONParser();
                    Object obj;
                    try {
                        obj = jsonParser.parse(new FileReader(file.getPath()));
                        JSONObject jsonObject = (JSONObject) obj;
                        JsonEntity jsonEntity = JsonEntityParser.parseJsonToEntity
                                (file.getName().split("\\.")[0], jsonObject);
                        generateEntity(jsonEntity);
                    } catch (ParseException e) {
                        logger.error("\n\n\nUnable to parse json.. Please check json {}..\n\n\n", file.getName());
                    }

                }
            }
            generateDao();
        } else {
            logger.warn("\nNo directory named entity present....");
        }
        generateDatabase();
    }


    private static void generateEntity(JsonEntity jsonEntity) {

        String fileName = ComponentParser.getEntityClass(jsonEntity.getClassName());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        paramMap.put(TemplateFileConstant.KEY_JSON_ENTITY, jsonEntity);

        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.DATA_SOURCE_DB_ENTITY,
                ConfigValueHelper.getMainJavaDirectory());
        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.ENTITY_LOCATION, velocityContext);
    }

    private static void generateBaseDao() {
        String fileName = ComponentParser.getEntityClass("BaseDao");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.DATA_SOURCE_DB_DAO,
                ConfigValueHelper.getMainJavaDirectory());
        config.writeFile(
                packageDirectory +
                        File.separator + fileName
                , TemplateFileConstant.BASE_DAO_LOCATION, velocityContext);
    }

    private static void generateDao() {
        DatabaseGenerator.daoList.forEach(jsonEntity -> {
            String fileName = ComponentParser.getDaoClass(jsonEntity.getCompleteEntityName().replaceAll("\\.", ""));
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
            paramMap.put(TemplateFileConstant.KEY_JSON_ENTITY, jsonEntity);

            VelocityContext velocityContext = config.getVelocityContextObject(paramMap);

            String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.
                            PackageConstant.DATA_SOURCE_DB_DAO,
                    ConfigValueHelper.getMainJavaDirectory());
            config.writeFile(
                    packageDirectory +
                            File.separator + fileName
                    , TemplateFileConstant.DAO_LOCATION, velocityContext);
        });
    }

    private static void generateDatabase() {
        VelocityConfig config = new VelocityConfig();
        config.initInfo();
        Map<String, Object> param = new HashMap<>();
        param.put(TemplateFileConstant.KEY_APP_ID, ConfigValueHelper.getApplicationId());
        param.put(TemplateFileConstant.APP_DATABASE_NAME, ConfigValueHelper.getAppDatabaseName());
        param.put(TemplateFileConstant.KEY_ENTITIES, entityListName);
        param.put(TemplateFileConstant.KEY_DAO, daoListName);
        param.put("AttributeHelper", new AttributeHelper());

        VelocityContext velocityContext = config.getVelocityContextObject(param);

        String packageDirectory = PathUtil.getFilePathFromPackage(ApplicationConstant.PackageConstant.DATA_SOURCE_DB,
                ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectory +
                        File.separator + TemplateFileConstant.APP_DATABASE_FILE
                , TemplateFileConstant.APP_DATABASE_LOCATION, velocityContext);

        String packageDirectoryConverter = PathUtil.getFilePathFromPackage(ApplicationConstant.
                        PackageConstant.DATA_SOURCE_DB_CONVERTER,
                ConfigValueHelper.getMainJavaDirectory());

        config.writeFile(
                packageDirectoryConverter +
                        File.separator + TemplateFileConstant.DATE_CONVERTER_FILE
                , TemplateFileConstant.DATE_CONVERTER_LOCATION, velocityContext);

    }
}
