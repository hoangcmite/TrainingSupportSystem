/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import Model.Classes;
import Model.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.Assignment1;
import utils.DBContext;
import static utils.DBContext.getConnection;
import dal.EvalCriteria;
import dal.Subject;
import utils.SubjectDBContext;

/**
 *
 * @author Admin
 */
public class AdminDAO extends DBContext<UserAccount> {

    Connection connection = getConnection();

    public String getMilestoneIdbByUid(int uid) {
        String out = "";
        String out2 = "";
        try {
            String sql = "SELECT class_id FROM MyDBG3.class where trainer_id = " + uid;
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt(1);
                out += x + ",";
            }
            if (out.length() > 0) {
                out = "(" + out.substring(0, out.length() - 1) + ")";
            }
            System.out.println("out ;" + out);
            String sql2 = "SELECT milestone_id FROM MyDBG3.milestone  where class_id in  " + out;
            System.out.println(sql2);

            ps = connection.prepareStatement(sql2);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt(1);
                out2 += x + ",";
            }
            if (out2.length() > 0) {
                out2 = "(" + out2.substring(0, out2.length() - 1) + ")";
            }
            System.out.println("out2 ;" + out2);

        } catch (Exception e) {
            System.out.println(e);
        }

        return out2;
    }

    public static void main(String[] args) {
        AdminDAO a = new AdminDAO();
        System.out.println(a.getMilestoneIdbByUid(97));
        ArrayList<EvalCriteria> l = a.getAllEvalCriteriaByMilestone("", "", "", a.getMilestoneIdbByUid(97), "");
        for (EvalCriteria evalCriteria : l) {
            System.out.println(evalCriteria.getId() + " ");
        }
    }

    public ArrayList<Classes> getAllClass(int uid) {
        ArrayList<Classes> list = new ArrayList<>();
        try {
            String sql = "SELECT class_id,class_code FROM MyDBG3.class\n"
                    + "where trainer_id = " + uid;
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classes c = new Classes();
                c.setClassId(rs.getInt(1));
                c.setClassCode(rs.getString(2));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<EvalCriteria> getAllEvalCriteria(String status, String name, String assId) {
        ArrayList<EvalCriteria> list = new ArrayList<>();
        String statusCon = "";
        if (status == "") {
            statusCon = "";
        }
        if (status == "0") {
            statusCon = " e.`status` = 0 and";
        }
        if (status == "1") {
            statusCon = " e.`status` = 1 and";
        }
        try {
            String sql = "SELECT * FROM MyDBG3.eval_criteria e, MyDBG3.assignment a "
                    + "where " + statusCon + "  e.criteria_name like '%" + name + "%' and e.ass_id like '%" + assId + "%' and e.ass_id = a.ass_id;";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EvalCriteria criteria = new EvalCriteria(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getBoolean(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8), rs.getString(9));
                Assignment1 a = new Assignment1();
                a.setTitle(rs.getString("title"));
                a.setSubjectId(rs.getInt("subject_id"));
                a.setSubject(geSubjectByid(rs.getInt("subject_id")));
                criteria.setAssignment(a);
                list.add(criteria);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Subject geSubjectByid(int id) {
        try {
            String sql = "SELECT * FROM MyDBG3.subject where subject_id =  " + id;
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getInt("subject_id"));
                s.setCode(rs.getString("subject_code"));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<EvalCriteria> getAllEvalCriteriaByMilestone(String status, String name, String assId, String miId, String cid) {
        ArrayList<EvalCriteria> list = new ArrayList<>();
        String statusCon = "";
        if ("".equals(status)) {
            statusCon = "";
        }
        if ("0".equals(status)) {
            statusCon = " e.`status` = 0 and";
        }
        if ("1".equals(status)) {
            statusCon = " e.`status` = 1 and";
        }

        try {
            String sql = "SELECT * FROM MyDBG3.eval_criteria e, MyDBG3.assignment a, MyDBG3.milestone m "
                    + "where " + statusCon + "  e.criteria_name like '%" + name + "%' and e.ass_id like '%" + assId + "%' and e.ass_id = a.ass_id and m.milestone_id=e.milestone_id and m.class_id like '%" + cid + "%'";
            if (miId.trim().length() > 0) {
                sql += "  and  e.milestone_id in " + miId;
            } else {
                sql += "  and  e.milestone_id in (-1)";
            }
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EvalCriteria criteria = new EvalCriteria(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getBoolean(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8), rs.getString(9));
                Assignment1 a = new Assignment1();
                a.setTitle(rs.getString("title"));
                a.setSubjectId(rs.getInt("subject_id"));
                a.setSubject(geSubjectByid(rs.getInt("subject_id")));
                criteria.setAssignment(a);
                list.add(criteria);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Assignment1> getAllAssignment() {
        ArrayList<Assignment1> list = new ArrayList<>();
        try {

            String sql = "SELECT * FROM assignment;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Assignment1 a = new Assignment1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8));
                list.add(a);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public EvalCriteria getEvalCriteria(String eid) {
        try {
            String sql = "SELECT * FROM eval_criteria e, assignment a\n"
                    + "where e.criteria_id = " + eid + " and e.ass_id = a.ass_id";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EvalCriteria criteria = new EvalCriteria(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getBoolean(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8), rs.getString(9));
                Assignment1 a = new Assignment1();
                a.setTitle(rs.getString("title"));
                a.setSubjectId(rs.getInt("subject_id"));
                a.setSubject(geSubjectByid(rs.getInt("subject_id")));
                criteria.setAssignment(a);
                return criteria;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Assignment1 getAssignment(String aid) {
        try {
            String sql = "SELECT * FROM assignment a ,subject s \n"
                    + "where a.subject_id = s.subject_id and a.ass_id = " + aid;
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Assignment1 a = new Assignment1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8));
                Subject s = new Subject();
                s.setName(rs.getString("subject_name"));
                s.setCode(rs.getString("subject_code"));
                a.setSubject(s);
                return a;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void deleteEval(String eid) {
        try {
            String sql = "DELETE FROM `eval_criteria` WHERE (`criteria_id` = '" + eid + "');";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editEval(String id, String name, String assId, String evalWeight, String maxLoc, String isTeamEval, String status, String description) {
        try {
            String sql = "UPDATE `eval_criteria` SET `ass_id` = '" + assId + "', `criteria_name` = '" + name + "', `is_team_eval` = b'" + isTeamEval + "', `eval_weight` = '" + evalWeight + "', `max_loc` = '" + maxLoc + "', `status` = b'" + status + "', `description` = '" + description + "' WHERE (`criteria_id` = '" + id + "');";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editStatusEval(String id, String status) {
        try {
            String sql = "UPDATE `eval_criteria` SET `status` = b'" + status + "' WHERE (`criteria_id` = '" + id + "');";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addEval(String name, String assId, String evalWeight, String maxLoc, String isTeamEval, String status, String description, String mid) {
        try {
            String sql = "INSERT INTO `eval_criteria` (`ass_id`, `milestone_id`, `criteria_name`, `is_team_eval`, `eval_weight`, `max_loc`, `status`, `description`) VALUES"
                    + " ('" + assId + "', '" + mid + "', '" + name + "', b'" + isTeamEval + "', '" + evalWeight + "', '" + maxLoc + "', b'" + status + "', '" + description + "');";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//INSERT INTO `MyDBG3`.`eval_criteria` (`ass_id`, `milestone_id`, `criteria_name`, `is_team_eval`, `eval_weight`, `max_loc`, `status`, `description`) VALUES ('1', '1', '1', b'1', b'1', b'1', b'1', b'aaaa');
//INSERT INTO `MyDBG3`.`eval_criteria` (`ass_id`, `milestone_id`, `criteria_name`, `is_team_eval`, `eval_weight`, `max_loc`, `status`, `description`) VALUES ('1', '1', '1', b'1', b'1', b'1', b'1', b'1');
//
//    public static void main(String[] args) {
//        new AdminDAO().editEval("5", "123", "123", "123", "11", "1", "1", "123");
//        System.out.println();
//    }

    public void addEval(String name, String assId, String evalWeight, String maxLoc, String isTeamEval, String status, String description) {
        try {
            String sql = "INSERT INTO `MyDBG3`.`eval_criteria` (`ass_id`, `milestone_id`, `criteria_name`, `is_team_eval`, `eval_weight`, `max_loc`, `status`, `description`) VALUES"
                    + " ('" + assId + "', '" + 1 + "', '" + name + "', b'" + isTeamEval + "', '" + evalWeight + "', '" + maxLoc + "', b'" + status + "', '" + description + "');";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<UserAccount> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserAccount get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(UserAccount model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UserAccount model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
