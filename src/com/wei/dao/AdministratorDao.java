package com.wei.dao;

import com.wei.entity.Administrator;
import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministratorDao { // 查
    // 由于全局只有一个管理员，所以不设置增加管理员的功能，因此也不设置删除的功能，需要的话请直接去数据库更改

    public Administrator select_by_id(int a_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Administrator administrator = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from administrator where a_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, a_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            String a_name = resultSet.getString("a_name");
            String a_password = resultSet.getString("a_password");
            String a_email = resultSet.getString("a_email");

            administrator = new Administrator(a_id, a_name, a_password, a_email);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return administrator;
    }
    public Administrator select_by_name(String a_name) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Administrator administrator = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from administrator where a_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, a_name);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int a_id = resultSet.getInt("a_id");
            String a_password = resultSet.getString("a_password");
            String a_email = resultSet.getString("a_email");

            administrator = new Administrator(a_id, a_name, a_password, a_email);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return administrator;
    }
}
