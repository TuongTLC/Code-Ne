/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.tea;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author trinh
 */
public class CartDTO {

    private Map<String, TeaDTO> cart;// <id,dto>

    public CartDTO() {
    }

    public CartDTO(Map<String, TeaDTO> cart) {
        this.cart = cart;
    }

    public Map<String, TeaDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, TeaDTO> cart) {
        this.cart = cart;
    }

    public boolean add(TeaDTO tea) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(tea.getId())) {
                int quantity = this.cart.get(tea.getId()).getQuantity();
                tea.setQuantity(quantity + tea.getQuantity());
            }
            cart.put(tea.getId(), tea);
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

    public boolean update(String id, TeaDTO tea) {
        boolean check = false;
        try {
            if (this.cart == null) {
                check = false;
            } else if (this.cart.containsKey(id)) {
                this.cart.replace(id, tea);
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
