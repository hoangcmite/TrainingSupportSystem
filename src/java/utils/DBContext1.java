/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*;
import static utils.DBContext.connection;

public class DBContext1 {

    protected Connection connection;

    public DBContext1() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://database-2.clu4n8uk1rfj.us-east-1.rds.amazonaws.com:3306/MyDBG3?allowPublicKeyRetrieval=true&useSSL=false", "dung2", "123456789");
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }

    public ResultSet getData(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    
//    public static void main(String[] args) {
//        DBContext db = new DBContext();
//        Connection conn;
//        conn = 
//        if (conn != null) {
//            System.out.println("thanh cong r djt me :)))");
//        } else {
//            System.out.println("fail");
//        }
//    }
}