/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.products;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tuongtlc
 */
public class CartDTO {
    private Map<String, CartProductDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, CartProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, CartProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CartProductDTO> cart) {
        this.cart = cart;
    }
    
    
    public boolean add(CartProductDTO pro) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(pro.getName().trim())) {
                int quantity = this.cart.get(pro.getName().trim()).getQuantity();
                pro.setQuantity(quantity + pro.getQuantity());
            }
            cart.put(pro.getName().trim(), pro);
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
        public boolean delete(String id) {
        boolean check = false;
        try {
            if (this.cart == null) {
                check = false;
            } else if (this.cart.containsKey(id)) {
                this.cart.remove(id);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;

    }

    public boolean update(String id, CartProductDTO pro) {
        boolean check = false;
        try {
            if (this.cart == null) {
                check = false;
            } else if (this.cart.containsKey(id)) {
                this.cart.replace(id, pro);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
