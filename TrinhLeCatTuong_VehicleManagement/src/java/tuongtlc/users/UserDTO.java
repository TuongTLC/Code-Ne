/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.users;

/**
 *
 * @author tuongtlc
 */
public class UserDTO {
    private String userName;
    private String fullName;
    private String password;
    private int roleID;

    public UserDTO() {
    }

    public UserDTO( String userName, String fullName, String password, int roleID) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    
    
}
