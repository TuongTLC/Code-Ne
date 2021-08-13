/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tuongtlc.dbutils.DBConnect;

/**
 *
 * @author trinh
 */
public class UserDAO {
    public boolean checkLongin(String userID, String pass) throws SQLException{
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBConnect.getConnection();
            if (con!=null) {
                String sql = "SELECT userID "
                        + " FROM tblUsers "
                        + " WHERE userID=? AND password=?";
                ps=con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (rs!=null) {
                rs.close();
            }
            if (ps!=null) {
                ps.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return result;
    }
    
}
