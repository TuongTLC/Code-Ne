/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author trinh
 */
public class DBCon {
    public static Connection makeConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url ="jdbc:sqlserver://localhost:1433;databaseName=UserManagement";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
