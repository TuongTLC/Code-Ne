/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.order;

/**
 *
 * @author tuongtlc
 */
public class OrderDTO {
    private int orderID;
    private String userName;

    public OrderDTO() {
    }
    
    public OrderDTO(int orderID, String userName) {
        this.orderID = orderID;
        this.userName = userName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
