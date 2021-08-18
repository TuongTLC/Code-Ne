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
public class ProductErr {
    private String productNameErr;
    private String priceErr;
    private String brandErr;
    private String descriptionErr;
    private String quantityErr;
    private String soldErr;

    public ProductErr() {
        this.productNameErr = "";
        this.priceErr = "";
        this.brandErr = "";
        this.descriptionErr = "";
        this.quantityErr = "";
        this.soldErr = "";
    }

    public ProductErr(String productNameErr, String priceErr, String brandErr, String descriptionErr, String quantityErr, String soldErr) {
        this.productNameErr = productNameErr;
        this.priceErr = priceErr;
        this.brandErr = brandErr;
        this.descriptionErr = descriptionErr;
        this.quantityErr = quantityErr;
        this.soldErr = soldErr;
    }

    public String getProductNameErr() {
        return productNameErr;
    }

    public void setProductNameErr(String productNameErr) {
        this.productNameErr = productNameErr;
    }

    public String getPriceErr() {
        return priceErr;
    }

    public void setPriceErr(String priceErr) {
        this.priceErr = priceErr;
    }

    public String getBrandErr() {
        return brandErr;
    }

    public void setBrandErr(String brandErr) {
        this.brandErr = brandErr;
    }

    public String getDescriptionErr() {
        return descriptionErr;
    }

    public void setDescriptionErr(String descriptionErr) {
        this.descriptionErr = descriptionErr;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public String getSoldErr() {
        return soldErr;
    }

    public void setSoldErr(String soldErr) {
        this.soldErr = soldErr;
    }
    
    
}
