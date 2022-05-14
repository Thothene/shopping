package com.wei.dao;

import com.wei.entity.B_record;
import com.wei.utils.DBUtils;
import com.wei.utils.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class B_recordDao { //增删改查
    // 不提供更改服务，信息如果插入是正确的就不应该遭受更改

    // 添加记录
    public int insert(int brec_user_id, int brec_pro_id, String brec_start_time, String brec_total_time, String brec_ip)
            throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into b_record(brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip) value(?,?,?,?,?);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_user_id);
        preparedStatement.setInt(2, brec_pro_id);
        preparedStatement.setTimestamp(3, DateUtils.StringToDate(brec_start_time));
        preparedStatement.setString(4, brec_total_time);
        preparedStatement.setString(5, brec_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 通过浏览记录的id删除
    public int delete_by_id(int brec_id) throws Exception {
     PreparedStatement preparedStatement = null;
     Connection connection = null;
     int result = 0;

     connection = (Connection) DBUtils.getConnection();
     String sql = "delete from b_record where brec_id = ?;";
     preparedStatement = connection.prepareStatement(sql);
     preparedStatement.setInt(1, brec_id);
     result = preparedStatement.executeUpdate();

     DBUtils.close(preparedStatement, connection);
     return result;
    }

    // 通过用户id删除
    public int delete_by_user_id(int brec_user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from b_record where brec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 通过商品id删除
    public int delete_by_pro_id(int brec_pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from b_record where brec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 通过用户id和商品id删除
    public int delete_by_user_pro_id(int brec_user_id, int brec_pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from b_record where brec_user_id = ? and brec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_user_id);
        preparedStatement.setInt(2, brec_pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 通过浏览记录的id查询
    public B_record search_by_id(int brec_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record where brec_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int brec_user_id = resultSet.getInt("brec_user_id");
            int brec_pro_id = resultSet.getInt("brec_pro_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return b_record;
    }

    // 通过用户id查询
    public List<B_record> search_by_user_id(int brec_user_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;
        List<B_record> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record where brec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_user_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int brec_id = resultSet.getInt("brec_id");
            int brec_pro_id = resultSet.getInt("brec_pro_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
            list.add(b_record);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 通过用户名字
    public List<B_record> search_by_user_name(String user_name) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;
        UserDao userDao = new UserDao();
        List<B_record> list = new ArrayList<>();

        int brec_user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record where brec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_user_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int brec_id = resultSet.getInt("brec_id");
            int brec_pro_id = resultSet.getInt("brec_pro_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
            list.add(b_record);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 通过商品id查询
    public List<B_record> search_by_pro_id(int brec_pro_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;
        List<B_record> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record where brec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_pro_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int brec_id = resultSet.getInt("brec_id");
            int brec_user_id = resultSet.getInt("brec_user_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
            list.add(b_record);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 通过商品名称查询!!!没写完
    public List<B_record> search_by_pro_name(int pro_name) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;
        List<B_record> list = new ArrayList<>();

        int brec_pro_id = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record where brec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_pro_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int brec_id = resultSet.getInt("brec_id");
            int brec_user_id = resultSet.getInt("brec_user_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
            list.add(b_record);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }
    // 通过用户和商品的id查询
    public B_record search_by_user_pro_id(int brec_user_id, int brec_pro_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record where brec_user_id = ? and brec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, brec_user_id);
        preparedStatement.setInt(2, brec_pro_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int brec_id = resultSet.getInt("brec_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return b_record;
    }

    // 查找所有记录
    public List<B_record> search_all() throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        B_record b_record = null;
        List<B_record> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from b_record;";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int brec_id = resultSet.getInt("brec_id");
            int brec_user_id = resultSet.getInt("brec_user_id");
            int brec_pro_id = resultSet.getInt("brec_pro_id");
            String brec_start_time = resultSet.getString("brec_start_time");
            String brec_total_time = resultSet.getString("brec_total_time");
            String brec_ip = resultSet.getString("brec_ip");

            b_record = new B_record(brec_id, brec_user_id, brec_pro_id, brec_start_time, brec_total_time, brec_ip);
            list.add(b_record);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }
}
