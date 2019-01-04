package com.devop.aashish.utility;

import com.devop.aashish.generator.DatabaseGenerator;
import com.devop.aashish.model.JsonEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class JsonEntityParser {

    public static JsonEntity parseJsonToEntity(String resourceName, JSONObject inputObject) {
        JsonEntity jsonEntity = new JsonEntity(AttributeHelper.getEntityName(resourceName),
                AttributeHelper.getPrefixName(resourceName), AttributeHelper.getTableName(resourceName),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        jsonEntity.setClassName(AttributeHelper.getEntityName(resourceName));
        jsonEntity.setCompleteEntityName(AttributeHelper.getEntityName(resourceName));
        DatabaseGenerator.entityListName.add(jsonEntity.getCompleteEntityName());
        performParsing(jsonEntity, inputObject);
        return jsonEntity;
    }

    @SuppressWarnings("unchecked")
    private static void performParsing(JsonEntity jsonEntity, JSONObject inputObject) {
        Set<String> keySet = inputObject.keySet();
        for (String key : keySet) {
            Object jsonObjectValue = inputObject.get(key);
            String parentIdFieldName = AttributeHelper.getParentIdName(jsonEntity.getEntityName());
            processObject(key, jsonObjectValue, parentIdFieldName, jsonEntity, false);
        }
    }

    private static void processObject(String key, Object jsonObjectValue, String parentIdFieldName,
                                      JsonEntity parentJsonEntity, boolean isListObjectEntity) {

        if (jsonObjectValue instanceof JSONObject) {

            JsonEntity embeddedObject = new JsonEntity(key, AttributeHelper.getPrefixName(key),
                    null, new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>());

            embeddedObject.setClassName(AttributeHelper.getEntityName(key));

            if (isListObjectEntity) {
                embeddedObject.setListEntity(true);
                embeddedObject.setParentEntityId(parentIdFieldName);
                embeddedObject.setTableName(AttributeHelper.getTableName(key));
                embeddedObject.setCompleteEntityName(AttributeHelper.
                        getEntityName(parentJsonEntity.getCompleteEntityName()) + "."
                        + AttributeHelper.getEntityName(key));
                DatabaseGenerator.entityListName.add(embeddedObject.getCompleteEntityName());
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
