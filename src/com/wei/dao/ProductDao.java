package com.wei.dao;

import com.wei.entity.Category;
import com.wei.entity.P_record;
import com.wei.entity.Product;
import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao { // 增删改查

    // 增加新的商品
    public int insert(String pro_name, String pro_info, String pro_image, int pro_number, float pro_price,
                      int pro_cat_id, int pro_sold) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into product(pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold) " +
                "value(?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, pro_name);
        preparedStatement.setString(2, pro_info);
        preparedStatement.setString(3, pro_image);
        preparedStatement.setInt(4, pro_number);
        preparedStatement.setFloat(5, pro_price);
        preparedStatement.setInt(6, pro_cat_id);
        preparedStatement.setInt(7, pro_sold);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 删除商品
    public int delete_by_id(int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        B_recordDao b_recordDao = new B_recordDao();
        CartDao cartDao = new CartDao();
        P_recordDao p_recordDao = new P_recordDao();
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        b_recordDao.delete_by_pro_id(pro_id);
        cartDao.delete_by_pro_id(pro_id);
        p_recordDao.delete_by_pro_id(pro_id);
        String sql = "delete from product where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int delete_by_name(String pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        B_recordDao b_recordDao = new B_recordDao();
        CartDao cartDao = new CartDao();
        P_recordDao p_recordDao = new P_recordDao();
        int result = 0;

        int pro_id = select_by_pro_name(pro_name).getPro_id();

        connection = (Connection) DBUtils.getConnection();
        b_recordDao.delete_by_pro_id(pro_id);
        cartDao.delete_by_pro_id(pro_id);
        cartDao.delete_by_pro_id(pro_id);
        p_recordDao.delete_by_pro_id(pro_id);
        String sql = "delete from product where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改商品名称
    public int update_pro_name(String old_pro_name, String new_pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_name = ? where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_pro_name);
        preparedStatement.setString(2, old_pro_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_pro_name(int old_pro_id, String new_pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_name = ? where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_pro_name);
        preparedStatement.setInt(2, old_pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改商品信息
    public int update_pro_info(String pro_name, String new_pro_info) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_info = ? where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_pro_info);
        preparedStatement.setString(2, pro_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_pro_info(int pro_id, String new_pro_info) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_info = ? where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_pro_info);
        preparedStatement.setInt(2, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改商品图片
    public int update_pro_image(String pro_name, String new_pro_image) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_image = ? where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_pro_image);
        preparedStatement.setString(2, pro_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_pro_image(int pro_id, String new_pro_image) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_image = ? where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_pro_image);
        preparedStatement.setInt(2, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改商品数量
    public int update_pro_number(String pro_name, int new_pro_number) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_number = ? where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, new_pro_number);
        preparedStatement.setString(2, pro_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_pro_number(int pro_id, int new_pro_number) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_number = ? where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, new_pro_number);
        preparedStatement.setInt(2, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改商品单价
    public int update_pro_price(String pro_name, float new_pro_price) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_price = ? where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, new_pro_price);
        preparedStatement.setString(2, pro_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_pro_price(int pro_id, float new_pro_price) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_price = ? where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, new_pro_price);
        preparedStatement.setInt(2, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改商品已售数量
    public int update_pro_sold(String pro_name, int new_pro_sold) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_sold = ? where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, new_pro_sold);
        preparedStatement.setString(2, pro_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_pro_sold(int pro_id, int new_pro_sold) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update product set pro_sold = ? where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, new_pro_sold);
        preparedStatement.setInt(2, pro_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据商品id搜索
    public Product select_by_pro_id(int pro_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Product product = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from product where pro_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pro_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            String pro_name = resultSet.getString("pro_name");
            String pro_info = resultSet.getString("pro_info");
            String pro_image = resultSet.getString("pro_image");
            int pro_number = resultSet.getInt("pro_number");
            float pro_price = resultSet.getFloat("pro_price");
            int pro_cat_id = resultSet.getInt("pro_cat_id");
            int pro_sold = resultSet.getInt("pro_sold");

            product = new Product(pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold);
        }

        return product;
    }

    // 根据商品名字搜索
    public Product select_by_pro_name(String pro_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Product product = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from product where pro_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, pro_name);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int pro_id = resultSet.getInt("pro_id");
            String pro_info = resultSet.getString("pro_info");
            String pro_image = resultSet.getString("pro_image");
            int pro_number = resultSet.getInt("pro_number");
            float pro_price = resultSet.getFloat("pro_price");
            int pro_cat_id = resultSet.getInt("pro_cat_id");
            int pro_sold = resultSet.getInt("pro_sold");

            product = new Product(pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return product;
    }

    // 根据类别搜索
    public List<Product> select_by_pro_cat_id(int cat_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from product where pro_cat_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cat_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int pro_id = resultSet.getInt("pro_id");
            String pro_name = resultSet.getString("pro_name");
            String pro_info = resultSet.getString("pro_info");
            String pro_image = resultSet.getString("pro_image");
            int pro_number = resultSet.getInt("pro_number");
            float pro_price = resultSet.getFloat("pro_price");
            int pro_sold = resultSet.getInt("pro_sold");

            product = new Product(pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, cat_id, pro_sold);
            list.add(product);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 根据商品价格
    public List<Product> select_by_pro_price(float lowwer_price, float upper_price) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from product where pro_price between ? and ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, lowwer_price);
        preparedStatement.setFloat(2, upper_price);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int pro_id = resultSet.getInt("pro_id");
            String pro_name = resultSet.getString("pro_name");
            String pro_info = resultSet.getString("pro_info");
            String pro_image = resultSet.getString("pro_image");
            int pro_number = resultSet.getInt("pro_number");
            float pro_price = resultSet.getFloat("pro_price");
            int pro_cat_id = resultSet.getInt("pro_cat_id");
            int pro_sold = resultSet.getInt("pro_sold");

            product = new Product(pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold);
            list.add(product);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    public List<Product> select_all_products() throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from product;";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int pro_id = resultSet.getInt("pro_id");
            String pro_name = resultSet.getString("pro_name");
            String pro_info = resultSet.getString("pro_info");
            String pro_image = resultSet.getString("pro_image");
            int pro_number = resultSet.getInt("pro_number");
            float pro_price = resultSet.getFloat("pro_price");
            int pro_cat_id = resultSet.getInt("pro_cat_id");
            int pro_sold = resultSet.getInt("pro_sold");

            product = new Product(pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold);
            list.add(product);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    public List<Product> recommend_product(int user_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> list = new ArrayList<>();

        connection = (Connection) DBUtils.getConnection();
        String sql = "SELECT brec_pro_id pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold, SUM(brec_total_time) FROM b_record, product WHERE brec_user_id = ? AND brec_pro_id = pro_id GROUP BY brec_pro_id;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);

        while (resultSet.next()){
            int pro_id = resultSet.getInt("pro_id");
            String pro_name = resultSet.getString("pro_name");
            String pro_info = resultSet.getString("pro_info");
            String pro_image = resultSet.getString("pro_image");
            int pro_number = resultSet.getInt("pro_number");
            float pro_price = resultSet.getFloat("pro_price");
            int pro_cat_id = resultSet.getInt("pro_cat_id");
            int pro_sold = resultSet.getInt("pro_sold");

            product = new Product(pro_id, pro_name, pro_info, pro_image, pro_number, pro_price, pro_cat_id, pro_sold);
            list.add(product);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

}
