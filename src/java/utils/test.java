/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Model.UserAccount;
import dal.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Legon
 */
public class test {

    public static void main(String[] args) throws ParseException {
//        DAO dao = new DAO();
        //    dao.setCon();
//        ArrayList<UserAccount> list = new ArrayList<UserAccount>();
//        list = dao.takeinfor();
//         for(int i=0;i<list.size();i++){
//           System.out.print(list.get(i).getId()+" "+list.get(i).getPassword()
//                   +" "+list.get(i).getMail()+" "+list.get(i).getCreateat()+" "+list.get(i).getCode());
//           System.out.println("");
//       }
//        System.out.println(Integer.parseInt("9"));
//        String a = " ";
//        a.equals("");

       // Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//      
//        String time3= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(cal.getTime());
//      //  hour.toString();
//      //  LocalDateTime time = localDate.atStartOfDay();
////        time.toString();
//        System.out.println(time3);
//            Date date2 = new SimpleDateFormat("MMM", Locale.ENGLISH).parse("Feb");
//    Calendar cal2 = Calendar.getInstance();
//    cal2.setTime(date);
//    int month = cal2.get(Calendar.MONTH);
//    System.out.println(month);
//String ctime= "15:28:53";
//
//String[] s = ctime.trim().split(":");
//   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//   
//   LocalDateTime now = LocalDateTime.now();  
//    String time = dtf.format(now).toString();
//    for(int i=0;i< time.length();i++){
//        if(time.charAt(i)==47){
//             StringBuilder myName = new StringBuilder(time);
//                myName.setCharAt(i, '-');  
//                time= myName.toString();
//        }
//    }
// 
//   System.out.println(time);  
//    String[] s1 = time.trim().split(":");
//    
//    int tongs = Integer.parseInt(s[0])*3600 + Integer.parseInt(s[1])*60 + Integer.parseInt(s[2]);
//     int tongs1 = Integer.parseInt(s1[0])*3600 + Integer.parseInt(s1[1])*60 + Integer.parseInt(s1[2]);
//     int hieu = tongs1-tongs;
//        System.out.println(tongs1-tongs);
        String s = "true/false/false/false";
        String[] s1 = s.split("/");
        String result = (s1[0].equals("false")) ? "False" :"True";
        String result2 = (s1[1].equals("false")) ? "False" :"True";
        String result3 = (s1[2].equals("false")) ? "False" :"True";
        String result4 = (s1[3].equals("false")) ? "False" :"True";
        
        
        System.out.println(result+"/"+result2+"/"+result3+"/"+result4);
    }
}
