/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class WebContact {
    private String supporterName;
    private String fullName;
    private String email;
    private String mobile;
    private String message;
    private String response;
    private int id;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WebContact() {
    }

    public WebContact(String supporterId, String fullName, String email, String mobile, String message, String response, int id, int status) {
        this.supporterName = supporterId;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.message = message;
        this.response = response;
        this.id = id;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSupporterName() {
        return supporterName;
    }

    public void setSupporterName(String supporterId) {
        this.supporterName = supporterId;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
            
}
