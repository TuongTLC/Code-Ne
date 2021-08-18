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
public class UserErr {
    private String userNameError;
    private String userFullNameError;
    private String passwordError;
    private String confirmError;

    public UserErr() {
        this.userNameError = "";
        this.userFullNameError = "";
        this.passwordError = "";
        this.confirmError = "";
    }

    public UserErr(String userNameError, String userFullNameError, String passwordError, String confirmError) {
        this.userNameError = userNameError;
        this.userFullNameError = userFullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getUserFullNameError() {
        return userFullNameError;
    }

    public void setUserFullNameError(String userFullNameError) {
        this.userFullNameError = userFullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }
    
    
}
