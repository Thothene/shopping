package com.wei.entity;

public class S_operation {
    private int sope_id;
    private int sope_sal_id;
    private String sope_action;
    private String sope_start_time;
    private String sope_ip;

    public S_operation() {
    }

    public S_operation(int sope_id, int sope_sal_id, String sope_action, String sope_start_time, String sope_ip) {
        this.sope_id = sope_id;
        this.sope_sal_id = sope_sal_id;
        this.sope_action = sope_action;
        this.sope_start_time = sope_start_time;
        this.sope_ip = sope_ip;
    }

    public int getSope_id() {
        return sope_id;
    }

    public void setSope_id(int sope_id) {
        this.sope_id = sope_id;
    }

    public int getSope_sal_id() {
        return sope_sal_id;
    }

    public void setSope_sal_id(int sope_sal_id) {
        this.sope_sal_id = sope_sal_id;
    }

    public String getSope_action() {
        return sope_action;
    }

    public void setSope_action(String sope_action) {
        this.sope_action = sope_action;
    }

    public String getSope_start_time() {
        return sope_start_time;
    }

    public void setSope_start_time(String sope_start_time) {
        this.sope_start_time = sope_start_time;
    }

    public String getSope_ip() {
        return sope_ip;
    }

    public void setSope_ip(String sope_ip) {
        this.sope_ip = sope_ip;
    }

    @Override
    public String toString() {
        return "S_operation{" +
                "sope_id=" + sope_id +
                ", sope_sal_id=" + sope_sal_id +
                ", sope_action='" + sope_action + '\'' +
                ", sope_start_time='" + sope_start_time + '\'' +
                ", sope_ip='" + sope_ip + '\'' +
                '}';
    }
}
