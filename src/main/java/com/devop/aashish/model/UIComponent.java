package com.devop.aashish.model;

/**
 * @author : Aashish Aadarsh
 * Follow Me:  "https://github.com/aashish-aadarsh"
 * Created Date: 16/12/2019
 *
 * <p>
 * This model class map key of UI component.
 * </p>
 */
public class UIComponent {
    private String attributeName;
    private String attributeType;
    private String inputType;
    private String constraintType;
    private String constraintValue;
    private String hintText;
    private boolean isRowItem;

    public UIComponent(String attributeName, String attributeType, String hintText, boolean isRowItem) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.isRowItem = isRowItem;
        this.hintText = hintText;
    }

    public UIComponent() {

    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    public String getConstraintValue() {
        return constraintValue;
    }

    public void setConstraintValue(String constraintValue) {
        this.constraintValue = constraintValue;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public boolean getIsRowItem() {
        return isRowItem;
    }

    public void setRowItem(boolean rowItem) {
        isRowItem = rowItem;
    }
}
