/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.products;

/**
 *
 * @author tuongtlc
 */
public class ProductDTO {
    private int productID;
    private String productName;
    private float productPrice;
    private String description;
    private int brandID;
    private int quantity;
    private int sold;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, float productPrice, String description, int brandID, int quantity, int sold) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.brandID = brandID;
        this.quantity = quantity;
        this.sold = sold;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
    
    
    
}
