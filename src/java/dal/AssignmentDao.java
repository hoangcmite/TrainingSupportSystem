/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Assignment;
import Model.Setting;
import static utils.DBContext.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBContext;

/**
 *
 * @author Admin
 */
public class AssignmentDao extends DBContext<Assignment> {

    Connection connection = getConnection();

    public static void main(String[] args) {
        AssignmentDao d = new AssignmentDao();
        d.editAssignment("10", "1", "1", "1", "1", "1", "0", "1");
    }
    public void addAssignment(String title, String body, String status, String isTeamWork, String isOngoing, String subject, String eval) {
        String sql = "INSERT INTO `MyDBG3`.`assignment` (`subject_id`, `title`, `ass_boby`, `eval_weight`, `is_team_work`, `is_ongoing`, `status`) "
                + "VALUES ('" + subject + "', '" + title + "', '" + body + "', '" + eval + "', b'" + isTeamWork + "', b'" + isOngoing + "', b'" + status + "');";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            psm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editAssignment(String id,String title, String body, String status, String isTeamWork, String isOngoing, String subject, String eval) {
        String sql = "UPDATE `MyDBG3`.`assignment` "
                + " SET `subject_id` = '" + subject + "', `title` = '" + title + "', `ass_boby` = '" + body + "', `eval_weight` = '" + eval + "',`is_team_work` = b'" + isTeamWork + "', `is_ongoing` = b'" + isOngoing + "', `status` = b'" + status + "' WHERE (`ass_id` = '" + id + "');";
        try {
            System.out.println(sql);
            PreparedStatement psm = connection.prepareStatement(sql);
            psm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Assignment> listSubject() {
        ArrayList<Assignment> listType = new ArrayList<>();
        String sql = "select sb.subject_id, sb.subject_code from subject sb ";
        try {
            PreparedStatement psm = connection.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            while (rs.next()) {
                Assignment ass = new Assignment();
                ass.setSubject_id(rs.getInt("subject_id"));
                ass.setSubject_code(rs.getString("subject_code"));
                listType.add(ass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listType;
    }

    public ArrayList<Assignment> advancedSearchAss(int sort, int subjectId, int status, String title, int pageIndex, int pageSize) {
        ArrayList<Assignment> list = new ArrayList<>();
        String sql = "select * from (select a.ass_id, a.subject_id, sb.subject_code, a.title, a.eval_weight, a.is_team_work, a.is_ongoing, a.status, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by a.ass_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by a.ass_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by sb.subject_code asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by sb.subject_code desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by a.title asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by a.title desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by a.eval_weight asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by a.eval_weight desc) as row_index ";
                    break;
                case 8:
                    sql += " ROW_NUMBER() OVER(order by a.is_team_work asc) as row_index ";
                    break;
                case 9:
                    sql += " ROW_NUMBER() OVER(order by a.is_team_work desc) as row_index ";
                    break;
                case 10:
                    sql += " ROW_NUMBER() OVER(order by a.is_ongoing asc) as row_index ";
                    break;
                case 11:
                    sql += " ROW_NUMBER() OVER(order by a.is_ongoing desc) as row_index ";
                    break;
                case 12:
                    sql += " ROW_NUMBER() OVER(order by a.status asc) as row_index ";
                    break;
                case 13:
                    sql += " ROW_NUMBER() OVER(order by a.status desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by a.ass_id asc) as row_index ";
                    break;
            }

            sql += " from assignment a inner join subject sb on a.subject_id = sb.subject_id where (1=1) ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (subjectId != -1) {
                sql += "and a.subject_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = subjectId;
                parameters.put(paramIndex, param);
            }
//            String raw_status = status+"";
//            boolean status_filter = Boolean.parseBoolean(raw_status.trim());
            if (status != -1) {
                sql += "and a.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (title != null) {
                sql += " and a.title like '%' ? '%' ";
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
                Assignment ass = new Assignment();
                ass.setAss_id(rs.getInt("ass_id"));
                ass.setSubject_id(rs.getInt("subject_id"));
                ass.setSubject_code(rs.getString("subject_code"));
                ass.setAss_title(rs.getString("title"));
                ass.setEval_weight(rs.getInt("eval_weight"));
                ass.setTeam_work(rs.getBoolean("is_team_work"));
                ass.setOngoing(rs.getBoolean("is_ongoing"));
                ass.setStatus(rs.getBoolean("status"));
                list.add(ass);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int countAdvancedSearchAss(int sort, int subjectId, int status, String title) {
        String sql = "select COUNT(*) as Total from (select a.ass_id, a.subject_id, sb.subject_code, a.title, a.eval_weight, a.is_team_work, a.is_ongoing, a.status, ";
        try {
            switch (sort) {
                case 0:
                    sql += " ROW_NUMBER() OVER(order by a.ass_id asc) as row_index ";
                    break;
                case 1:
                    sql += " ROW_NUMBER() OVER(order by a.ass_id desc) as row_index ";
                    break;
                case 2:
                    sql += " ROW_NUMBER() OVER(order by sb.subject_code asc) as row_index ";
                    break;
                case 3:
                    sql += " ROW_NUMBER() OVER(order by sb.subject_code desc) as row_index ";
                    break;
                case 4:
                    sql += " ROW_NUMBER() OVER(order by a.title asc) as row_index ";
                    break;
                case 5:
                    sql += " ROW_NUMBER() OVER(order by a.title desc) as row_index ";
                    break;
                case 6:
                    sql += " ROW_NUMBER() OVER(order by a.eval_weight asc) as row_index ";
                    break;
                case 7:
                    sql += " ROW_NUMBER() OVER(order by a.eval_weight desc) as row_index ";
                    break;
                case 8:
                    sql += " ROW_NUMBER() OVER(order by a.is_team_work asc) as row_index ";
                    break;
                case 9:
                    sql += " ROW_NUMBER() OVER(order by a.is_team_work desc) as row_index ";
                    break;
                case 10:
                    sql += " ROW_NUMBER() OVER(order by a.is_ongoing asc) as row_index ";
                    break;
                case 11:
                    sql += " ROW_NUMBER() OVER(order by a.is_ongoing desc) as row_index ";
                    break;
                case 12:
                    sql += " ROW_NUMBER() OVER(order by a.status asc) as row_index ";
                    break;
                case 13:
                    sql += " ROW_NUMBER() OVER(order by a.status desc) as row_index ";
                    break;
                default:
                    sql += " ROW_NUMBER() OVER(order by a.ass_id asc) as row_index ";
                    break;
            }

            sql += " from assignment a inner join subject sb on a.subject_id = sb.subject_id where (1=1) ";

            HashMap<Integer, Object[]> parameters = new HashMap<>();
            int paramIndex = 0;
            if (subjectId != -1) {
                sql += "and a.subject_id = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = subjectId;
                parameters.put(paramIndex, param);
            }
//            String raw_status = status+"";
//            boolean status_filter = Boolean.parseBoolean(raw_status.trim());
            if (status != -1) {
                sql += "and a.status = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = status;
                parameters.put(paramIndex, param);
            }
            if (title != null) {
                sql += " and a.title like '%' ? '%' ";
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
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void active(int id) {
        String sqlString = "UPDATE `assignment` SET `status` = b'1' WHERE (`ass_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inactive(int id) {
        String sqlString = "UPDATE `assignment` SET `status` = b'0' WHERE (`ass_id` = '" + id + "');";
        try {
            PreparedStatement pre = connection.prepareStatement(sqlString);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Assignment> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Assignment get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Assignment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Assignment model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
