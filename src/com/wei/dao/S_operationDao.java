package com.wei.dao;

import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class S_operationDao {
    // 插入登陆操作
    public int insert_log_in(int sope_sal_id, String sope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into s_operation(sope_sal_id, sope_action, sope_start_time, sope_ip)" +
                "value(?,'log_in',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sope_sal_id);
        preparedStatement.setString(2, sope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_log_out(int sope_sal_id, String sope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into s_operation(sope_sal_id, sope_action, sope_start_time, sope_ip)" +
                "value(?,'log_out',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sope_sal_id);
        preparedStatement.setString(2, sope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_search(int sope_sal_id, String sope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into s_operation(sope_sal_id, sope_action, sope_start_time, sope_ip)" +
                "value(?,'search',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sope_sal_id);
        preparedStatement.setString(2, sope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_update(int sope_sal_id, String sope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into s_operation(sope_sal_id, sope_action, sope_start_time, sope_ip)" +
                "value(?,'update',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sope_sal_id);
        preparedStatement.setString(2, sope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
}
