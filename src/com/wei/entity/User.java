package com.wei.entity;

public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_reg_time;
    private int user_activation;

    public User() {
    }

    public User(String user_name, String user_password, String user_email, String user_reg_time, int user_activation) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_reg_time = user_reg_time;
        this.user_activation = user_activation;
    }

    public User(int user_id, String user_name, String user_password, String user_email, String user_reg_time, int user_activation) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_reg_time = user_reg_time;
        this.user_activation = user_activation;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_reg_time() {
        return user_reg_time;
    }

    public void setUser_reg_time(String user_reg_time) {
        this.user_reg_time = user_reg_time;
    }

    public int getUser_activation() {
        return user_activation;
    }

    public void setUser_activation(int user_activation) {
        this.user_activation = user_activation;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_reg_time='" + user_reg_time + '\'' +
                ", user_activation=" + user_activation +
                '}';
    }
}
