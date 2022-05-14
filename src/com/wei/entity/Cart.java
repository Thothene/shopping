package com.wei.entity;

public class Cart {
    private int cart_user_id;
    private int cart_pro_id;
    private int cart_pro_number;

    @Override
    public String toString() {
        return "Cart{" +
                "cart_user_id=" + cart_user_id +
                ", cart_pro_id=" + cart_pro_id +
                ", cart_pro_number=" + cart_pro_number +
                '}';
    }

    public Cart(int cart_user_id, int cart_pro_id, int cart_pro_number) {
        this.cart_user_id = cart_user_id;
        this.cart_pro_id = cart_pro_id;
        this.cart_pro_number = cart_pro_number;
    }

    public Cart() {
    }



    public int getCart_user_id() {
        return cart_user_id;
    }

    public void setCart_user_id(int cart_user_id) {
        this.cart_user_id = cart_user_id;
    }

    public int getCart_pro_id() {
        return cart_pro_id;
    }

    public void setCart_pro_id(int cart_pro_id) {
        this.cart_pro_id = cart_pro_id;
    }

    public int getCart_pro_number() {
        return cart_pro_number;
    }

    public void setCart_pro_number(int cart_pro_number) {
        this.cart_pro_number = cart_pro_number;
    }
}
