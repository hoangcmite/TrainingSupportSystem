/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.ClassList;
import Model.ClassSetting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBContext;
import static utils.DBContext.getConnection;

/**
 *
 * @author Admin
 */
public class ClassSettingDao extends DBContext<ClassSetting> {

    Connection connection = getConnection();

    public ArrayList<ClassList> listClass() {
        ArrayList<ClassList> listClass = new ArrayList<>();
        String sql = "select class_id, class_code from class;";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassList cl = new ClassList();
                cl.setClass_id(rs.getInt("class_id"));
                cl.setClass_code(rs.getString("class_code"));
                listClass.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClass;
    }

    public ArrayList<ClassSetting> listType() {
        ArrayList<ClassSetting> listType = new ArrayList<>();
        String sql = "select distinct(cs.type_id), s.setting_title from class_setting cs inner join setting s on cs.type_id = s.setting_id";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassSetting cs = new ClassSetting();
                cs.setType_id(rs.getInt("type_id"));
                cs.setClassSetting_type(rs.getString("setting_title"));
                listType.add(cs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listType;
    }

    public ArrayList<ClassSetting> advancedSearchClassSetting(int sort, int typeId, int status, String title_value, int pageIndex, int pageSize, int classId) {
        ArrayList<ClassSetting> list = new ArrayList<>();
        String sql = "select * from (select cs.setting_id, cs.type_id, s.setting_title as setting_type, cs.setting_title, cs.setting_value, cs.display_order, cs.status, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by s.setting_title asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by s.setting_title desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_title asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_title desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_value asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_value desc) as row_index ";
                    break;
                case 8:
                    sql += " ROW_NUMBER() OVER(order by cs.display_order asc) as row_index ";
                    break;
                case 9:
                    sql += " ROW_NUMBER() OVER(order by cs.display_order desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_id asc) as row_index ";
                    break;
            }

            sql += " from class_setting cs inner join setting s on cs.type_id = s.setting_id where (1=1) and cs.class_id = " + classId + " ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (typeId != -1) {
                sql += " and cs.type_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = typeId;
                parameters.put(paramIndex, param);
            }
            if (status != -1) {
                sql += "and cs.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (title_value != null) {
                sql += " and (cs.setting_title like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = title_value;
                parameters.put(paramIndex, param);
            }
            if (title_value != null) {
                sql += " or cs.setting_value like '%' ? '%') ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = title_value;
                parameters.put(paramIndex, param);
            }

            sql += ") as tbl where row_index >= ( ? - 1 ) * ? + 1 and row_index <= ? * ?";

            // dấu hỏi số 1 của where row_index >= ....
            paramIndex++;
            Object[] param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageIndex;
            parameters.put(paramIndex, param);
            // dấu hỏi số 2 của where row_index >= ....
            paramIndex++;
            param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageSize;
            parameters.put(paramIndex, param);
            // dấu hỏi số 3 của where row_index >= ....
            paramIndex++;
            param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageIndex;
            parameters.put(paramIndex, param);
            // dấu hỏi số 4 của where row_index >= ....
            paramIndex++;
            param = new Object[2];
            param[0] = Integer.class.getTypeName();
            param[1] = pageSize;
            parameters.put(paramIndex, param);

            PreparedStatement psm = connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object[]> entry : parameters.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(Integer.class.getName())) {
                    psm.setInt(index, Integer.parseInt(value[1].toString()));
                } else if (type.equals(String.class.getName())) {
                    psm.setString(index, value[1].toString());
                } else if (type.equals(Boolean.class.getName())) {
                    psm.setBoolean(index, Boolean.parseBoolean(value[1].toString()));
                }
            }
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassSetting cs = new ClassSetting();
                cs.setClassSetting_id(rs.getInt("setting_id"));
                cs.setType_id(rs.getInt("type_id"));
                cs.setClassSetting_type(rs.getString("setting_type"));
                cs.setClassSetting_title(rs.getString("setting_title"));
                cs.setClassSetting_value(rs.getString("setting_value"));
                cs.setDisplay_order(rs.getInt("display_order"));
                cs.setStatus(rs.getInt("status"));
                list.add(cs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int countAdvancedSearchClassSetting(int sort, int typeId, int status, String title_value, int classId) {
        String sql = "select COUNT(*) as Total from (select cs.setting_id, cs.type_id, s.setting_title as setting_type, cs.setting_title, cs.setting_value, cs.display_order, cs.status, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by s.setting_title asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by s.setting_title desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_title asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_title desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_value asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_value desc) as row_index ";
                    break;
                case 8:
                    sql += " ROW_NUMBER() OVER(order by cs.display_order asc) as row_index ";
                    break;
                case 9:
                    sql += " ROW_NUMBER() OVER(order by cs.display_order desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by cs.setting_id asc) as row_index ";
                    break;
            }

            sql += " from class_setting cs inner join setting s on cs.type_id = s.setting_id where (1=1) and cs.class_id = " + classId + " ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;

            if (typeId != -1) {
                sql += "and cs.type_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = typeId;
                parameters.put(paramIndex, param);
            }
            if (status != -1) {
                sql += "and cs.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (title_value != null) {
                sql += " and (cs.setting_title like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = title_value;
                parameters.put(paramIndex, param);
            }
            if (title_value != null) {
                sql += " or cs.setting_value like '%' ? '%') ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = title_value;
                parameters.put(paramIndex, param);
            }

            sql += ") as tbl";

            PreparedStatement psm = connection.prepareStatement(sql);

            for (Map.Entry<Integer, Object[]> entry : parameters.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(Integer.class.getName())) {
                    psm.setInt(index, Integer.parseInt(value[1].toString()));
                } else if (type.equals(String.class.getName())) {
                    psm.setString(index, value[1].toString());
                } else if (type.equals(Boolean.class.getName())) {
                    psm.setBoolean(index, Boolean.parseBoolean(value[1].toString()));
                }
            }
            ResultSet rs = psm.executeQuery();

            while (rs.next()) {
                return rs.getInt("Total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void active(int id) {
        String sqlString = "UPDATE class_setting SET `status` = '1' WHERE (`setting_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inactive(int id) {
        String sqlString = "UPDATE class_setting SET `status` = '0' WHERE (`setting_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClassSetting getClassSettingById(int id) {

        String sql = "select cs.setting_id, cs.type_id, s.setting_title as setting_type, cs.setting_title, cs.setting_value, cs.display_order, cs.class_id, cs.status, cs.description \n"
                + "from class_setting cs inner join setting s on cs.type_id = s.setting_id where cs.setting_id = ?";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            psm.setInt(1, id);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassSetting cs = new ClassSetting();
                cs.setClassSetting_id(rs.getInt("setting_id"));
                cs.setType_id(rs.getInt("type_id"));
                cs.setClassSetting_type(rs.getString("setting_type"));
                cs.setClassSetting_title(rs.getString("setting_title"));
                cs.setClassSetting_value(rs.getString("setting_value"));
                cs.setDisplay_order(rs.getInt("display_order"));
                cs.setClass_id(rs.getInt("class_id"));
                cs.setStatus(rs.getInt("status"));
                cs.setDescription(rs.getString("description"));
                return cs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int deleteClassSetting(int id) {
        int n = 0;
        String sql = "DELETE FROM class_setting WHERE setting_id = " + id + "";
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateClassSetting(int id, ClassSetting cs) {
        String sql_update = "UPDATE `class_setting`\n"
                + "SET\n"
                + "`type_id` = ?,\n"
                + "`setting_title` = ?,\n"
                + "`setting_value` = ?,\n"
                + "`display_order` = ?,\n"
                + "`class_id` = ?,\n"
                + "`status` = ?,\n"
                + "`description` = ?\n"
                + "WHERE `setting_id` = " + id + "";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setInt(1, cs.getType_id());
            stm.setString(2, cs.getClassSetting_title());
            stm.setString(3, cs.getClassSetting_value());
            stm.setInt(4, cs.getDisplay_order());
            stm.setInt(5, cs.getClass_id());
            stm.setInt(6, cs.getStatus());
            stm.setString(7, cs.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addClassSetting(ClassSetting s) {
        String sql_update = "INSERT INTO `class_setting`\n"
                + "(\n"
                + "`type_id`,\n"
                + "`setting_title`,\n"
                + "`setting_value`,\n"
                + "`display_order`,\n"
                + "`class_id`,\n"
                + "`status`,\n"
                + "`description`)\n"
                + "VALUES\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setInt(1, s.getType_id());
            stm.setString(2, s.getClassSetting_title());
            stm.setString(3, s.getClassSetting_value());
            stm.setInt(4, s.getDisplay_order());
            stm.setInt(5, s.getClass_id());
            stm.setInt(6, s.getStatus());
            stm.setString(7, s.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassSettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ClassSetting> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClassSetting get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(ClassSetting model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ClassSetting model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
