/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Subject {
    private int subjectId;
    private String subjectCode;
    private String subjectName;
    private ArrayList<User> list = new ArrayList<>();
    private Boolean status;
    private String body;
    private User user;
    private int managerId;
    private int expertId;
    private String managerName;
    private String expertName;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public Subject(int subjectId, String subjectCode, String subjectName, Boolean status, String body, int managerId, int expertId) {
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.status = status;
        this.body = body;
        this.managerId = managerId;
        this.expertId = expertId;
    }

    
    
    

    public Subject(int subjectId, String subjectCode, String subjectName, Boolean status, String body, User user, int managerId, int expertId) {
        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.status = status;
        this.body = body;
        this.user = user;
        this.managerId = managerId;
        this.expertId = expertId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }
    
    

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public Subject() {
    }

    

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public Boolean getStatus() {
       
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", list=" + list + ", status=" + status + ", body=" + body + ", managerId=" + managerId + ", expertId=" + expertId + "}\n";
    }
    
    
}
