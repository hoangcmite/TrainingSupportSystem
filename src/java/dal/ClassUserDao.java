/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.ClassSetting;
import Model.ClassUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class ClassUserDao extends DBContext<ClassUser> {

    Connection connection = getConnection();

    public ArrayList<ClassUser> listClassByRole(int roleId) {
        ArrayList<ClassUser> listClass = new ArrayList<>();
        String sql = "select distinct(cu.class_id), c.class_code from class_user cu inner join class c on cu.class_id = c.class_id where c.supporter_id = " + roleId + " ";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassUser cu = new ClassUser();
                cu.setClass_id(rs.getInt("class_id"));
                cu.setClass_code(rs.getString("class_code"));
                listClass.add(cu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listClass;
    }

    public ArrayList<ClassUser> listTraineeOfClass(int sort, int roleId, int classId, int status, String nameU, int pageIndex, int pageSize) {
        ArrayList<ClassUser> list = new ArrayList<>();
        String sql = "select * from (select cu.class_id, cu.user_id, u.full_name, u.email, cu.status, cu.note, cu.dropout_date, cu.ongoing_eval, cu.final_eval, cu.topic_eval, c.trainer_id, c.supporter_id, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by u.full_name asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by u.full_name desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by u.email asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by u.email desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by cu.status asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by cu.status desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by cu.dropout_date asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by cu.dropout_date desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by u.full_name asc) as row_index ";
                    break;
            }

            sql += " from class_user cu inner join class c on cu.class_id = c.class_id inner join user u on cu.user_id = u.user_id where (1=1) and c.supporter_id = " + roleId + " and cu.class_id = " + classId;

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (status != -1) {
                sql += " and cu.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (nameU != null) {
                sql += " and (u.full_name like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = nameU;
                parameters.put(paramIndex, param);
            }
            if (nameU != null) {
                sql += " or u.email like '%' ? '%' ) ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = nameU;
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
                } else if (type.equals(Date.class.getName())) {
                    psm.setDate(index, Date.valueOf(value[1].toString()));
                }
            }
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassUser cu = new ClassUser();
                cu.setClass_id(rs.getInt("class_id"));
                cu.setUser_id(rs.getInt("user_id"));
                cu.setFull_name(rs.getString("full_name"));
                cu.setUser_email(rs.getString("email"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setNote(rs.getString("note"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getFloat("ongoing_eval"));
                cu.setFinal_eval(rs.getFloat("final_eval"));
                cu.setTopic_eval(rs.getFloat("topic_eval"));
                cu.setTrainer_id(rs.getInt("trainer_id"));
                cu.setSupporter_id(rs.getInt("supporter_id"));
                list.add(cu);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int countListTraineeOfClass(int sort, int roleId, int classId, int status, String nameU) {
        String sql = "select COUNT(*) as Total from (select cu.class_id, cu.user_id, u.full_name, u.email, cu.status, cu.note,cu.dropout_date, cu.ongoing_eval, cu.final_eval, cu.topic_eval, c.trainer_id, c.supporter_id, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by u.full_name asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by u.full_name desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by u.email asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by u.email desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by cu.status asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by cu.status desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by cu.dropout_date asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by cu.dropout_date desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by u.full_name asc) as row_index ";
                    break;
            }

            sql += " from class_user cu inner join class c on cu.class_id = c.class_id inner join user u on cu.user_id = u.user_id where (1=1) and c.supporter_id = " + roleId + " and cu.class_id = " + classId;

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (status != -1) {
                sql += " and cu.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (nameU != null) {
                sql += " and u.full_name like '%' ? '%' ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = nameU;
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
                } else if (type.equals(Date.class.getName())) {
                    psm.setDate(index, Date.valueOf(value[1].toString()));
                }
            }
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public void active(int uID, int cID) {
        String sqlString = "UPDATE `class_user` SET `status` = b'1', `dropout_date` = null WHERE `class_id` = " + cID + " AND `user_id` = " + uID;
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inactive(int uID, int cID) {
        LocalDate date = LocalDate.now();
        String sqlString = "UPDATE `class_user` SET `status` = b'0', `dropout_date` = '" + date + "' WHERE `class_id` = " + cID + " AND `user_id` = " + uID;
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClassUser getTraineeDetail(int cID, int uID) {
        String sql = "select cu.class_id, cu.user_id, u.full_name, u.email, cu.status, cu.note,\n"
                + "cu.dropout_date, cu.ongoing_eval, cu.final_eval, cu.topic_eval, c.trainer_id, c.supporter_id\n"
                + "from class_user cu inner join class c on cu.class_id = c.class_id inner join user u on cu.user_id = u.user_id where cu.user_id = ? and cu.class_id = ?";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            psm.setInt(1, uID);
            psm.setInt(2, cID);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                ClassUser cu = new ClassUser();
                cu.setClass_id(rs.getInt("class_id"));
                cu.setUser_id(rs.getInt("user_id"));
                cu.setFull_name(rs.getString("full_name"));
                cu.setUser_email(rs.getString("email"));
                cu.setStatus(rs.getBoolean("status"));
                cu.setNote(rs.getString("note"));
                cu.setDropout_date(rs.getDate("dropout_date"));
                cu.setOngoing_eval(rs.getFloat("ongoing_eval"));
                cu.setFinal_eval(rs.getFloat("final_eval"));
                cu.setTopic_eval(rs.getFloat("topic_eval"));
                cu.setTrainer_id(rs.getInt("trainer_id"));
                cu.setSupporter_id(rs.getInt("supporter_id"));
                return cu;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateTrainee(int cID, int uID, ClassUser cu) {
        LocalDate date2 = LocalDate.now();
        String sql_update = "UPDATE `class_user`\n"
                + "SET\n"
                + "`status` = ?,\n"
                + "`note` = ?,\n"
                + "`dropout_date` = ?\n"
                + "WHERE `class_id` = ? AND `user_id` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setBoolean(1, cu.isStatus());
            stm.setString(2, cu.getNote());
            if (cu.isStatus() == true) {
                stm.setDate(3, null);
            } else {
                stm.setDate(3, cu.getDropout_date());
            }
            stm.setInt(4, cID);
            stm.setInt(5, uID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ClassUser> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClassUser get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(ClassUser model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ClassUser model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
