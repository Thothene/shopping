package com.wei.dao;

import com.wei.entity.Cart;
import com.wei.entity.User;
import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDao { // 增删改查
    // 增加购物车记录
    public int insert(int cart_user_id, int cart_pro_id, int cart_pro_number) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        int result = 0;

        cart = select_by_user_pro_id(cart_user_id, cart_pro_id);
        if (cart == null){
            connection = (Connection) DBUtils.getConnection();
            String sql = "insert into cart(cart_user_id, cart_pro_id, cart_pro_number) value(?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cart_user_id);
            preparedStatement.setInt(2, cart_pro_id);
            preparedStatement.setInt(3, cart_pro_number);

            result = preparedStatement.executeUpdate();
        }
        else{
            result = update_cart_pro_number(cart_pro_number, cart_user_id, cart_pro_id);
        }

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id删除购物车记录
    public int delete_by_user_id(int cart_user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from cart where cart_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cart_user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户名字删除购物车记录
    public int delete_by_user_name(String cart_user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        UserDao userDao = new UserDao();
        int result = 0;

        int cart_user_id = userDao.select_self_by_name(cart_user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from cart where cart_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cart_user_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据商品id删除购物车记录
    public int delete_by_pro_id(int cart_pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from cart where cart_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cart_pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据商品名称删除购物车记录
    public int delete_by_pro_name(String cart_pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ProductDao productDao = null;
        int result = 0;

        int cart_pro_id = productDao.select_by_pro_name(cart_pro_name).getPro_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from cart where cart_pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cart_pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id和商品id删除记录
    public int delete_by_user_pro_id(int user_id, int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from cart where cart_user_id = ? and cart_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id和商品id更改商品数量
    public int update_cart_pro_number(int cart_pro_number, int user_id, int pro_id) throws Exception{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update cart set cart_pro_number = ? where cart_user_id = ? and cart_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,cart_pro_number);
        preparedStatement.setInt(2, user_id);
        preparedStatement.setInt(3, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据用户id查购物车记录
    public List<Cart> select_by_user_id(int user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        List<Cart> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from cart where cart_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int cart_pro_id = resultSet.getInt("cart_pro_id");
            int cart_pro_number = resultSet.getInt("cart_pro_number");

            cart = new Cart(user_id, cart_pro_id, cart_pro_number);
            list.add(cart);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户名字查购物车记录
    public List<Cart> select_by_user_name(String user_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        List<Cart> list = new ArrayList<>();
        UserDao userDao = null;
        ResultSet resultSet = null;

        int user_id = userDao.select_self_by_name(user_name).getUser_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from cart where cart_user_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int cart_pro_id = resultSet.getInt("cart_pro_id");
            int cart_pro_number = resultSet.getInt("cart_pro_number");

            cart = new Cart(user_id, cart_pro_id, cart_pro_number);
            list.add(cart);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据商品id查购物车记录
    public List<Cart> select_by_pro_id(int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        List<Cart> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from cart where cart_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int cart_user_id = resultSet.getInt("cart_user_id");
            int cart_pro_number = resultSet.getInt("cart_pro_number");

            cart = new Cart(cart_user_id, pro_id, cart_pro_number);
            list.add(cart);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据商品名称查购物车记录
    public List<Cart> select_by_pro_name(String pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        List<Cart> list = new ArrayList<>();
        ProductDao productDao = null;
        ResultSet resultSet = null;

        int pro_id = productDao.select_by_pro_name(pro_name).getPro_id();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from cart where cart_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int cart_user_id = resultSet.getInt("cart_user_id");
            int cart_pro_number = resultSet.getInt("cart_pro_number");

            cart = new Cart(cart_user_id, pro_id, cart_pro_number);
            list.add(cart);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据用户id和商品id查购物车记录
    public Cart select_by_user_pro_id(int user_id, int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from cart where cart_user_id = ? and cart_pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, pro_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int cart_pro_number = resultSet.getInt("cart_pro_number");
            cart = new Cart(user_id, pro_id, cart_pro_number);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return cart;
    }

    // 查找所有记录
    public List<Cart> select_all() throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Cart cart = null;
        List<Cart> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from cart;";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int cart_user_id = resultSet.getInt("cart_user_id");
            int cart_pro_id = resultSet.getInt("cart_pro_id");
            int cart_pro_number = resultSet.getInt("cart_pro_number");

            cart = new Cart(cart_user_id, cart_pro_id, cart_pro_number);
            list.add(cart);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }
}
