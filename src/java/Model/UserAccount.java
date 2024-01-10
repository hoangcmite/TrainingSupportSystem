/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Legon
 */
public class UserAccount {

     public int id;
    public int isleader;
    public String fullname;
     public String mail;
    public String mobie;
    public String password;
   public String avatar_url;
    public String status;
    public String note;
    public String createat;
    public String code;
 public String company;
    public String position;
    public String address;
     public String role;
     
    public UserAccount() {
    }

 public UserAccount(int id, String fullname, String mail, String mobie, String password, String avatar_url, String status, String note, String createat, String code, String company, String position, String address, String role) {
        this.id = id;
        this.fullname = fullname;
        this.mail = mail;
        this.mobie = mobie;
        this.password = password;
        this.avatar_url = avatar_url;
        this.status = status;
        this.note = note;
        this.createat = createat;
        this.code = code;
        this.company = company;
        this.position = position;
        this.address = address;
        this.role = role;
    }

 

    public UserAccount(int id, String fullname, String mail, String mobie, String password, String avatar_url, String status, String note, String createat, String code, String role) {
        this.id = id;
        this.fullname = fullname;
        this.mail = mail;
        this.mobie = mobie;
        this.password = password;
        this.avatar_url = avatar_url;
        this.status = status;
        this.note = note;
        this.createat = createat;
        this.code = code;
        this.role = role;
    }

    public UserAccount(String company, String position, String address) {
        this.company = company;
        this.position = position;
        this.address = address;
    }

//    public UserAccount(int id, int isleader, String fullname, String status,String ) {
//        this.id = id;
//        this.isleader = isleader;
//        this.fullname = fullname;
//        this.status = status;
//    }

    public UserAccount(int id, int isleader, String status, String fullname, String mail) {
        this.id = id;
        this.isleader = isleader;
        this.fullname = fullname;
        this.mail = mail;
        this.status = status;
    }

   

    public int getIsleader() {
        return isleader;
    }

    public void setIsleader(int isleader) {
        this.isleader = isleader;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

 



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobie() {
        return mobie;
    }

    public void setMobie(String mobie) {
        this.mobie = mobie;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
