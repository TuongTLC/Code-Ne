/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tuongtlc.utils.DBUtils;

/**
 *
 * @author tuongtlc
 */
public class UserDAO {
    public UserDTO checkLogin(String userName, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT  userName, userFullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE userName=" +"'"+ userName +"'"+ ""
                        + " AND password=" +"'"+ password+"'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String loginUserName = rs.getString("userName");
                    String fullName = rs.getString("userFullName");
                    int roleID = Integer.parseInt(rs.getString("roleID"));
                    user = new UserDTO( loginUserName, fullName, "", roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }
    public boolean insert(UserDTO user) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="INSERT INTO tblUsers(userName, userFullName, password, roleID)  "
                        + " VALUES(?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getPassword());
                ps.setString(4, String.valueOf(user.getRoleID()));
                
                check = ps.executeUpdate()>0?true:false;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (ps!=null) {
                ps.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return check;
    }
     public boolean checkDuplicate(String userName) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="SELECT userName FROM tblUsers "
                        + " WHERE userName=? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                rs=ps.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }
}
