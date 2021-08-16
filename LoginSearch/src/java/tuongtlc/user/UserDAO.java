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
import java.util.ArrayList;
import java.util.List;
import tuongtlc.utils.DBUtils;

/**
 *
 * @author trinh
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT fullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE userID=" +"'"+ userID +"'"+ ""
                        + " AND password=" +"'"+ password+"'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID, "");
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
    public List<UserDTO> getListUSer(String search) throws SQLException{
        List<UserDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "SELECT userID, fullname, roleID"
                        + " FROM tblUsers"
                        + " WHERE fullName LIKE ?";
                ps=con.prepareStatement(sql);
                ps.setString(1, "%"+search+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    list.add(new UserDTO(userID, fullName, roleID, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
        return list;
    }
    public boolean delete(String userID) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="DELETE FROM tblUsers "
                        + " WHERE userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
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
    public boolean update(UserDTO user) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="UPDATE tblUsers "
                        + " SET fullName=?, roleID=? "
                        + " WHERE userID=? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getRoleID());
                ps.setString(3, user.getUserID());
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
    public boolean checkDuplicate(String userID) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="SELECT userID FROM tblUsers "
                        + " WHERE userID=? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
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
    public boolean insert(UserDTO user) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="INSERT INTO tblUsers(userID, fullName, roleID, password)  "
                        + " VALUES(?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserID());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getRoleID());
                ps.setString(4, user.getPassword());
                
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
    public boolean insert2(UserDTO user) throws SQLException, ClassNotFoundException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="INSERT INTO tblUsers(userID, fullName, roleID, password) "
                        + " VALUES(?,?,?,?) ";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getUserID());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getRoleID());
                ps.setString(4, user.getPassword());
                
                check = ps.executeUpdate()>0?true:false;
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
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
