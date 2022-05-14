package com.wei.entity;

public class U_operation {
    private int uope_id;
    private int uope_user_id;
    private String uope_action;
    private String uope_start_time;
    private String uope_total_time;
    private String uope_ip;

    public U_operation() {
    }

    public U_operation(int uope_user_id, String uope_action, String uope_start_time, String uope_total_time, String uope_ip) {
        this.uope_user_id = uope_user_id;
        this.uope_action = uope_action;
        this.uope_start_time = uope_start_time;
        this.uope_total_time = uope_total_time;
        this.uope_ip = uope_ip;
    }

    public U_operation(int uope_id, int uope_user_id, String uope_action, String uope_start_time, String uope_total_time, String uope_ip) {
        this.uope_id = uope_id;
        this.uope_user_id = uope_user_id;
        this.uope_action = uope_action;
        this.uope_start_time = uope_start_time;
        this.uope_total_time = uope_total_time;
        this.uope_ip = uope_ip;
    }

    public int getUope_id() {
        return uope_id;
    }

    public void setUope_id(int uope_id) {
        this.uope_id = uope_id;
    }

    public int getUope_user_id() {
        return uope_user_id;
    }

    public void setUope_user_id(int uope_user_id) {
        this.uope_user_id = uope_user_id;
    }

    public String getUope_action() {
        return uope_action;
    }

    public void setUope_action(String uope_action) {
        this.uope_action = uope_action;
    }

    public String getUope_start_time() {
        return uope_start_time;
    }

    public void setUope_start_time(String uope_start_time) {
        this.uope_start_time = uope_start_time;
    }

    public String getUope_total_time() {
        return uope_total_time;
    }

    public void setUope_total_time(String uope_total_time) {
        this.uope_total_time = uope_total_time;
    }

    public String getUope_ip() {
        return uope_ip;
    }

    public void setUope_ip(String uope_ip) {
        this.uope_ip = uope_ip;
    }

    @Override
    public String toString() {
        return "U_operation{" +
                "uope_id=" + uope_id +
                ", uope_user_id=" + uope_user_id +
                ", uope_action=" + uope_action +
                ", uope_start_time='" + uope_start_time + '\'' +
                ", uope_total_time='" + uope_total_time + '\'' +
                ", uope_ip='" + uope_ip + '\'' +
                '}';
    }
}
