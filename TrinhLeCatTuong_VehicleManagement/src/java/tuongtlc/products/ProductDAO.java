/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tuongtlc.utils.DBUtils;

/**
 *
 * @author tuongtlc
 */
public class ProductDAO {

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, productPrice,"
                        + " description, brandID, quantity, sold"
                        + " FROM tblProducts"
                        + " WHERE productName LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = Integer.parseInt(rs.getString("productID"));
                    String productName = rs.getString("productName");
                    float productPrice = Float.parseFloat(rs.getString("productPrice"));
                    String description= rs.getString("description");
                    int brandID = Integer.parseInt(rs.getString("brandID"));
                    int quantity=  Integer.parseInt(rs.getString("quantity"));
                    int sold = Integer.parseInt(rs.getString("sold"));
                    list.add(new ProductDTO(productID, productName, productPrice, description, brandID, quantity, sold));
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
    public List<BrandDTO> getListBrand() throws SQLException {
        List<BrandDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT brandID, brandName"
                        + " FROM tblBrands" ;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int brandID = Integer.parseInt(rs.getString("brandID"));
                    String branddName = rs.getString("brandName");
                    list.add(new BrandDTO(brandID, branddName));
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
    public boolean delete(String productID) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="DELETE FROM tblProducts "
                        + " WHERE productID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, productID);
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
    public boolean update(ProductDTO product) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="UPDATE tblProducts "
                        + " SET productName=?, productPrice=?,"
                        + " description=?, brandID=?, quantity=?, sold=? "
                        + " WHERE productID=? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setString(2, String.valueOf(product.getProductPrice()));
                ps.setString(3, product.getDescription());
                ps.setString(4, String.valueOf(product.getBrandID()));
                ps.setString(5, String.valueOf(product.getQuantity()));
                ps.setString(6, String.valueOf(product.getSold()));
                ps.setString(7, String.valueOf(product.getProductID()));
                
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
    public boolean insert(ProductDTO product) throws SQLException{
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql="INSERT INTO tblProducts( productName, productPrice,"
                        + " description, brandID, quantity, sold) "
                        + " VALUES(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setString(2, String.valueOf(product.getProductPrice()));
                ps.setString(3, product.getDescription());
                ps.setString(4, String.valueOf(product.getBrandID()));
                ps.setString(5, String.valueOf(product.getQuantity()));
                ps.setString(6, String.valueOf(product.getSold()));
                
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
}
