/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import Model.WebContact;
import Model.WebContact1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import utils.DBContext;
import static utils.DBContext.getConnection;

/**
 *
 * @author Admin
 */
public class WebContactDAO extends DBContext<WebContact1> {

    Connection connection = getConnection();

    @Override
    public ArrayList<WebContact1> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WebContact1 get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insertWebcontact(WebContact1 model) throws Exception{
            String sql = "INSERT INTO `MyDBG3`.`web_contact` (`category_id`, `supporter_id`, `full_name`, `email`, `mobile`, `message`, `status`) VALUES ('"
                    + model.getCategoryId() + "','" + model.getSupporter_id() + "','" + model.getFullName() + "','" + model.getEmail() + "','" + model.getMobile() + "','" + model.getMessage() + "', b'1')";
            System.out.println("SQL la: "+sql);
            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1,String.valueOf( ));
//            stm.setString(2,String.valueOf(  model.getSupporter_id()));
//            stm.setString(3, model.getFullName());
//            stm.setString(4, model.getEmail());
//            stm.setString(5, model.getMobile());
//            stm.setString(6, model.getMessage());
            stm.executeUpdate();
    }

    @Override
    public void update(WebContact1 model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(WebContact1 model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
