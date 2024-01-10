/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Classes;
import Model.Subject;
import Model.Account;

/**
 *
 * @author dell
 */
public class ClassDBContext extends DBContext1 {

    public ArrayList<Account> getTrainerList() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "select distinct u.user_id, u.full_name from class c \n"
                    + "inner join user u on c.trainer_id = u.user_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Account u = new Account();
                u.setUserId(rs.getInt("user_id"));
                u.setFullname(rs.getString("full_name"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Classes> getTermList() {
        ArrayList<Classes> list = new ArrayList<>();
        try {
            String sql = "select distinct term_id, s.setting_title from class c inner join setting s \n"
                    + "on c.term_id = s.setting_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Classes c = new Classes();
                c.setTermId(rs.getInt("term_id"));
                c.setTermName(rs.getString("setting_title"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Classes> getComboList() {
        ArrayList<Classes> list = new ArrayList<>();
        try {
            String sql = "select distinct c.combo_id, p.title from class c inner join package p on \n"
                    + "c.combo_id = p.package_id ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Classes c = new Classes();
                c.setComboId(rs.getInt("combo_id"));
                c.setComboName(rs.getString("title"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Classes> getClassList(int index) {
        ArrayList<Classes> list = new ArrayList<>();
        try {
            String sql = "select c.class_id, c.class_code, s.setting_title, u.full_name, c.status from class c \n"
                    + "inner join user u on c.trainer_id = u.user_id \n"
                    + "inner join setting s on c.term_id = s.setting_id limit ?,4";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 4);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Classes c = new Classes();
                c.setClassId(rs.getInt(1));
                c.setClassCode(rs.getString(2));
                c.setTermName(rs.getString(3));
                c.setNameTrainer(rs.getString(4));
                c.setStatus(rs.getInt(5));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int totalPage() {
        int total = 0;
        try {
            String sql = "select count(*) from class c \n"
                    + "inner join user u on c.trainer_id = u.user_id \n"
                    + "inner join setting s on c.term_id = s.setting_id;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public ArrayList<Classes> filter(Integer term, Integer trainerId, Integer status, String txtSearch) {
        String sql = "select c.class_id, c.class_code, s.setting_title, u.full_name, c.status from class c \n"
                + "inner join user u on c.trainer_id = u.user_id \n"
                + "inner join setting s on c.term_id = s.setting_id\n"
                + "where (1=1)\n";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (term != null) {
            count++;
            sql += "and c.term_id = ?\n";
            params.put(count, term);
        }
        if (trainerId != null) {
            count++;
            sql += "and c.trainer_id = ?\n";
            params.put(count, trainerId);
        }
        if (status != null) {
            count++;
            sql += "and c.status = ?\n";
            params.put(count, status);
        }
        if (txtSearch != null) {
            count++;
            sql += "and c.class_code like '%' ? '%'";
            params.put(count, txtSearch);
        }

        ArrayList<Classes> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Classes c = new Classes();
                c.setClassId(rs.getInt(1));
                c.setClassCode(rs.getString(2));
                c.setTermName(rs.getString(3));
                c.setNameTrainer(rs.getString(4));
                c.setStatus(rs.getInt(5));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void insertClass(Classes entity) {
        try {
            String sql = "insert into class (class_code, combo_id, trainer_id,term_id, status, description) \n"
                    + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getClassCode());
            stm.setInt(2, entity.getComboId());
            stm.setInt(3, entity.getTrainerId());
            stm.setInt(4, entity.getTermId());
            stm.setInt(5, entity.getStatus());
            stm.setString(6, entity.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
