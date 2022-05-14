package com.wei.entity;

public class P_record {
    private int prec_id;
    private int prec_user_id;
    private int prec_pro_id;
    private String prec_time;
    private int prec_number;
    private float prec_total_money;
    private String prec_ip;

    public P_record() {
    }

    public P_record(int prec_user_id, int prec_pro_id, String prec_time, int prec_number, float prec_total_money, String prec_ip) {
        this.prec_user_id = prec_user_id;
        this.prec_pro_id = prec_pro_id;
        this.prec_time = prec_time;
        this.prec_number = prec_number;
        this.prec_total_money = prec_total_money;
        this.prec_ip = prec_ip;
    }

    public P_record(int prec_id, int prec_user_id, int prec_pro_id, String prec_time, int number, float prec_total_money, String prec_ip) {
        this.prec_id = prec_id;
        this.prec_user_id = prec_user_id;
        this.prec_pro_id = prec_pro_id;
        this.prec_time = prec_time;
        this.prec_number = number;
        this.prec_total_money = prec_total_money;
        this.prec_ip = prec_ip;
    }

    @Override
    public String toString() {
        return "P_record{" +
                "prec_id=" + prec_id +
                ", prec_user_id=" + prec_user_id +
                ", prec_pro_id=" + prec_pro_id +
                ", prec_time='" + prec_time + '\'' +
                ", prec_number=" + prec_number +
                ", prec_total_money=" + prec_total_money +
                ", prec_ip='" + prec_ip + '\'' +
                '}';
    }

    public int getPrec_id() {
        return prec_id;
    }

    public void setPrec_id(int prec_id) {
        this.prec_id = prec_id;
    }

    public int getPrec_user_id() {
        return prec_user_id;
    }

    public void setPrec_user_id(int prec_user_id) {
        this.prec_user_id = prec_user_id;
    }

    public int getPrec_pro_id() {
        return prec_pro_id;
    }

    public void setPrec_pro_id(int prec_pro_id) {
        this.prec_pro_id = prec_pro_id;
    }

    public String getPrec_time() {
        return prec_time;
    }

    public void setPrec_time(String prec_time) {
        this.prec_time = prec_time;
    }

    public int getPrec_number() {
        return prec_number;
    }

    public void setPrec_number(int prec_number) {
        this.prec_number = prec_number;
    }

    public float getPrec_total_money() {
        return prec_total_money;
    }

    public void setPrec_total_money(float prec_total_money) {
        this.prec_total_money = prec_total_money;
    }

    public String getPrec_ip() {
        return prec_ip;
    }

    public void setPrec_ip(String prec_ip) {
        this.prec_ip = prec_ip;
    }

}
