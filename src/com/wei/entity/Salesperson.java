package com.wei.entity;

public class Salesperson {
    private int sal_id;
    private String sal_name;
    private String sal_password;
    private String sal_email;
    private int sal_a_id;
    private int sal_activation;

    public Salesperson() {
    }

    public Salesperson(String sal_name, String sal_password, String sal_email, int sal_a_id, int sal_activation) {
        this.sal_name = sal_name;
        this.sal_password = sal_password;
        this.sal_email = sal_email;
        this.sal_a_id = sal_a_id;
        this.sal_activation = sal_activation;
    }

    public Salesperson(int sal_id, String sal_name, String sal_password, String sal_email, int sal_a_id, int sal_activation) {
        this.sal_id = sal_id;
        this.sal_name = sal_name;
        this.sal_password = sal_password;
        this.sal_email = sal_email;
        this.sal_a_id = sal_a_id;
        this.sal_activation = sal_activation;
    }

    public int getSal_id() {
        return sal_id;
    }

    public void setSal_id(int sal_id) {
        this.sal_id = sal_id;
    }

    public String getSal_name() {
        return sal_name;
    }

    public void setSal_name(String sal_name) {
        this.sal_name = sal_name;
    }

    public String getSal_password() {
        return sal_password;
    }

    public void setSal_password(String sal_password) {
        this.sal_password = sal_password;
    }

    public String getSal_email() {
        return sal_email;
    }

    public void setSal_email(String sal_email) {
        this.sal_email = sal_email;
    }

    public int getSal_a_id() {
        return sal_a_id;
    }

    public void setSal_a_id(int sal_a_id) {
        this.sal_a_id = sal_a_id;
    }

    public int getSal_activation() {
        return sal_activation;
    }

    public void setSal_activation(int sal_activation) {
        this.sal_activation = sal_activation;
    }

    @Override
    public String toString() {
        return "Salesperson{" +
                "sal_id=" + sal_id +
                ", sal_name='" + sal_name + '\'' +
                ", sal_password='" + sal_password + '\'' +
                ", sal_email='" + sal_email + '\'' +
                ", sal_a_id='" + sal_a_id + '\'' +
                ", sal_activation='" + sal_activation + '\'' +
                '}';
    }
}
