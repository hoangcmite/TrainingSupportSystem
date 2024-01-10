/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Assignment {
    private int ass_id;
    private int subject_id;
    private String subject_code;
    private String ass_title;
    private String ass_body;
    private int eval_weight;
    private boolean team_work;
    private boolean ongoing;
    private boolean status;

    public Assignment() {
    }

    public Assignment(int ass_id, int subject_id, String subject_code, String ass_title, String ass_body, int eval_weight, boolean team_work, boolean ongoing, boolean status) {
        this.ass_id = ass_id;
        this.subject_id = subject_id;
        this.subject_code = subject_code;
        this.ass_title = ass_title;
        this.ass_body = ass_body;
        this.eval_weight = eval_weight;
        this.team_work = team_work;
        this.ongoing = ongoing;
        this.status = status;
    }

    public int getAss_id() {
        return ass_id;
    }

    public void setAss_id(int ass_id) {
        this.ass_id = ass_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getAss_title() {
        return ass_title;
    }

    public void setAss_title(String ass_title) {
        this.ass_title = ass_title;
    }

    public String getAss_body() {
        return ass_body;
    }

    public void setAss_body(String ass_body) {
        this.ass_body = ass_body;
    }

    public int getEval_weight() {
        return eval_weight;
    }

    public void setEval_weight(int eval_weight) {
        this.eval_weight = eval_weight;
    }

    public boolean isTeam_work() {
        return team_work;
    }

    public void setTeam_work(boolean team_work) {
        this.team_work = team_work;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
  
}
