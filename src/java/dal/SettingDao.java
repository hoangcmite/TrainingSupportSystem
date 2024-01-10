/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Setting;
import java.sql.Statement;
import utils.DBContext;
import static utils.DBContext.getConnection;

/**
 *
 * @author Admin
 */
public class SettingDao extends DBContext<Setting> {

    Connection connection = getConnection();

    public ArrayList<Setting> listType() {
        ArrayList<Setting> listType = new ArrayList<>();
        String sql = "select setting_id, setting_title from setting where type_id is null";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setType_id(rs.getInt("setting_id"));
                s.setSetting_type(rs.getString("setting_title"));
                listType.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listType;
    }

    public ArrayList<Setting> advancedSearchSetting(int sort, int typeId, int status, String title, int pageIndex, int pageSize) {
        ArrayList<Setting> list = new ArrayList<>();
        String sql = "select * from (select a.setting_id, a.type_id, b.setting_title as setting_type, a.setting_title, a.setting_value, a.display_order, a.status, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by a.setting_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by a.setting_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by b.setting_title asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by b.setting_title desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by a.setting_title asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by a.setting_title desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by a.setting_value asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by a.setting_value desc) as row_index ";
                    break;
                case 8:
                    sql += " ROW_NUMBER() OVER(order by a.display_order asc) as row_index ";
                    break;
                case 9:
                    sql += " ROW_NUMBER() OVER(order by a.display_order desc) as row_index ";
                    break;
                case 10:
                    sql += " ROW_NUMBER() OVER(order by a.status asc) as row_index ";
                    break;
                case 11:
                    sql += " ROW_NUMBER() OVER(order by a.status desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by a.setting_id asc) as row_index ";
                    break;
            }

            sql += " from setting a inner join setting b on a.type_id = b.setting_id and (1=1) ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (typeId != -1) {
                sql += "and a.type_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = typeId;
                parameters.put(paramIndex, param);
            }
            if (status != -1) {
                sql += "and a.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (title != null) {
                sql += " and a.setting_title like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = title;
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
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_type(rs.getString("setting_type"));
                s.setSetting_title(rs.getString("setting_title"));
                s.setSetting_value(rs.getString("setting_value"));
                s.setDisplay_order(rs.getInt("display_order"));
                s.setStatus(rs.getInt("status"));
                list.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int countAdvancedSearchSetting(int sort, int typeId, int status, String title) {
        String sql = "select COUNT(*) as Total from (select a.setting_id, a.type_id, b.setting_title as setting_type, a.setting_title, a.setting_value, a.display_order, a.status, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by a.setting_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by a.setting_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by b.setting_title asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by b.setting_title desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by a.setting_title asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by a.setting_title desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by a.setting_value asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by a.setting_value desc) as row_index ";
                    break;
                case 8:
                    sql += " ROW_NUMBER() OVER(order by a.display_order asc) as row_index ";
                    break;
                case 9:
                    sql += " ROW_NUMBER() OVER(order by a.display_order desc) as row_index ";
                    break;
                case 10:
                    sql += " ROW_NUMBER() OVER(order by a.status asc) as row_index ";
                    break;
                case 11:
                    sql += " ROW_NUMBER() OVER(order by a.status desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by a.setting_id asc) as row_index ";
                    break;
            }

            sql += " from setting a inner join setting b on a.type_id = b.setting_id and (1=1) ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (typeId != -1) {
                sql += "and a.type_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = typeId;
                parameters.put(paramIndex, param);
            }
            if (status != -1) {
                sql += "and a.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (title != null) {
                sql += "and a.setting_type like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = title;
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
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void active(int id) {
        String sqlString = "UPDATE setting SET `status` = '1' WHERE (`setting_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inactive(int id) {
        String sqlString = "UPDATE `setting` SET `status` = '0' WHERE (`setting_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Setting getSettingById(int id) {

        String sql = "select a.setting_id, a.type_id, b.setting_title as setting_type, a.setting_title, a.setting_value, a.display_order, a.status, a.description\n"
                + "from setting a inner join setting b on a.type_id = b.setting_id where a.setting_id = ?";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            psm.setInt(1, id);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSetting_id(rs.getInt("setting_id"));
                s.setType_id(rs.getInt("type_id"));
                s.setSetting_type(rs.getString("setting_type"));
                s.setSetting_title(rs.getString("setting_title"));
                s.setSetting_value(rs.getString("setting_value"));
                s.setDisplay_order(rs.getInt("display_order"));
                s.setStatus(rs.getInt("status"));
                s.setDescription(rs.getString("description"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int deleteSetting(int id) {
        int n = 0;
        String sql = "DELETE FROM setting WHERE setting_id = " + id + "";
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateSetting(int id, Setting s) {
        String sql_update = "UPDATE `setting`\n"
                + "SET\n"
                + "`type_id` = ?,\n"
                + "`setting_title` = ?,\n"
                + "`setting_value` = ?,\n"
                + "`display_order` = ?,\n"
                + "`status` = ?,\n"
                + "`description` = ?\n"
                + "WHERE `setting_id` = " + id + "";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setInt(1, s.getType_id());
            stm.setString(2, s.getSetting_title());
            stm.setString(3, s.getSetting_value());
            stm.setInt(4, s.getDisplay_order());
            stm.setInt(5, s.getStatus());
            stm.setString(6, s.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addSetting(Setting s) {
        String sql_update = "INSERT INTO `setting`\n"
                + "(\n"
                + "`type_id`,\n"
                + "`setting_title`,\n"
                + "`setting_value`,\n"
                + "`display_order`,\n"
                + "`status`,\n"
                + "`description`)\n"
                + "VALUES\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setInt(1, s.getType_id());
            stm.setString(2, s.getSetting_title());
            stm.setString(3, s.getSetting_value());
            stm.setInt(4, s.getDisplay_order());
            stm.setInt(5, s.getStatus());
            stm.setString(6, s.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Setting> listWebContactType() {
        ArrayList<Setting> listType = new ArrayList<>();
        String sql = "select setting_id, setting_title from setting where type_id =2";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setType_id(rs.getInt("setting_id"));
                s.setSetting_title(rs.getString("setting_title"));
                listType.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listType;
    }

    @Override
    public ArrayList<Setting> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Setting get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Setting model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Setting model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
