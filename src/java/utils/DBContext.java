
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public abstract class DBContext<T> {
     static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://database-2.clu4n8uk1rfj.us-east-1.rds.amazonaws.com:3306/MyDBG3?allowPublicKeyRetrieval=true&useSSL=false", "dung2", "123456789");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbg3?allowPublicKeyRetrieval=true&useSSL=false", "root", "12345678");
//
//                            connection = DriverManager.getConnection("jdbc:mysql://database-2.clu4n8uk1rfj.us-east-1.rds.amazonaws.com:3306/MyDBG3?allowPublicKeyRetrieval=true&useSSL=false", "dung2", "123456789");

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }
    
    public abstract ArrayList<T> list();

    public abstract T get(int id);

    public abstract void insert(T model);

    public abstract void update(T model);

    public abstract void delete(int id);
}
