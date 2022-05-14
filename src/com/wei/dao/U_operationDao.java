package com.wei.dao;

import com.wei.entity.Salesperson;
import com.wei.entity.U_operation;
import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class U_operationDao { //增删查

    // 添加一条新的用户操作记录
    public int insert_log_in(int uope_user_id, String uope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into u_operation(uope_user_id, uope_action, uope_start_time, uope_ip)" +
                "value(?,'log_in',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uope_user_id);
        preparedStatement.setString(2, uope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_log_out(int uope_user_id, String uope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into u_operation(uope_user_id, uope_action, uope_start_time, uope_ip)" +
                "value(?,'log_out',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uope_user_id);
        preparedStatement.setString(2, uope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_browse(int uope_user_id, String uope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into u_operation(uope_user_id, uope_action, uope_start_time, uope_ip)" +
                "value(?,'browse',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uope_user_id);
        preparedStatement.setString(2, uope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_purchase(int uope_user_id, String uope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into u_operation(uope_user_id, uope_action, uope_start_time, uope_ip)" +
                "value(?,'purchase',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uope_user_id);
        preparedStatement.setString(2, uope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert_search(int uope_user_id, String uope_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into u_operation(uope_user_id, uope_action, uope_start_time,uope_ip)" +
                "value(?,'search',NOW(),?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uope_user_id);
        preparedStatement.setString(2, uope_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id删除用户操作记录
    public int delete(int user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from u_operation where uope_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户名字删除用户操作记录
    public int delete(String user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        UserDao userDao = new UserDao();
        int result = 0;

        int user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from u_operation where uope_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id查找记录
    public List<U_operation> select_by_user_id(int user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        U_operation u_operation = null;
        List<U_operation> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from u_operation where uope_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int uope_id = resultSet.getInt("uope_id");
            int uope_user_id = resultSet.getInt("uope_user_id");
            String uope_action = resultSet.getNString("uope_action");
            String uope_start_time = resultSet.getString("uope_start_time");
            String uope_total_time = resultSet.getNString("uope_total_time");
            String uope_ip = resultSet.getNString("uope_ip");

            u_operation = new U_operation(uope_user_id, uope_user_id, uope_action, uope_start_time, uope_total_time, uope_ip);
            list.add(u_operation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户名字查找记录
    public List<U_operation> select_by_user_name(String user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        U_operation u_operation = null;
        List<U_operation> list = new ArrayList<>();
        UserDao userDao = new UserDao();
        ResultSet resultSet = null;

        int user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from u_operation where uope_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int uope_id = resultSet.getInt("uope_id");
            int uope_user_id = resultSet.getInt("uope_user_id");
            String uope_action = resultSet.getNString("uope_action");
            String uope_start_time = resultSet.getString("uope_start_time");
            String uope_total_time = resultSet.getNString("uope_total_time");
            String uope_ip = resultSet.getNString("uope_ip");

            u_operation = new U_operation(uope_user_id, uope_user_id, uope_action, uope_start_time, uope_total_time, uope_ip);
            list.add(u_operation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户id和行为查找记录
    public List<U_operation> select_by_user_id_action(int user_id, String user_action) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        U_operation u_operation = null;
        List<U_operation> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from u_operation where uope_user_id = ? and uope_action = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setString(2, user_action);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int uope_id = resultSet.getInt("uope_id");
            int uope_user_id = resultSet.getInt("uope_user_id");
            String uope_action = resultSet.getNString("uope_action");
            String uope_start_time = resultSet.getString("uope_start_time");
            String uope_total_time = resultSet.getNString("uope_total_time");
            String uope_ip = resultSet.getNString("uope_ip");

            u_operation = new U_operation(uope_user_id, uope_user_id, uope_action, uope_start_time, uope_total_time, uope_ip);
            list.add(u_operation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户名字和行为查找记录
    public List<U_operation> select_by_user_name_action(String user_name, String user_action) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        U_operation u_operation = null;
        List<U_operation> list = new ArrayList<>();
        UserDao userDao = new UserDao();
        ResultSet resultSet = null;

        int user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from u_operation where uope_user_id = ? and uope_action = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setString(2, user_action);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int uope_id = resultSet.getInt("uope_id");
            int uope_user_id = resultSet.getInt("uope_user_id");
            String uope_action = resultSet.getNString("uope_action");
            String uope_start_time = resultSet.getString("uope_start_time");
            String uope_total_time = resultSet.getNString("uope_total_time");
            String uope_ip = resultSet.getNString("uope_ip");

            u_operation = new U_operation(uope_user_id, uope_user_id, uope_action, uope_start_time, uope_total_time, uope_ip);
            list.add(u_operation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据行为查找记录
    public List<U_operation> select_by_action(String user_action) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        U_operation u_operation = null;
        List<U_operation> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from u_operation where uope_action = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user_action);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int uope_id = resultSet.getInt("uope_id");
            int uope_user_id = resultSet.getInt("uope_user_id");
            String uope_action = resultSet.getNString("uope_action");
            String uope_start_time = resultSet.getString("uope_start_time");
            String uope_total_time = resultSet.getNString("uope_total_time");
            String uope_ip = resultSet.getNString("uope_ip");

            u_operation = new U_operation(uope_user_id, uope_user_id, uope_action, uope_start_time, uope_total_time, uope_ip);
            list.add(u_operation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据IP查找记录
    public List<U_operation> select_by_ip(String user_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        U_operation u_operation = null;
        List<U_operation> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from u_operation where uope_ip = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user_ip);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int uope_id = resultSet.getInt("uope_id");
            int uope_user_id = resultSet.getInt("uope_user_id");
            String uope_action = resultSet.getNString("uope_action");
            String uope_start_time = resultSet.getString("uope_start_time");
            String uope_total_time = resultSet.getNString("uope_total_time");
            String uope_ip = resultSet.getNString("uope_ip");

            u_operation = new U_operation(uope_user_id, uope_user_id, uope_action, uope_start_time, uope_total_time, uope_ip);
            list.add(u_operation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }
}
