package com.wei.entity;

public class Product {
    private int pro_id;
    private String pro_name;
    private String pro_info;
    private String pro_image;
    private int pro_number;
    private float pro_price;
    private int pro_cat_id;
    private int pro_sold;

    public Product() {
    }

    public Product(int pro_id, String pro_name, String pro_info, String pro_image, int pro_number, float pro_price, int pro_cat_id, int pro_sold) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_info = pro_info;
        this.pro_image = pro_image;
        this.pro_number = pro_number;
        this.pro_price = pro_price;
        this.pro_cat_id = pro_cat_id;
        this.pro_sold = pro_sold;
    }

    public Product(String pro_name, String pro_info, String pro_image, int pro_number, float pro_price, int pro_cat_id, int pro_sold) {
        this.pro_name = pro_name;
        this.pro_info = pro_info;
        this.pro_image = pro_image;
        this.pro_number = pro_number;
        this.pro_price = pro_price;
        this.pro_cat_id = pro_cat_id;
        this.pro_sold = pro_sold;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_info() {
        return pro_info;
    }

    public void setPro_info(String pro_info) {
        this.pro_info = pro_info;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    public int getPro_number() {
        return pro_number;
    }

    public void setPro_number(int pro_number) {
        this.pro_number = pro_number;
    }

    public float getPro_price() {
        return pro_price;
    }

    public void setPro_price(float pro_price) {
        this.pro_price = pro_price;
    }

    public int getPro_cat_id() {
        return pro_cat_id;
    }

    public void setPro_cat_id(int pro_cat_id) {
        this.pro_cat_id = pro_cat_id;
    }

    public int getPro_sold() {
        return pro_sold;
    }

    public void setPro_sold(int pro_sold) {
        this.pro_sold = pro_sold;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pro_id=" + pro_id +
                ", pro_name='" + pro_name + '\'' +
                ", pro_info='" + pro_info + '\'' +
                ", pro_image='" + pro_image + '\'' +
                ", pro_number=" + pro_number +
                ", pro_price=" + pro_price +
                ", pro_cat_id=" + pro_cat_id +
                ", pro_sold=" + pro_sold +
                '}';
    }
}
