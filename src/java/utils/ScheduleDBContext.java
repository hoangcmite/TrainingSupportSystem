/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Model.Account;
import Model.Attendance;
import Model.Classes;
import Model.Schedule;
import Model.Subject;
import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ScheduleDBContext extends DBContext1 {

    public ArrayList<Schedule> getClassList() {
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "select class_id, class_code from class";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Schedule s = new Schedule();
                s.setClassId(rs.getInt("class_id"));
                s.setClassCode(rs.getString("class_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Schedule> getRoomList() {
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "select distinct room from schedule";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Schedule s = new Schedule();
                s.setRoom(rs.getString("room"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Schedule> getScheduleList(int classId) {
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "select s.slot, s1.subject_code, s.dayStudy, s.fromTime, s.toTime, s.room ,s.number, s.classId\n"
                    + "from schedule s inner join class c \n"
                    + "on s.classId = c.class_id inner join subject s1 on s.subjectId = s1.subject_id \n"
                    + "where s.classId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, classId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSlot(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setDates(rs.getDate(3));
                s.setFrom(rs.getString(4));
                s.setTo(rs.getString(5));
                s.setRoom(rs.getString(6));
                s.setNumber(rs.getInt(7));
                s.setClassId(rs.getInt(8));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Schedule getSchedule(Schedule entity) {
        try {
            String sql = "select s.slot, s1.subject_code, s.dayStudy, s.fromTime, s.toTime, s.room ,s.number, s.classId\n"
                    + "from schedule s inner join class c \n"
                    + "on s.classId = c.class_id inner join subject s1 on s.subjectId = s1.subject_id \n"
                    + "where s.number = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getNumber());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setSlot(rs.getInt(1));
                s.setSubjectCode(rs.getString(2));
                s.setDates(rs.getDate(3));
                s.setFrom(rs.getString(4));
                s.setTo(rs.getString(5));
                s.setRoom(rs.getString(6));
                s.setNumber(rs.getInt(7));
                s.setClassId(rs.getInt(8));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertClass(Schedule entity) {
        try {
            String sql = "insert into schedule (slot, subjectId, classId, dayStudy, fromTime, toTime, room) values\n"
                    + "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSlot());
            stm.setInt(2, entity.getSubjectId());
            stm.setInt(3, entity.getClassId());
            stm.setDate(4, entity.getDates());
            stm.setString(5, entity.getFrom());
            stm.setString(6, entity.getTo());
            stm.setString(7, entity.getRoom());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateScheduleDetails(Schedule entity) {
        try {
            String sql = "update schedule\n"
                    + "set slot= ?, subjectId= ?, classId = ?, dayStudy = ?, fromTime = ?, toTime = ?, room = ?\n"
                    + "where number= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getSlot());
            stm.setInt(2, entity.getSubjectId());
            stm.setInt(3, entity.getClassId());
            stm.setDate(4, entity.getDates());
            stm.setString(5, entity.getFrom());
            stm.setString(6, entity.getTo());
            stm.setString(7, entity.getRoom());
            stm.setInt(8, entity.getNumber());
            stm.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Attendance> getAttendance(int subjectId, int classId) {
        ArrayList<Attendance> list = new ArrayList<>();
        try {
            String sql = "select u.avatar_url, a.trainee_id ,u.full_name, a.status, a.comment from attendance a inner join class c \n"
                    + "on a.class_id = c.class_id inner join user u \n"
                    + "on a.trainee_id = u.user_id inner join schedule s\n"
                    + "on a.schedule_id = s.number\n"
                    + "where s.subjectId = ? and s.classid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            stm.setInt(2, classId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance s = new Attendance();
                s.setUrl(rs.getString(1));
                s.setTraineeId(rs.getInt(2));
                s.setTraineeName(rs.getString(3));
                s.setStatus(rs.getInt(4));
                s.setComment(rs.getString(5));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

//    public static void main(String[] args) {
//        ScheduleDBContext context = new ScheduleDBContext();
//        ArrayList<Schedule> s = new ArrayList<>();
//        for (Schedule schedule : s) {
//            System.out.println(context.getScheduleList(1));
//        }
//    }
}
