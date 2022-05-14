package com.wei.dao;

import com.wei.entity.Category;
import com.wei.entity.P_record;
import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class P_recordDao { // 增删查

    // 添加新的购买记录
    public int insert(int prec_user_id, int prec_pro_id, int prec_number, float prec_total_money,
                      String prec_ip) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into p_record(prec_user_id, prec_pro_id, prec_time, prec_number, prec_total_money, prec_ip) " +
                "value(?, ?, NOW(), ?, ?, ?);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, prec_user_id);
        preparedStatement.setInt(2, prec_pro_id);
        preparedStatement.setInt(3, prec_number);
        preparedStatement.setFloat(4, prec_total_money);
        preparedStatement.setString(5, prec_ip);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id删除购买记录
    public int delete_by_user_id(int user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from p_record where prec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户名称删除购买记录
    public int delete_by_user_name(String user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        UserDao userDao = new UserDao();
        int result = 0;

        int user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from p_record where prec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据商品id删除购买记录
    public int delete_by_pro_id(int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from p_record where prec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据商品名称删除购买记录
    public int delete_by_pro_name(String pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ProductDao productDao = new ProductDao();
        int result = 0;

        int pro_id = productDao.select_by_pro_name(pro_name).getPro_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from p_record where prec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id查找购买记录
    public List<P_record> search_by_user_id(int user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        P_record p_record = null;
        List<P_record> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from p_record where prec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int prec_pro_id = resultSet.getInt("prec_pro_id");
            String prec_time = resultSet.getString("prec_time");
            int number = resultSet.getInt("prec_number");
            float prec_total_money = resultSet.getFloat("prec_total_money");
            String prec_ip = resultSet.getString("prec_ip");
            p_record = new P_record(user_id, prec_pro_id, prec_time, number, prec_total_money, prec_ip);
            list.add(p_record);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户名字查找购买记录
    public List<P_record> search_by_user_name(String user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        P_record p_record = null;
        List<P_record> list = new ArrayList<>();
        UserDao userDao = new UserDao();
        ResultSet resultSet = null;

        int user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from p_record where prec_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int prec_pro_id = resultSet.getInt("prec_pro_id");
            String prec_time = resultSet.getString("prec_time");
            int number = resultSet.getInt("prec_number");
            float prec_total_money = resultSet.getFloat("prec_total_money");
            String prec_ip = resultSet.getString("prec_ip");
            p_record = new P_record(user_id, prec_pro_id, prec_time, number, prec_total_money, prec_ip);
            list.add(p_record);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据商品id查找购买记录
    public List<P_record> search_by_pro_id(int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        P_record p_record = null;
        List<P_record> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from p_record where prec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int prec_user_id = resultSet.getInt("prec_user_id");
            String prec_time = resultSet.getString("prec_time");
            int number = resultSet.getInt("prec_number");
            float prec_total_money = resultSet.getFloat("prec_total_money");
            String prec_ip = resultSet.getString("prec_ip");
            p_record = new P_record(prec_user_id, pro_id, prec_time, number, prec_total_money, prec_ip);
            list.add(p_record);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据商品名称查找购买记录
    public List<P_record> search_by_pro_name(String pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        P_record p_record = null;
        List<P_record> list = new ArrayList<>();
        ProductDao productDao = new ProductDao();
        ResultSet resultSet = null;

        int pro_id = productDao.select_by_pro_name(pro_name).getPro_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from p_record where prec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int prec_user_id = resultSet.getInt("prec_user_id");
            String prec_time = resultSet.getString("prec_time");
            int number = resultSet.getInt("prec_number");
            float prec_total_money = resultSet.getFloat("prec_total_money");
            String prec_ip = resultSet.getString("prec_ip");
            p_record = new P_record(prec_user_id, pro_id, prec_time, number, prec_total_money, prec_ip);
            list.add(p_record);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户id和商品id查购买记录
    public List<P_record> search_by_user_pro_id(int user_id, int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        P_record p_record = null;
        List<P_record> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from p_record where prec_user_id = ? and prec_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, pro_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            String prec_time = resultSet.getString("prec_time");
            int number = resultSet.getInt("prec_number");
            float prec_total_money = resultSet.getFloat("prec_total_money");
            String prec_ip = resultSet.getString("prec_ip");
            p_record = new P_record(user_id, pro_id, prec_time, number, prec_total_money, prec_ip);
            list.add(p_record);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }
}
