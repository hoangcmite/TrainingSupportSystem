/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legon
 */
public class SettingValue {
     public int title;
    public String value;
    public int status;
    public String description;
    
    
    public SettingValue() {
    }

    public SettingValue(int title, String value) {
        this.title = title;
        this.value = value;
    }

    public SettingValue(String value, int status) {
        this.value = value;
        this.status = status;
    }

    public SettingValue(String value, int status, String description) {
        this.value = value;
        this.status = status;
        this.description = description;
    }

    public SettingValue(int title, String value, int status, String description) {
        this.title = title;
        this.value = value;
        this.status = status;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
    
}
