package com.devop.aashish.utility;

import com.devop.aashish.generator.DatabaseGenerator;
import com.devop.aashish.model.JsonEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 * <p>
 * <p>
 * This utility class is  used to parse json for entity creation.
 * </p>
 */
public class JsonEntityParser {

    /**
     * @param resourceName of input json
     * @param inputObject  parsed from value in json file
     * @return JSON entity used in velocity template
     */
    public static JsonEntity parseJsonToEntity(String resourceName, JSONObject inputObject) {
        JsonEntity jsonEntity = new JsonEntity(AttributeHelper.getEntityName(resourceName),
                AttributeHelper.getPrefixName(resourceName), AttributeHelper.getTableName(resourceName),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        jsonEntity.setClassName(AttributeHelper.getEntityName(resourceName));
        jsonEntity.setCompleteEntityName(AttributeHelper.getEntityName(resourceName));
        DatabaseGenerator.entityListName.add(jsonEntity.getCompleteEntityName());
        DatabaseGenerator.daoList.add(jsonEntity);
        DatabaseGenerator.daoListName.add(jsonEntity.getCompleteEntityName().replaceAll("\\.", ""));

        performParsing(jsonEntity, inputObject);
        return jsonEntity;
    }

    /**
     * @param jsonEntity  created from input json
     * @param inputObject input json
     */
    @SuppressWarnings("unchecked")
    private static void performParsing(JsonEntity jsonEntity, JSONObject inputObject) {
        Set<String> keySet = inputObject.keySet();
        for (String key : keySet) {
            Object jsonObjectValue = inputObject.get(key);
            String parentIdFieldName = AttributeHelper.getParentIdName(jsonEntity.getEntityName());
            processObject(key, jsonObjectValue, parentIdFieldName, jsonEntity, false);
        }
    }

    /**
     * @param key                of json
     * @param jsonObjectValue    value of key
     * @param parentIdFieldName  id field of parent entity which has to be set in mapping table of list object
     * @param parentJsonEntity   of the current key getting parsed
     * @param isListObjectEntity to check if key is a nested list object
     */
    private static void processObject(String key, Object jsonObjectValue, String parentIdFieldName,
                                      JsonEntity parentJsonEntity, boolean isListObjectEntity) {

        if (jsonObjectValue instanceof JSONObject) {

            JsonEntity embeddedObject = new JsonEntity(key, AttributeHelper.getPrefixName(key),
                    null, new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>());

            embeddedObject.setClassName(AttributeHelper.getEntityName(key));
            if (null != parentJsonEntity)
                embeddedObject.setCompleteEntityName(AttributeHelper.
                        getEntityName(parentJsonEntity.getCompleteEntityName()) + "."
                        + AttributeHelper.getEntityName(key));
            if (isListObjectEntity) {
                embeddedObject.setListEntity(true);
                embeddedObject.setParentEntityId(parentIdFieldName);
                embeddedObject.setTableName(AttributeHelper.getTableName(key));
                if (null != parentJsonEntity)
                    embeddedObject.setCompleteEntityName(AttributeHelper.
                            getEntityName(parentJsonEntity.getCompleteEntityName()) + "."
                            + AttributeHelper.getEntityName(key));
                DatabaseGenerator.entityListName.add(embeddedObject.getCompleteEntityName());
                DatabaseGenerator.daoList.add(embeddedObject);
                DatabaseGenerator.daoListName.add(embeddedObject.getCompleteEntityName().replaceAll("\\.", ""));
            }

            if (parentJsonEntity != null && !isListObjectEntity) {
                parentJsonEntity.getNestedObjectAttributes().add(embeddedObject);
            }
            if (parentJsonEntity != null && isListObjectEntity) {
                parentJsonEntity.getNestedListAttributes().add(embeddedObject);
            }

            performParsing(embeddedObject, (JSONObject) jsonObjectValue);

        } else if (jsonObjectValue instanceof JSONArray) {
            JSONArray jArray = (JSONArray) jsonObjectValue;
            if (jArray.size() > 0) {
                Object obj = jArray.get(0);
                processObject(key, obj, parentIdFieldName, parentJsonEntity, true);
            }

        } else {
            parentJsonEntity.setPrimitiveAttributes(updatePrimitiveAttribs(parentJsonEntity.getPrimitiveAttributes(),
                    key, jsonObjectValue.getClass().getSimpleName()));
        }

    }

    /**
     * @param primitiveAttributes input list
     * @param key                 of entity
     * @param dataType            of variable of entity
     * @return list of attributes of entity with their data type
     */
    private static List<JsonEntity.AttributeEntity> updatePrimitiveAttribs(List<JsonEntity.AttributeEntity> primitiveAttributes,
                                                                           String key, String dataType) {
        JsonEntity.AttributeEntity resource = new JsonEntity.AttributeEntity();
        if ("Integer".equals(dataType)) {
            dataType = "Int";
        }
        resource.setDataType(dataType);
        resource.setAttributeName(key);
        primitiveAttributes.add(resource);
        return primitiveAttributes;
    }

}
