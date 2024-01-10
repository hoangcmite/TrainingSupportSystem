/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.sun.webkit.LoadListenerClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Subject;
import Model.Account;
import Model.Classes;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class SubjectDBContext extends DBContext1 {

    public ArrayList<Subject> getStatusList() {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select distinct status from subject";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Subject getSubjectList(Subject entity) {
        try {
            String sql = "select s.subject_id, s.subject_code, s.subject_name, u.full_name, u2.full_name, \n"
                    + "s.status, s.manager_id,s.expect_id, s.body FROM subject s inner join \n"
                    + "user u on u.user_id = s.manager_id inner join user u2 on u2.user_id = s.expect_id \n"
                    + "where s.subject_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSubjectId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setSubjectName(rs.getString(3));
                s.setManagerName(rs.getString(4));
                s.setExpertName(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                s.setManagerId(rs.getInt(7));
                s.setExpertId(rs.getInt(8));
                s.setBody(rs.getString(9));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
    public Subject getSubjectById(int id) {
        try {
            String sql = "select s.subject_id, s.subject_code, s.subject_name, u.full_name, u2.full_name, \n"
                    + "s.status, s.manager_id,s.expect_id, s.body FROM subject s inner join \n"
                    + "user u on u.user_id = s.manager_id inner join user u2 on u2.user_id = s.expect_id \n"
                    + "where s.subject_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setSubjectName(rs.getString(3));
                s.setManagerName(rs.getString(4));
                s.setExpertName(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                s.setManagerId(rs.getInt(7));
                s.setExpertId(rs.getInt(8));
                s.setBody(rs.getString(9));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
    //sô trang cua subject list
    public int totalPage() {
        int total = 0;
        try {
            String sql = "select count(*) from subject";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    //phân trang cho subject list
    public ArrayList<Subject> pagingSubject(int index) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select s.subject_id, s.subject_code, s.subject_name, u.full_name, u2.full_name, \n"
                    + "s.status, s.manager_id,s.expect_id FROM subject s inner join \n"
                    + "user u on u.user_id = s.manager_id inner join user u2 on u2.user_id = s.expect_id \n"
                    + "order by s.subject_id asc limit ?,5";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setSubjectName(rs.getString(3));
                s.setManagerName(rs.getString(4));
                s.setExpertName(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                s.setManagerId(rs.getInt(7));
                s.setExpertId(rs.getInt(8));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;

    }

    public ArrayList<Subject> Filter(Integer managerId, Integer expertId, Integer status, String txtSearch) {
        String sql = "select s.subject_id, s.subject_code, s.subject_name, u.full_name, u2.full_name, \n"
                + "s.status, s.manager_id,s.expect_id FROM subject s inner join \n"
                + "user u on u.user_id = s.manager_id inner join user u2 on u2.user_id = s.expect_id \n"
                + "where (1=1)\n";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (managerId != null) {
            count++;
            sql += "and s.manager_id = ?\n";
            params.put(count, managerId);
        }
        if (expertId != null) {
            count++;
            sql += "and s.expect_id = ?\n";
            params.put(count, expertId);
        }
        if (status != null) {
            count++;
            sql += "and s.status = ?\n";
            params.put(count, status);
        }
        if (txtSearch != null) {
            count++;
            sql += "and s.subject_code like '%' ? '%'";
            params.put(count, txtSearch);
        }

        ArrayList<Subject> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setSubjectName(rs.getString(3));
                s.setManagerName(rs.getString(4));
                s.setExpertName(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                s.setManagerId(rs.getInt(7));
                s.setExpertId(rs.getInt(8));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void updateSubjectDetails(Subject entity) {
        try {
            String sql = "update subject\n"
                    + "set subject_code= ?, subject_name= ?, manager_id = ?, expect_id = ?, status = ?,\n"
                    + "body = ? where subject_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getSubjectCode());
            stm.setString(2, entity.getSubjectName());
            stm.setInt(3, entity.getManagerId());
            stm.setInt(4, entity.getExpertId());
            stm.setBoolean(5, entity.getStatus());
            stm.setString(6, entity.getBody());
            stm.setInt(7, entity.getSubjectId());
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void main(String[] args) {
//        SubjectDBContext user = new SubjectDBContext();
//        Subject s = new Subject(3, "IoT102", "internet of things", false, "tech in life", 2, 2);
//        user.updateSubjectDetails(s);
//    }
}
