/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tuongtlc.products.ProductDTO;
import tuongtlc.utils.DBUtils;

/**
 *
 * @author tuongtlc
 */
public class OrderDAO {

    public boolean insertOrder(String userName) throws SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblOrders (userName)"
                        + " VALUES(?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);

                check = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
    public boolean insertOrderItem(OrderProductDTO dto) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="INSERT INTO tblOrderItems (orderID, productID, quantity )"
                        + " VALUES(?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(2, String.valueOf(dto.getProductID()));
                ps.setString(3, String.valueOf(dto.getQuantity()));
                ps.setString(1, String.valueOf(dto.getOrderID()));
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
    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT productID, productName "
                        + " FROM tblProducts"
                        + " WHERE productName LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = Integer.parseInt(rs.getString("productID"));
                    String productName = rs.getString("productName");

                    list.add(new ProductDTO(productID, productName, 0, "", 0, 0, 0));
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
        return list;
    }
    public OrderDTO getOrderList(String userName) throws SQLException {
        OrderDTO list = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT orderID"
                        + " FROM tblOrders"
                        + " WHERE userName=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int orderID = Integer.parseInt(rs.getString("orderID"));

                    list = new OrderDTO(orderID, userName);
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
        return list;
    }
    public OrderDTO searchOrder(String userName, int orderID) throws SQLException {
        OrderDTO list = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT orderID, userName"
                        + " FROM tblOrders"
                        + " WHERE userName=? AND orderID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, String.valueOf(orderID));
                rs = ps.executeQuery();
                while (rs.next()) {
                    list = new OrderDTO(orderID, userName);
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
        return list;
    }
    public List<OrderProductDTO> searchOrderItems( int orderID) throws SQLException {
        List<OrderProductDTO> itemList = new ArrayList<OrderProductDTO>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT orderID, productID, quantity"
                        + " FROM tblOrderItems"
                        + " WHERE  orderID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, String.valueOf(orderID));
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = Integer.parseInt(rs.getString("productID"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    itemList.add(new OrderProductDTO(orderID, productID, quantity));
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
        return itemList;
    }
}
