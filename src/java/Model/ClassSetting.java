/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ClassSetting {
    private int classSetting_id;
    private int type_id;
    private String classSetting_type;
    private String classSetting_title;
    private String classSetting_value;
    private int class_id;
    private int display_order;
    private int status;
    private String description;

    public ClassSetting() {
    }

    public ClassSetting(int classSetting_id, int type_id, String classSetting_type, String classSetting_title, String classSetting_value, int class_id, int display_order, int status, String description) {
        this.classSetting_id = classSetting_id;
        this.type_id = type_id;
        this.classSetting_type = classSetting_type;
        this.classSetting_title = classSetting_title;
        this.classSetting_value = classSetting_value;
        this.class_id = class_id;
        this.display_order = display_order;
        this.status = status;
        this.description = description;
    }

    public int getClassSetting_id() {
        return classSetting_id;
    }

    public void setClassSetting_id(int classSetting_id) {
        this.classSetting_id = classSetting_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getClassSetting_type() {
        return classSetting_type;
    }

    public void setClassSetting_type(String classSetting_type) {
        this.classSetting_type = classSetting_type;
    }

    public String getClassSetting_title() {
        return classSetting_title;
    }

    public void setClassSetting_title(String classSetting_title) {
        this.classSetting_title = classSetting_title;
    }

    public String getClassSetting_value() {
        return classSetting_value;
    }

    public void setClassSetting_value(String classSetting_value) {
        this.classSetting_value = classSetting_value;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    
}
