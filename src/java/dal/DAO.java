/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Classes;
import utils.JavaMailUtil;
import Model.Setting;
import Model.SettingValue;
import Model.Settingtitle;
import Model.Team;
import Model.UserAccount;
import Model.permission;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import utils.DBContext;

/**
 *
 * @author Legon
 */
public class DAO extends DBContext<UserAccount> {

    Connection con = getConnection();

    ArrayList<UserAccount> list;

    public Connection getCon() {
        return con;
    }

    public ArrayList<UserAccount> getList() {
        return list;
    }

    public void setList(ArrayList<UserAccount> list) {
        this.list = list;
    }
    
     public SettingValue getIDforDashboard(int id) {

        
        String sql = "SELECT setting_value,status,setting_title FROM MyDBG3.setting where setting_id="+id+";";
        
           // sql = "SELECT setting_title,setting_id,status FROM  setting  where setting_title like '%" + search + "%' and type_id = 5 and (status =2 or status =1) order by setting_title asc ;";

       SettingValue st=null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           while(rs.next()){
                st = new SettingValue(rs.getString(1),rs.getInt(2),rs.getString(3));
           }
        } catch (Exception e) {
        }

        return st;
    }
     
    
      public ArrayList<UserAccount> getWaitingMember(int id, int mid){
        ArrayList<UserAccount> lstcl = new ArrayList<UserAccount>();
         String sql = "with t as(SELECT user_id  FROM MyDBG3.class_user  where class_id="+id+" and user_id not in(select user_id from team_member where team_id in (select team_id from submit where milestone_id="+mid+")) ) select user_id,status,full_name,email from user where user_id in (select * from t);" ;
        
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                
                
                lstcl.add(new UserAccount(rs.getInt(1),0,rs.getString(2),rs.getString(3),rs.getString(4)));
            }} catch (Exception e) {
        }
        return lstcl;
    }
       public ArrayList<Classes> addMembertoTeam(int tid,int mid){
        ArrayList<Classes> lstcl = new ArrayList<Classes>();
         String sql = "INSERT INTO `MyDBG3`.`team_member` (`team_id`, `user_id`, `is_leader`, `is_active`) VALUES ('"+tid+"', '"+mid+"', b'0', b'1');";
       
        
        try {

             PreparedStatement ps = con.prepareStatement(sql);
          
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();
           
            } catch (Exception e) {
        }
        return lstcl;
    }
    
    
    
    
    
public ArrayList<Classes> updateLeader(int tid,int mid){
        ArrayList<Classes> lstcl = new ArrayList<Classes>();
         String sql = "  UPDATE `MyDBG3`.`team_member` SET `is_leader` = b'0' WHERE (`team_id` = '"+tid+"') and (`is_leader` = b'1');";
                
        
        try {

             PreparedStatement ps = con.prepareStatement(sql);
          
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();
           
            } catch (Exception e) {
        }
        sql = " UPDATE `MyDBG3`.`team_member` SET `is_leader` = b'1' WHERE (`team_id` = '"+tid+"') and (`user_id` = '"+mid+"');";
        try {

             PreparedStatement ps = con.prepareStatement(sql);
          
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();
           
            } catch (Exception e) {
        }
        return lstcl;
    }

   public ArrayList<Classes> deleteMember(int tid,int mid){
        ArrayList<Classes> lstcl = new ArrayList<Classes>();
         String sql = "DELETE FROM `MyDBG3`.`team_member` WHERE (`team_id` = '"+tid+"') and (`user_id` = '"+mid+"');";
       
        
        try {

             PreparedStatement ps = con.prepareStatement(sql);
          
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();
           
            } catch (Exception e) {
        }
        return lstcl;
    }
    public ArrayList<Classes> updateteam(int id,int status){
        ArrayList<Classes> lstcl = new ArrayList<Classes>();
         String sql = "UPDATE `MyDBG3`.`submit` SET `status` = b'"+status+"' WHERE (`team_id` = '"+id+"');";
       
        
        try {

             PreparedStatement ps = con.prepareStatement(sql);
          
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();
           
            } catch (Exception e) {
        }
        return lstcl;
    }
    
public ArrayList<Classes> getClassList(int id){
        ArrayList<Classes> lstcl = new ArrayList<Classes>();
         String sql = "SELECT class_id,class_code FROM MyDBG3.class where trainer_id="+id+" or supporter_id="+id+" ;";
       
        
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                lstcl.add(new Classes(rs.getInt(1),rs.getString(2)));
            }} catch (Exception e) {
        }
        return lstcl;
    }
      public ArrayList<UserAccount> getTeamMember(int id){
        ArrayList<UserAccount> lstcl = new ArrayList<UserAccount>();
         String sql = "SELECT tm.user_id,tm.is_leader,tm.is_active,u.full_name,u.email FROM MyDBG3.team_member tm inner join user u on tm.user_id=u.user_id  where team_id="+id+";\n" ;
        
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                
                
                lstcl.add(new UserAccount(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }} catch (Exception e) {
        }
        return lstcl;
    }
      
      
     public Team getTeamName(int id){
       Team lstcl = new Team();
         String sql = "SELECT  team_id,team_code,status FROM MyDBG3.team where team_id="+id+" ;";
       
        
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                lstcl = new Team(rs.getInt(1),rs.getString(2),rs.getInt(3));
            }} catch (Exception e) {
        }
        return lstcl;
    }
      public int[] getMilestoneList(int classid){
        int[] lstms = new int[100];
         String sql = "SELECT milestone_id FROM MyDBG3.milestone where class_id="+classid+";";
       
        int i=0;
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
               lstms[i] = rs.getInt(1);
               i++;
            }} catch (Exception e) {
        }
        return lstms;
    }
       public int[] getTeamList(int classid,int msid,int status){
        int[] lstms = new int[100];
         String sql = "SELECT team_id FROM MyDBG3.submit where class_id="+classid+" and milestone_id="+msid+" and status="+status+" ; ";
       
        int i=0;
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
               lstms[i] = rs.getInt(1);
               i++;
            }} catch (Exception e) {
        }
        return lstms;
    }
    public ArrayList<UserAccount> takeinfor() {
        
        String sql = "select * from user  ;";
       
        ArrayList<UserAccount> ar = new ArrayList<>();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String temp4 = "";
                String temp8 = "";
                String temp9 = "";
                String temp10 = "";

                if (rs.getString(4) != null) {
                    temp4 = rs.getString(4);
                }

                if (rs.getString(8) != null) {
                    temp8 = rs.getString(8);
                }
                if (rs.getString(9) != null) {
                    temp9 = rs.getString(9);
                }
                if (rs.getString(10) != null) {
                    temp10 = rs.getString(10);
                }
                int status = rs.getInt(7);
                String statuss = "";
                if (status == 0) {
                    statuss = "Deactive";
                }
                if (status == 1) {
                    statuss = "Active";
                }
                if (status == 2) {
                    statuss = "Inactive";
                }
        String sql2 = "SELECT company , position, address FROM MyDBG3.client where user_id= "+rs.getInt(1)+" ;";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
                String company = "";
                String position = "";
                String address = "";
                while (rs2.next()) {
                    if (rs2.getString(1) != null) {
                        company = rs2.getString(1);
                    }
                    if (rs2.getString(2) != null) {
                        position = rs2.getString(2);
                    }
                    if (rs2.getString(3) != null) {
                        address = rs2.getString(3);
                    }
                }
                ar.add(new UserAccount(rs.getInt(1), rs.getString(2), rs.getString(3),
                        temp4, rs.getString(5), rs.getString(6), statuss, temp8, temp9, temp10, company, position, address, rs.getString(11) + ""));
            }
        } catch (Exception e) {
        }

        return ar;
    }
     public void updateCurrent1(String mail) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now).toString();

        String code = "";
        final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzaABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int N = alphabet.length();

        Random r = new Random();
        String result = "";
        for (int i = 0; i < 10; i++) {
            result += alphabet.charAt(r.nextInt(N));
        }
        code = result;

//        String sql = " update MyDBG3.user set createtime = '" + time + "' ,code='" + code + "' where email='" + mail + "';";
        String sql = " update MyDBG3.user set code=? where email='" + mail + "';";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);

//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();

        } catch (Exception e) {
            //System.out.println("error");
        }
              JavaMailUtil.sendMail(mail, "Your link to verify your account is: http://localhost:9999/WebApplication2/CheckExpire?RegisID="+ code +"");
    }
        public ArrayList<Settingtitle> getSettingtitleAfterSearortaSearch(int type, String search) {

        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();
        String sql = "";
        if (type == 0) {
            sql = "SELECT setting_title,setting_id,status FROM  setting  where setting_title like '%" + search + "%' and type_id = 5 and (status =2 or status =1) order by setting_title asc ;";

        } else {
            sql = "SELECT setting_title,setting_id,status FROM  setting where setting_title like '%" + search + "%' and type_id = 5 and (status =2 or status =1) order by setting_title desc ;";

        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Settingtitle st = new Settingtitle();
                st.setTitle(rs.getString(1));
                st.setSid(rs.getInt(2));
                st.setStatus(rs.getInt(3));
                int flag = 0;
                for (int i = 0; i < lstst.size(); i++) {
                    if (lstst.get(i).getSid() == rs.getInt(2)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    lstst.add(st);
                }

            }
        } catch (Exception e) {
        }

        return lstst;
    }
        
        public int[] getScreenId(int id){
           int[] i  = new int[1000];
           int index=0;
        try {

            String sql = "SELECT p.screen_id from user u inner join user_role ur on u.user_id=ur.user_id inner join permission p on p.role_id= ur.setting_id inner join setting s on s.setting_id = p.screen_id\n" +
" where u.user_id= "+id+" and p.get_all_data=1 and s.status=2 and s.setting_title not like '%detail%' and s.setting_id != 21 and s.setting_id!= 54;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i[index] = rs.getInt(1);
                index++;

            }
        } catch (Exception e) {
        }

        return i; 
        }
     public ArrayList<Settingtitle> getSettingtitleAfterFilteraSearch(int type, String search) {

        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();
        String sql = "";
        if (type == 2) {
            sql = "SELECT setting_title,setting_id,status FROM  MyDBG3.setting  where status =1 and type_id = 5 ;";

        } 
        else if (type == 3) {
            sql = "SELECT setting_title,setting_id,status FROM MyDBG3.setting  where status =2 and type_id = 5;";

        }
        else if (type == 0) {
            sql = "SELECT setting_title,setting_id,status FROM  MyDBG3.setting where setting_title like '%" + search + "%' and status =1 and type_id = 5;";

        }   else {
            sql = "SELECT setting_title,setting_id,status FROM MyDBG3.setting where setting_title like '%" + search + "%' and  status =2 and type_id = 5;";

        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Settingtitle st = new Settingtitle();
                st.setTitle(rs.getString(1));
                st.setSid(rs.getInt(2));
                 st.setStatus(rs.getInt(3));
                int flag = 0;
                for (int i = 0; i < lstst.size(); i++) {
                    if (lstst.get(i).getSid() == rs.getInt(2)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    lstst.add(st);
                }

            }
        } catch (Exception e) {
        }

        return lstst;
    }
     
       public ArrayList<Settingtitle> getSettingtitleAfterSearch(String txt) {

        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();

        try {

            String sql = "SELECT setting_title,setting_id,status FROM MyDBG3.setting  where setting_title LIKE '%" + txt + "%' and type_id = 5 and (status =2 or status =1);";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Settingtitle st = new Settingtitle();
                st.setTitle(rs.getString(1));
                st.setSid(rs.getInt(2));
                 st.setStatus(rs.getInt(3));
                int flag = 0;
                for (int i = 0; i < lstst.size(); i++) {
                    if (lstst.get(i).getSid() == rs.getInt(2)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    lstst.add(st);
                }

            }
        } catch (Exception e) {
        }

        return lstst;
    }
         public void updateStatus(String mail) throws Exception {

//        String sql = " update MyDBG3.user set createtime = '" + time + "' ,code='" + code + "' where email='" + mail + "';";
        String sql = " update MyDBG3.user set status=1 where code='" + mail + "';";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();

        } catch (Exception e) {
            //System.out.println("error");
        }
        //   JavaMailUtil.sendMail(mail, "Your link to change password screen is: http://localhost:9999/WebApplication2/CheckExpire?RegisID="+ code +"");
    }
     public ArrayList<SettingValue> getSettingValue() {

        ArrayList<SettingValue> lstst = new ArrayList<SettingValue>();

        try {
           
            String sql = "SELECT setting_id,setting_value FROM MyDBG3.setting ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingValue st = new SettingValue();
                st.setTitle(rs.getInt(1));
                st.setValue(rs.getString(2));
           
               
               
                    lstst.add(st);
               
               

            
         
            }
        } catch (Exception e) {
        }

        return lstst;
    }
      public ArrayList<Settingtitle> getSettingtitleAfterSearort(int type) {

        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();
        String sql = "";
        if (type == 0) {
            sql = "SELECT setting_title,setting_id,status FROM  setting where type_id = 5 and (status =2 or status =1) order by setting_title asc ;";

        } else {
            sql = "SELECT setting_title,setting_id,status FROM  setting where type_id = 5 and (status =2 or status =1) order by setting_title desc ;";

        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Settingtitle st = new Settingtitle();
                st.setTitle(rs.getString(1));
                st.setSid(rs.getInt(2));
                 st.setStatus(rs.getInt(3));
                int flag = 0;
                for (int i = 0; i < lstst.size(); i++) {
                    if (lstst.get(i).getSid() == rs.getInt(2)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    lstst.add(st);
                }

            }
        } catch (Exception e) {
        }

        return lstst;
    }

     
        public String updatePermission(int id, int role, int a, int b, int c, int d) {
        
        String sql = "SELECT * FROM MyDBG3.permission WHERE (`screen_id` = '" + id + "') and  (`role_id` = '" + role + "') ;";
        int flag=0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
               flag=1;

            }
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();

        } catch (Exception e) {

        }
        if(flag==1)
         sql = "UPDATE `MyDBG3`.`permission` SET `get_all_data` = b'" + a + "', `can_edit` = b'" + b + "', `can_add` = b'" + c + "', `can_delete` = b'" + d + "' WHERE (`screen_id` = '" + id + "') and (`role_id` = '" + role + "');";
        else
         sql = "INSERT INTO `MyDBG3`.`permission` (`screen_id`, `role_id`, `get_all_data`, `can_edit`, `can_add`, `can_delete`) VALUES ('"+id+"', '"+role+"', b'"+a+"', b'"+b+"', b'"+c+"', b'"+d+"');";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();

        } catch (Exception e) {

        }
         
        return sql;
    }

    public ArrayList<UserAccount> getAllUser() {
        ArrayList<UserAccount> Alist = new ArrayList<>();
        try {
            String sql = "select * from user where status =1;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserAccount account = new UserAccount();
                account.setId(rs.getInt("user_id"));
                account.setFullname(rs.getString("full_name"));
                account.setMail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setStatus(rs.getInt("status") == 1 ? "Active" : "Inactive");
                Alist.add(account);
            }
        } catch (Exception e) {
        }
        return Alist;
    }

     public permission getPermis(int sid, int rid) {

        permission p = new permission();
        try {
            String sql = "SELECT get_all_data,can_edit,can_add,can_delete FROM MyDBG3.permission  where screen_id=" + sid + " and role_id = " + rid + ";";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int flag = rs.getInt(1);
                if (flag == 1) {
                    p.setAccess(true);

                } else {
                    p.setAccess(false);
                }
                flag = rs.getInt(2);
                if (flag == 1) {
                    p.setEdit(true);

                } else {
                    p.setEdit(false);
                }

                flag = rs.getInt(3);
                if (flag == 1) {
                    p.setAdd(true);

                } else {
                    p.setAdd(false);
                }
                flag = rs.getInt(4);
                if (flag == 1) {
                    p.setDelete(true);

                } else {
                    p.setDelete(false);
                }

            }
        } catch (Exception e) {
        }

        return p;
    }
       public String setUserImage(String link, int userID, String currentPath) throws IOException {
        String result = "";
        int width = 963;
        int height = 640;
        BufferedImage image = null;
        File f = null; //read image 

//        ServletContext servletContext = getServletContext();
//		String currentPath = servletContext.getRealPath(File.separator);
//         currentPath = new java.io.File(".").getCanonicalPath();
//       currentPath= currentPath.substring(0, currentPath.length()-10);
        try {
            f = new File(link); //image file path 
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } //write image try{ f = new File("D:\\Image\\Output.jpg");  //output file path ImageIO.write(image, "jpg", f); System.out.println("Writing complete."); }catch(IOException e){ System.out.println("Error: "+e); } }//main() ends here

        try {
            f = new File(currentPath + "//images//target//" + userID + ".jpg");
            result = "//images//target//" + userID + ".jpg";
            if (f.exists()) {
                f.delete();
            }
//output file path 
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            result = currentPath;
        }
        return "//images//target//" + userID + ".jpg";
    }
         public String linktoimg(String path) throws IOException {

        DAO dao2 = this;
        //write image
        String filepath1 = path;

        File f1 = new File(filepath1);
        String imgName1 = filepath1;
        String type = imgName1.substring(imgName1.length() - 3);

        BufferedImage bImage1 = ImageIO.read(new File(imgName1));//give the path of an image
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();

        if (type.equals("jpg")) {
            ImageIO.write(bImage1, "jpg", baos1);
        } else {
            ImageIO.write(bImage1, "png", baos1);
        }

        baos1.flush();
        byte[] imageInByteArray1 = baos1.toByteArray();
        baos1.close();
        String b641 = DatatypeConverter.printBase64Binary(imageInByteArray1);
        return b641;

    }
        public int getSettingSize() {

        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();

        try {
           
            String sql = "SELECT setting_title,screen_id FROM MyDBG3.permission  left join setting on screen_id = setting.setting_id ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Settingtitle st = new Settingtitle();
                st.setTitle(rs.getString(1));
                st.setSid(rs.getInt(2));
                int flag = 0;
                for (int i = 0; i < lstst.size(); i++) {
                    if (lstst.get(i).getSid() == rs.getInt(2)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    lstst.add(st);
                }
             

            }
       
        } catch (Exception e) {
        }

        return lstst.size();
    }

     public ArrayList<Settingtitle> getSettingtitle() {

        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();

        try {

            String sql = "SELECT setting_title,setting_id,status FROM  setting where type_id = 5 and (status =2 or status =1) ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Settingtitle st = new Settingtitle();
                st.setTitle(rs.getString(1));
                st.setSid(rs.getInt(2));
                 st.setStatus(rs.getInt(3));
                int flag = 0;
                for (int i = 0; i < lstst.size(); i++) {
                    if (lstst.get(i).getSid() == rs.getInt(2)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    lstst.add(st);
                }

            }
        } catch (Exception e) {
        }

        return lstst;
    }
     public ArrayList<SettingValue> getSettingStatus() {

        ArrayList<SettingValue> lstst = new ArrayList<SettingValue>();

        try {

            String sql = "SELECT setting_id,setting_title, status FROM MyDBG3.setting where type_id=1;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingValue st = new SettingValue();
                st.setValue(rs.getString(2));
                st.setStatus(rs.getInt(3));
                st.setTitle(rs.getInt(1));
                lstst.add(st);

            }
        } catch (Exception e) {
        }

        return lstst;
    }
    public void updateCurrent(String mail) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now).toString();

        String code = "";
        final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzaABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int N = alphabet.length();

        Random r = new Random();
        String result = "";
        for (int i = 0; i < 10; i++) {
            result += alphabet.charAt(r.nextInt(N));
        }
        code = result;

//        String sql = " update MyDBG3.user set createtime = '" + time + "' ,code='" + code + "' where email='" + mail + "';";
        String sql = " update MyDBG3.user set createtime = ?,code=? where email='" + mail + "';";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, time);
            ps.setString(2, code);
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();

        } catch (Exception e) {
            //System.out.println("error");
        }
        JavaMailUtil.sendMail(mail, "Your link to change password screen is: http://localhost:9999/WebApplication2/CheckExpire?id=" + code + "");
    }

    public String getRandomChars(int numChars) {
        final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzaABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int N = alphabet.length();

        Random r = new Random();
        String result = "";
        for (int i = 0; i < numChars; i++) {
            result += alphabet.charAt(r.nextInt(N));
        }
        return result;
    }

    public String register(String fullname, String mail) throws Exception {

        String password = this.getRandomChars(10);
        String sql = "insert into MyDBG3.user(password,email,full_name,avatar_url,status,role)  values(md5('123123444'),'" + mail + "','" + fullname + "','https://www.w3schools.com/html/workplace.jpg',0,6);";

        Connection con2 = con;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
//               ps.setString(1, time);
//                ps.setString(2, code);
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();
//            JavaMailUtil.sendMail(mail, "Your password to login is: " + password);

        } catch (Exception e) {

        }
//        String sql2 = "  INSERT INTO `MyDBG3`.`client` (`user_id`) VALUES ("+id+");";
//PreparedStatement ps2 = con2.prepareStatement(sql2);
//ps2.execute();

        sql = "SELECT user_id FROM MyDBG3.user where email='" + mail + "' ;";
        int id = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {

        }
        

        sql = " INSERT INTO `MyDBG3`.`client` (`user_id`) VALUES (" + id + ");";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();

        } catch (Exception e) {

        }
         sql = " INSERT INTO `MyDBG3`.`user_role` (user_id,setting_id) VALUES (" + id + ",46);";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();

        } catch (Exception e) {

        }
        return sql;
    }
        public ArrayList<SettingValue> getSettingValueStatus() {

        ArrayList<SettingValue> lstst = new ArrayList<SettingValue>();

        try {

            String sql = "SELECT setting_id,setting_value,status FROM MyDBG3.setting where type_id=5 ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingValue st = new SettingValue();
                st.setTitle(rs.getInt(1));
                st.setValue(rs.getString(2));
                st.setStatus(rs.getInt(3));
                lstst.add(st);

            }
        } catch (Exception e) {
        }

        return lstst;
    }
         public int getUserRole(int id) {

       int role=0;

        try {

            String sql = "SELECT * FROM MyDBG3.user_role where user_id  = "+id+";";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role = rs.getInt(2);

            }
        } catch (Exception e) {
        }

        return role;
    }
   public String registerbymail(String mail, String picture) throws Exception {
        String password = this.getRandomChars(8);
        String sql = "insert into MyDBG3.user(password,email,full_name,avatar_url,status,role)  values(md5('" + password + "'),'" + mail + "','New User','" + picture + "',1,6);";
        Connection con2 = con;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
//               ps.setString(1, time);
//                ps.setString(2, code);
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
             JavaMailUtil.sendMail(mail, "Your password is: "+ password +"");
            ps.execute();

        } catch (Exception e) {
        }
        sql = "SELECT user_id FROM MyDBG3.user where email='" + mail + "' ;";
        int id = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }

        sql = id + "";
        sql = " INSERT INTO `MyDBG3`.`client` (`user_id`) VALUES (" + id + ");";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
        }
           sql = " INSERT INTO `MyDBG3`.`user_role` (user_id,setting_id) VALUES (" + id + ",46);";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();

        } catch (Exception e) {

        }
    
        return sql;
    }


    public void mailchangepass(String id, String pass) {
        String sql = "UPDATE `MyDBG3`.`user` SET `password` = md5('" + pass + "') WHERE (`user_id` = " + id + ");";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();
            ps.execute();

        } catch (Exception e) {

        }

    }

    public String updateprofile(String id, String fullname, String mobie, String avatar_url, String note, String company, String position, String address) {
//          String sql = "UPDATE `MyDBG3`.`user` SET `full_name` = ?, SET `mobile` = ?, SET `avatar_url` = ?, "
//                  + "SET `note` = ?, SET `company` = ?, SET `position` = ?, SET `address` = ?,"
//                  +" WHERE (`user_id` = ?);";
        String sql = "UPDATE `MyDBG3`.`user` SET `full_name` = '" + fullname + "',  `mobile` =  '" + mobie + "',  `avatar_url` =  '" + avatar_url + "', "
                + " `note` =  '" + note + "'"
                + " WHERE (`user_id` = " + id + ");";
        String sql2 = "UPDATE `MyDBG3`.`client` SET  `company` =  '" + company + "',  `position` =  '" + position + "',  `address` =  '" + address + "'"
                + " WHERE (`user_id` = " + id + ");";
        int temp = Integer.parseInt(id);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.execute();
//               ps.setString(1, fullname);
//               ps.setString(2, mobie);
//               ps.setString(3, avatar_url);
//               ps.setString(4, note);
//               ps.setString(5, company);
//               ps.setString(6, position);
//               ps.setString(7, address);
//                ps.setInt(8, temp);
//                 ps.setString(3, mail);
//                 
//                        sql=ps.toString();

        } catch (Exception e) {

        }
        return sql;
    }

    @Override
    public ArrayList<UserAccount> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserAccount get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(UserAccount model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(UserAccount model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
