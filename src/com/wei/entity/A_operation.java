package com.wei.entity;

public class A_operation {
    private int aope_id;
    private int aope_aid;
    private String aope_action;
    private String aope_start_time;
    private String aope_ip;

    public A_operation() {
    }

    public A_operation(int aope_id, int aope_aid, String aope_action, String aope_start_time, String aope_ip) {
        this.aope_id = aope_id;
        this.aope_aid = aope_aid;
        this.aope_action = aope_action;
        this.aope_start_time = aope_start_time;
        this.aope_ip = aope_ip;
    }

    public int getAope_id() {
        return aope_id;
    }

    public void setAope_id(int aope_id) {
        this.aope_id = aope_id;
    }

    public int getAope_aid() {
        return aope_aid;
    }

    public void setAope_aid(int aope_aid) {
        this.aope_aid = aope_aid;
    }

    public String getAope_action() {
        return aope_action;
    }

    public void setAope_action(String aope_action) {
        this.aope_action = aope_action;
    }

    public String getAope_start_time() {
        return aope_start_time;
    }

    public void setAope_start_time(String aope_start_time) {
        this.aope_start_time = aope_start_time;
    }

    public String getAope_ip() {
        return aope_ip;
    }

    public void setAope_ip(String aope_ip) {
        this.aope_ip = aope_ip;
    }

    @Override
    public String toString() {
        return "A_operation{" +
                "aope_id=" + aope_id +
                ", aope_aid=" + aope_aid +
                ", aope_action='" + aope_action + '\'' +
                ", aope_start_time='" + aope_start_time + '\'' +
                ", aope_ip='" + aope_ip + '\'' +
                '}';
    }
}
