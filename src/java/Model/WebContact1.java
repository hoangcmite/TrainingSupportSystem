/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class WebContact1 {

    int id;
    int categoryId;
    int supporter_id;
    String fullName;
    String email;
    String mobile;
    String message;
    String reponse;
    boolean status;

    public WebContact1(int id, int categoryId, int supporter_id, String fullName, String email, String mobile, String message, String reponse, boolean status) {
        this.id = id;
        this.categoryId = categoryId;
        this.supporter_id = supporter_id;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.message = message;
        this.reponse = reponse;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSupporter_id() {
        return supporter_id;
    }

    public void setSupporter_id(int supporter_id) {
        this.supporter_id = supporter_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WebContact{" + "id=" + id + ", categoryId=" + categoryId + ", supporter_id=" + supporter_id + ", fullName=" + fullName + ", email=" + email + ", mobile=" + mobile + ", message=" + message + ", reponse=" + reponse + ", status=" + status + '}';
    }

}
