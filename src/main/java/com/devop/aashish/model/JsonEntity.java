package com.devop.aashish.model;

import java.util.List;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 1/5/2019
 *
 * <p>
 * This model class map key of json file.
 * </p>
 */
public class JsonEntity {

    private String entityName;
    private String completeEntityName;
    private String className;

    private String parentEntityId;
    private String prefix;
    private boolean isListEntity;
    private String tableName;

    private List<AttributeEntity> primitiveAttributes;
    private List<JsonEntity> nestedObjectAttributes;

    private List<JsonEntity> nestedListAttributes;


    public JsonEntity(String entityName, String prefix, String tableName, List<AttributeEntity> primitiveAttributes,
                      List<JsonEntity> nestedObjectAttributes, List<JsonEntity> nestedListAttributes) {
        super();
        this.entityName = entityName;
        this.prefix = prefix;
        this.tableName = tableName;
        this.primitiveAttributes = primitiveAttributes;
        this.nestedObjectAttributes = nestedObjectAttributes;
        this.nestedListAttributes = nestedListAttributes;
    }


    public String getEntityName() {
        return entityName;
    }


    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }


    public String getParentEntityId() {
        return parentEntityId;
    }


    public void setParentEntityId(String parentEntityId) {
        this.parentEntityId = parentEntityId;
    }


    public String getPrefix() {
        return prefix;
    }


    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    public boolean isListEntity() {
        return isListEntity;
    }


    public void setListEntity(boolean isListEntity) {
        this.isListEntity = isListEntity;
    }


    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public List<AttributeEntity> getPrimitiveAttributes() {
        return primitiveAttributes;
    }


    public void setPrimitiveAttributes(List<AttributeEntity> primitiveAttributes) {
        this.primitiveAttributes = primitiveAttributes;
    }


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public List<JsonEntity> getNestedObjectAttributes() {
        return nestedObjectAttributes;
    }


    public void setNestedObjectAttributes(List<JsonEntity> nestedObjectAttributes) {
        this.nestedObjectAttributes = nestedObjectAttributes;
    }


    public List<JsonEntity> getNestedListAttributes() {
        return nestedListAttributes;
    }


    public void setNestedListAttributes(List<JsonEntity> nestedListAttributes) {
        this.nestedListAttributes = nestedListAttributes;
    }

    public String getCompleteEntityName() {
        return completeEntityName;
    }

    public void setCompleteEntityName(String completeEntityName) {
        this.completeEntityName = completeEntityName;
    }

    @Override
    public String toString() {
        return "JsonEntity [entityName=" + entityName + ", className=" + className + ", tableName=" + tableName
                + ", primitiveAttributes=" + primitiveAttributes + "]";
    }

    public static class AttributeEntity {
        private String attributeName;
        private String dataType;

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        @Override
        public String toString() {
            return "AttributeEntity [attributeName=" + attributeName + ", dataType=" + dataType + "]";
        }

    }


}
