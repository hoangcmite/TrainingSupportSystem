/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legon
 */
public class permission {
    
 
    public boolean access;
    public boolean edit;
    public boolean add;
    public boolean delete;

    public permission() {
    }

    public permission(boolean access, boolean edit, boolean add, boolean delete) {
        
        this.access = access;
        this.edit = edit;
        this.add = add;
        this.delete = delete;
    }

  

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return  access + "/" + edit + "/" + add + "/" + delete;
    }
    
    
    
}
