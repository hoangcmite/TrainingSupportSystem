/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legon
 */
public class Settingtitle {
    public String title;
    public int sid;
    public int status;
    
    public Settingtitle() {
    }

    public Settingtitle(String title, int sid) {
        this.title = title;
        this.sid = sid;
    }

    public Settingtitle(String title, int sid, int status) {
        this.title = title;
        this.sid = sid;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
    
}
