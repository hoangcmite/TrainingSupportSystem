/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Setting;
import Model.User;
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
public class UserListDao extends DBContext<User>{

    Connection connection = getConnection();
    
    public ArrayList<User> advancedSearchUser(int sort, int status, String name_email, int pageIndex, int pageSize) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from (select u.user_id, u.full_name, u.email, u.mobile, u.avatar_url, u.status, u.createtime, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by u.user_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by u.user_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by u.full_name asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by u.full_name desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by u.email asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by u.email desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by u.mobile asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by u.mobile desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by u.user_id asc) as row_index ";
                    break;
            }

            sql += " from user u where (1=1) ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (status != -1) {
                sql += "and u.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (name_email != null) {
                sql += " and (u.full_name like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = name_email;
                parameters.put(paramIndex, param);
            }
            if (name_email != null) {
                sql += " or u.email like '%' ? '%') ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = name_email;
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
                User us = new User();
                us.setUserId(rs.getInt("user_id"));
                us.setFullName(rs.getString("full_name"));
                us.setEmail(rs.getString("email"));
                us.setMobile(rs.getString("mobile"));
                us.setAvatar_url(rs.getString("avatar_url"));
                us.setStatus(rs.getBoolean("status"));
                us.setCreateTime(rs.getString("createtime"));
                list.add(us);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserListDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int countAdvancedSearchUser(int sort, int status, String name_email) {
        String sql = "select COUNT(*) as Total from (select u.user_id, u.full_name, u.email, u.mobile, u.avatar_url, u.status, u.createtime, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by u.user_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by u.user_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by u.full_name asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by u.full_name desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by u.email asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by u.email desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by u.mobile asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by u.mobile desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by u.user_id asc) as row_index ";
                    break;
            }

            sql += " from user u where (1=1) ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (status != -1) {
                sql += "and u.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (name_email != null) {
                sql += " and (u.full_name like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = name_email;
                parameters.put(paramIndex, param);
            }
            if (name_email != null) {
                sql += " or u.email like '%' ? '%') ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = name_email;
                parameters.put(paramIndex, param);
            }

            sql += ") as tbl ";

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
            Logger.getLogger(UserListDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void active(int id) {
        String sqlString = "UPDATE `user` SET `status` = '1' WHERE (`user_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserListDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inactive(int id) {
        String sqlString = "UPDATE `user` SET `status` = '0' WHERE (`user_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserListDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE user_id = " + id;
        try {
            Statement state = connection.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserListDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
