/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

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
import Model.SubjectSetting;
import Model.Account;

/**
 *
 * @author dell
 */
public class SubjectSettingDBContext extends DBContext1 {

    public ArrayList<SubjectSetting> getTitleList() {
        ArrayList<SubjectSetting> list = new ArrayList<>();
        try {
            String sql = "select distinct setting_title from subject_setting";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SubjectSetting s = new SubjectSetting();
                s.setSettingTitle(rs.getString("setting_title"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<SubjectSetting> getSubjectSettingList(int index) {
        ArrayList<SubjectSetting> list = new ArrayList<>();
        try {
            String sql = "select ss.setting_id, s.subject_code, s1.setting_title, ss.display_order,\n"
                    + "ss.setting_title, ss.status\n"
                    + "from subject_setting ss inner join subject s \n"
                    + "on ss.subject_id = s.subject_id inner join \n"
                    + "setting s1 on s1.setting_id = ss.type_id limit ?,4";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 4);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectSetting s = new SubjectSetting();
                s.setSettingId(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setSettingTitle(rs.getString(3));
                s.setDisplayOrder(rs.getString(4));
                s.setSettingTitle2(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                list.add(s);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int totalPage() {
        int total = 0;
        try {
            String sql = "select count(*)\n"
                    + "from subject_setting ss inner join subject s \n"
                    + "on ss.subject_id = s.subject_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public ArrayList<SubjectSetting> filter(Integer code, Integer status, String txtSearch) {
        String sql = "select ss.setting_id, s.subject_code, s1.setting_title, ss.display_order,\n"
                + "ss.setting_title, ss.status\n"
                + "from subject_setting ss inner join subject s \n"
                + "on ss.subject_id = s.subject_id inner join \n"
                + "setting s1 on s1.setting_id = ss.type_id where (1=1)\n";
        Integer count = 0;
        HashMap<Integer, Object> params = new HashMap<>();
        if (code != null) {
            count++;
            sql += "and s.subject_id = ?\n";
            params.put(count, code);
        }

        if (status != null) {
            count++;
            sql += "and ss.status = ?\n";
            params.put(count, status);
        }
        if (txtSearch != null) {
            count++;
            sql += "and ss.setting_title like '%' ? '%'";
            params.put(count, txtSearch);
        }

        ArrayList<SubjectSetting> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectSetting s = new SubjectSetting();
                s.setSettingId(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setSettingTitle(rs.getString(3));
                s.setDisplayOrder(rs.getString(4));
                s.setSettingTitle2(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public SubjectSetting getSubjectSettingList(SubjectSetting entity) {
        try {
            String sql = "select ss.setting_id, ss.setting_title, ss.setting_value, ss.display_order,\n"
                    + "ss.description, ss.status, s.subject_code \n"
                    + "from subject_setting ss inner join subject s \n"
                    + "on ss.subject_id = s.subject_id where ss.setting_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSettingId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectSetting s = new SubjectSetting();
                s.setSettingId(rs.getInt(1));
                s.setSettingTitle2(rs.getString(2));
                s.setSettingValue(rs.getString(3));
                s.setDisplayOrder(rs.getString(4));
                s.setDescription(rs.getString(5));
                s.setStatus(rs.getBoolean(6));
                s.setSubjectCode(rs.getString(7));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void updateSubjectSettingDetails(SubjectSetting entity) {
        try {
            String sql = "update subject_setting\n"
                    + "set setting_title = ?, setting_value = ?, status = ?, description = ?\n"
                    + "where setting_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getSettingTitle2());
            stm.setString(2, entity.getSettingValue());
            stm.setBoolean(3, entity.getStatus());
            stm.setString(4, entity.getDescription());
            stm.setInt(5, entity.getSettingId());
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<SubjectSetting> getTypeList() {
        ArrayList<SubjectSetting> list = new ArrayList<>();
        try {
            String sql = "select distinct ss.type_id, s.setting_title from subject_setting ss inner join setting s \n"
                    + "on ss.type_id = s.setting_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SubjectSetting s = new SubjectSetting();
                s.setType_id(rs.getInt("type_id"));
                s.setTypeName(rs.getString("setting_title"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<SubjectSetting> getSubjectList() {
        ArrayList<SubjectSetting> list = new ArrayList<>();
        try {
            String sql = "select subject_id, subject_code from subject";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SubjectSetting s = new SubjectSetting();
                s.setSubjectId(rs.getInt("subject_id"));
                s.setSubjectCode(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertSubjectSetting(SubjectSetting entity) {
        try {
            String sql = "insert into subject_setting (type_id, setting_title, setting_value, display_order, status, description, subject_id)\n"
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getType_id());
            stm.setString(2, entity.getSettingTitle());
            stm.setString(3, entity.getSettingValue());
            stm.setString(4, entity.getDisplayOrder());
            stm.setBoolean(5, entity.getStatus());
            stm.setString(6, entity.getDescription());
            stm.setInt(7, entity.getSubjectId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //public static void main(String[] args) {
    //  SubjectSettingDBContext s = new SubjectSettingDBContext();
    // SubjectSetting ss = new SubjectSetting();
//        ArrayList<SubjectSetting> ss = s.getSubjectSettingList(1);
//            for (SubjectSetting s1 : ss) {
//                System.out.println(s1.toString());
//            }
    //}
}
