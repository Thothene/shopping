package com.wei.entity;

public class B_record {
    public int getBrec_id() {
        return brec_id;
    }

    @Override
    public String toString() {
        return "B_record{" +
                "brec_id=" + brec_id +
                ", brec_user_id=" + brec_user_id +
                ", brec_pro_id=" + brec_pro_id +
                ", brec_start_time='" + brec_start_time + '\'' +
                ", brec_total_time='" + brec_total_time + '\'' +
                ", brec_ip='" + brec_ip + '\'' +
                '}';
    }

    public void setBrec_id(int brec_id) {
        this.brec_id = brec_id;
    }

    public int getBrec_user_id() {
        return brec_user_id;
    }

    public void setBrec_user_id(int brec_user_id) {
        this.brec_user_id = brec_user_id;
    }

    public int getBrec_pro_id() {
        return brec_pro_id;
    }

    public void setBrec_pro_id(int brec_pro_id) {
        this.brec_pro_id = brec_pro_id;
    }

    public String getBrec_start_time() {
        return brec_start_time;
    }

    public void setBrec_start_time(String brec_start_time) {
        this.brec_start_time = brec_start_time;
    }

    public String getBrec_total_time() {
        return brec_total_time;
    }

    public void setBrec_total_time(String brec_total_time) {
        this.brec_total_time = brec_total_time;
    }

    public String getBrec_ip() {
        return brec_ip;
    }

    public void setBrec_ip(String brec_ip) {
        this.brec_ip = brec_ip;
    }

    public B_record() {
    }

    public B_record(int brec_user_id, int brec_pro_id, String brec_start_time, String brec_total_time, String brec_ip) {
        this.brec_user_id = brec_user_id;
        this.brec_pro_id = brec_pro_id;
        this.brec_start_time = brec_start_time;
        this.brec_total_time = brec_total_time;
        this.brec_ip = brec_ip;
    }

    public B_record(int brec_id, int brec_user_id, int brec_pro_id, String brec_start_time, String brec_total_time, String brec_ip) {
        this.brec_id = brec_id;
        this.brec_user_id = brec_user_id;
        this.brec_pro_id = brec_pro_id;
        this.brec_start_time = brec_start_time;
        this.brec_total_time = brec_total_time;
        this.brec_ip = brec_ip;
    }

    private int brec_id;
    private int brec_user_id;
    private int brec_pro_id;
    private String brec_start_time;
    private String brec_total_time;
    private String brec_ip;
}
