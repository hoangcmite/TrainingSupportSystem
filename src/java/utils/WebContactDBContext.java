/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.User;
import Model.WebContact;

/**
 *
 * @author ADMIN
 */
public class WebContactDBContext {

    DBContext con = new DBContext() {
        @Override
        public ArrayList list() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Object get(int id) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void insert(Object model) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void update(Object model) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void delete(int id) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };
    Connection con2 = con.getConnection();

    
    public int getTotalWebContact(){
        String sql = "SELECT count(*) FROM MyDBG3.web_contact;";
        try {
            PreparedStatement stm = con2.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
        }
        return 0;
    }
    public ArrayList<WebContact> getWebContact(int page) {
        ArrayList<WebContact> webcs = new ArrayList<>();
        try {
            String sql = "SELECT user.full_name, contact_id, web_contact.full_name, web_contact.email,web_contact.mobile, message, response,web_contact.status FROM web_contact,user where supporter_id = user_id order by contact_id DESC limit ?,?";
            PreparedStatement stm = con2.prepareStatement(sql);
            stm.setInt(1, page*5);
            stm.setInt(2, page+5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WebContact webc = new WebContact();
                webc.setFullName(rs.getString(3));
                webc.setSupporterName(rs.getString(1));
                webc.setEmail(rs.getString(4));
                webc.setMobile(rs.getString(5));
                webc.setMessage(rs.getString(6));
                webc.setResponse(rs.getString(7));
                webc.setId(rs.getInt(2));
                webc.setStatus(rs.getInt(8));
                webcs.add(webc);
            }
            return webcs;
        } catch (Exception ex) {

            System.out.println(ex);
        }
        return null;
    } 
     public void updateWebContact(int userID,String[] ids, String[] response) {
        String sql = "update web_contact set response = ?,status =b'1',supporter_id = ?   where contact_id = ?";
        try {
            for(int i = 0; i< ids.length ; i++){
            PreparedStatement stm = con2.prepareStatement(sql);
            stm.setString(1, response[i]);
            stm.setInt(3, Integer.parseInt(ids[i]) );
            stm.setInt(2, userID);
            stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//    public static void main(String[] args) {
//         WebContactDBContext db = new WebContactDBContext();
//            String[] ids = {"1", "4"};
//            String[] response = {"ádasd", "ádasdsa"};
//            db.updateWebContact(ids, response);
//         }
   }

