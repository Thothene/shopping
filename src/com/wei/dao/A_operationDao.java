package com.wei.dao;

import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class A_operationDao {
    // 插入登陆操作
    public int insert_log_in(int aope_aid, String aope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into a_operation(aope_aid, aope_action, aope_start_time, aope_ip)" +
                "value(?,'log_in',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, aope_aid);
        preparedStatement.setString(2, aope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_log_out(int aope_aid, String aope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into a_operation(aope_aid, aope_action, aope_start_time, aope_ip)" +
                "value(?,'log_out',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, aope_aid);
        preparedStatement.setString(2, aope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_search(int aope_aid, String aope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into a_operation(aope_aid, aope_action, aope_start_time, aope_ip)" +
                "value(?,'search',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, aope_aid);
        preparedStatement.setString(2, aope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_update(int aope_aid, String aope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into a_operation(aope_aid, aope_action, aope_start_time, aope_ip)" +
                "value(?,'update',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, aope_aid);
        preparedStatement.setString(2, aope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
}
