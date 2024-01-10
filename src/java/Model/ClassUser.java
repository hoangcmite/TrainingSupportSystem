/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ClassUser {
    private int class_id;
    private String class_code;
    private int user_id;
    private String full_name;
    private String user_email;
    private boolean status; 
    private String note;
    private Date dropout_date;
    private float ongoing_eval;
    private float final_eval;
    private float topic_eval;
    private int trainer_id;
    private int supporter_id;

    public ClassUser() {
    }

    public ClassUser(int class_id, String class_code, int user_id, String full_name, String user_email, boolean status, String note, Date dropout_date, float ongoing_eval, float final_eval, float topic_eval, int trainer_id, int supporter_id) {
        this.class_id = class_id;
        this.class_code = class_code;
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_email = user_email;
        this.status = status;
        this.note = note;
        this.dropout_date = dropout_date;
        this.ongoing_eval = ongoing_eval;
        this.final_eval = final_eval;
        this.topic_eval = topic_eval;
        this.trainer_id = trainer_id;
        this.supporter_id = supporter_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDropout_date() {
        return dropout_date;
    }

    public void setDropout_date(Date dropout_date) {
        this.dropout_date = dropout_date;
    }

    public float getOngoing_eval() {
        return ongoing_eval;
    }

    public void setOngoing_eval(float ongoing_eval) {
        this.ongoing_eval = ongoing_eval;
    }

    public float getFinal_eval() {
        return final_eval;
    }

    public void setFinal_eval(float final_eval) {
        this.final_eval = final_eval;
    }

    public float getTopic_eval() {
        return topic_eval;
    }

    public void setTopic_eval(float topic_eval) {
        this.topic_eval = topic_eval;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getSupporter_id() {
        return supporter_id;
    }

    public void setSupporter_id(int supporter_id) {
        this.supporter_id = supporter_id;
    }

    

    
}
