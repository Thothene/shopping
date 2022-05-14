package com.wei.dao;

import com.mysql.jdbc.Connection;
import com.wei.entity.P_record;
import com.wei.entity.User;
import com.wei.utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao { // 增删改查

    // 向用户数据表中插入一条用户数据，是否激活由标志位决定
    public int insert(String user_name, String user_password, String user_email, int sign_aactivation){
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;
        // 默认情况等于不激活，只有当标志位为1时才激活
        int user_activation = 0;
        if (sign_aactivation == 1)
            user_activation = 1;
        try {
            connection = (Connection) DBUtils.getConnection();
            String sql = "INSERT INTO users(user_name, user_password, user_email, user_reg_time, user_activation) VALUE(?,?,?,NOW(),?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, user_password);
            preparedStatement.setString(3, user_email);
            preparedStatement.setInt(4, user_activation);
            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 删除用户，也即 将用户激活位置0，其他数据表中的数据不做变化
    public int update_sign_by_id(int user_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;
        connection = (Connection) DBUtils.getConnection();
        String sql = "UPDATE users SET user_activation=? WHERE user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, user_id);
        result = preparedStatement.executeUpdate();
        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_sign_by_name(String user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;
        connection = (Connection) DBUtils.getConnection();
        String sql = "UPDATE users SET user_activation=? WHERE user_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 0);
        preparedStatement.setString(2, user_name);
        result = preparedStatement.executeUpdate();
        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 彻底删除用户，不推荐使用
    public int delete_by_id(int user_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        B_recordDao b_recordDao = new B_recordDao();
        CartDao cartDao = new CartDao();
        P_recordDao p_recordDao = new P_recordDao();
        U_operationDao u_operationDao = new U_operationDao();
        int result = 0;

        // 其他相关联的表彻底删除相关数据，包括：浏览记录表、购物车表、购买记录表、用户操作表
        b_recordDao.delete_by_user_id(user_id);
        cartDao.delete_by_user_id(user_id);
        p_recordDao.delete_by_user_id(user_id);
        u_operationDao.delete(user_id);

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from users where user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        result = preparedStatement.executeUpdate();
        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int delete_by_name(String user_name) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        UserDao userDao = new UserDao();
        B_recordDao b_recordDao = new B_recordDao();
        CartDao cartDao = new CartDao();
        P_recordDao p_recordDao = new P_recordDao();
        U_operationDao u_operationDao = new U_operationDao();
        int result = 0;

        // 其他相关联的表彻底删除相关数据，包括：浏览记录表、购物车表、购买记录表、用户操作表
        int user_id = userDao.select_self_by_name(user_name).getUser_id();
        b_recordDao.delete_by_user_id(user_id);
        cartDao.delete_by_user_id(user_id);
        p_recordDao.delete_by_user_id(user_id);
        u_operationDao.delete(user_id);

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from users where user_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user_name);
        result = preparedStatement.executeUpdate();
        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 查询个人信息
    public User select_self_by_id(int user_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        User user = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from users where user_id = ?;";
        preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            String user_name = resultSet.getString("user_name");
            String user_password = resultSet.getString("user_password");
            String user_email = resultSet.getString("user_email");
            String user_reg_time = resultSet.getString("user_reg_time");
            int user_activation = resultSet.getInt("user_activation");

            user = new User(user_id, user_name, user_password, user_email, user_reg_time, user_activation);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return user;
    }
    public User select_self_by_name(String user_name) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        User user = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from users where user_name = ?;";
        preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
        preparedStatement.setString(1, user_name);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int user_id = resultSet.getInt("user_id");
            String user_password = resultSet.getString("user_password");
            String user_email = resultSet.getString("user_email");
            String user_reg_time = resultSet.getString("user_reg_time");
            int user_activation = resultSet.getInt("user_activation");

            user = new User(user_id, user_name, user_password, user_email, user_reg_time, user_activation);
        }
        DBUtils.close(preparedStatement, connection, resultSet);
        return user;
    }
}
