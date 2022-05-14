package com.wei.dao;

import com.wei.entity.Category;
import com.wei.entity.Product;
import com.wei.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao { // 增删改查

    // 增加新类
    public int insert(String cat_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into category(cat_name, cat_sal_id) value(?, 1);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cat_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int insert(String cat_name, int cat_sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into category(cat_name, cat_sal_id) value(?, ?);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cat_name);
        preparedStatement.setInt(2, cat_sal_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 删除已有类
    public int delete_by_id(int cat_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        ProductDao productDao = new ProductDao();
        List<Product> list = productDao.select_by_pro_cat_id(cat_id);
        if (list.isEmpty()) {
            String sql = "delete from category where cat_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cat_id);
            result = preparedStatement.executeUpdate();
        }

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int delete_by_name(String cat_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        ProductDao productDao = new ProductDao();
        int cat_id = select_one_cat(cat_name).getCat_id();
        List<Product> list = productDao.select_by_pro_cat_id(cat_id);
        if (list.isEmpty()) {
            String sql = "delete from category where cat_name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cat_name);
            result = preparedStatement.executeUpdate();
        }

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改类名
    public int update_cat_name(String old_cat_name, String new_cat_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update category set cat_name = ? where cat_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_cat_name);
        preparedStatement.setString(2, old_cat_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_cat_name(int old_cat_id, String new_cat_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update category set cat_name = ? where cat_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_cat_name);
        preparedStatement.setInt(2, old_cat_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改类的所属销售员
    // 未指定的情况下将销售员暂定位根销售员
    // 指定情况下按指定修改
    public int update_cat_sal_id(int cat_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update category set cat_sal_id = 1 where cat_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cat_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_cat_sal_id(String cat_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update category set cat_sal_id = 1 where cat_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cat_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_cat_sal_id(int cat_id, int new_cat_sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update category set cat_sal_id = ? where cat_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, new_cat_sal_id);
        preparedStatement.setInt(2, cat_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_cat_sal_id(String cat_name, int new_cat_sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update category set cat_sal_id = ? where cat_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, new_cat_sal_id);
        preparedStatement.setString(2, cat_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 通过销售员id查询
    public List<Category> select_by_cat_sal_id(int cat_sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Category category = null;
        List<Category> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from category where cat_sal_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cat_sal_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int cat_id = resultSet.getInt("cat_id");
            String cat_name = resultSet.getString("cat_name");
            category = new Category(cat_id, cat_name, cat_sal_id);
            list.add(category);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 通过类别查询
    public Category select_one_cat(int cat_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Category category = null;
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from category where cat_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cat_id);
        resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            int cat_sal_id = resultSet.getInt("cat_sal_id");
            String cat_name = resultSet.getString("cat_name");
            category = new Category(cat_id, cat_name, cat_sal_id);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return category;
    }
    public Category select_one_cat(String cat_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Category category = null;
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from category where cat_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cat_name);
        resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            int cat_id = resultSet.getInt("cat_id");
            int cat_sal_id = resultSet.getInt("cat_sal_id");
            category = new Category(cat_id, cat_name, cat_sal_id);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return category;
    }
    public List<Category> select_all_cat() throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Category category = null;
        List<Category> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from category;";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int cat_id = resultSet.getInt("cat_id");
            String cat_name = resultSet.getString("cat_name");
            int cat_sal_id = resultSet.getInt("cat_sal_id");
            category = new Category(cat_id, cat_name, cat_sal_id);
            list.add(category);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 计算给定类的销售总金额
    public float count_category_money(int cat_id) throws Exception{
        ProductDao productDao = new ProductDao();
        PreparedStatement preparedStatement = null;
        Connection connection = (Connection) DBUtils.getConnection();
        List<Product> pro_list = null;
        ResultSet resultSet = null;
        float money = 0;

        pro_list = productDao.select_by_pro_cat_id(cat_id);
        for (Product product : pro_list){
            String sql = "SELECT SUM(prec_total_money) total_money FROM p_record WHERE prec_pro_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getPro_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                money += resultSet.getFloat("total_money");
            }
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return money;
    }

    // 计算给定类在某月的销售总金额
    public float count_category_month_money(int cat_id, String month) throws Exception{
        ProductDao productDao = new ProductDao();
        PreparedStatement preparedStatement = null;
        Connection connection = (Connection) DBUtils.getConnection();
        List<Product> pro_list = null;
        ResultSet resultSet = null;
        float money = 0;

        pro_list = productDao.select_by_pro_cat_id(cat_id);
        for (Product product : pro_list){
            String sql = "SELECT SUM(prec_total_money) total_money FROM p_record WHERE prec_pro_id = ? and MONTH(prec_time) = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getPro_id());
            preparedStatement.setString(2, month);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                money += resultSet.getFloat("total_money");
            }
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return money;
    }

    // 计算给定类的销售总量
    public int count_category_number(int cat_id) throws Exception{
        ProductDao productDao = new ProductDao();
        PreparedStatement preparedStatement = null;
        Connection connection = (Connection) DBUtils.getConnection();
        List<Product> pro_list = null;
        ResultSet resultSet = null;
        int number = 0;

        pro_list = productDao.select_by_pro_cat_id(cat_id);
        for (Product product : pro_list){
            String sql = "SELECT SUM(prec_number) total_number FROM p_record WHERE prec_pro_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getPro_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                number += resultSet.getInt("total_number");
            }
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return number;
    }

    // 计算给定类的库存,虽然是这么写，但是实际计算的是总量，和上面一个函数count_category_number配合使用可以计算库存
    public int count_category_kuncun_number(int cat_id) throws Exception{
        ProductDao productDao = new ProductDao();
        PreparedStatement preparedStatement = null;
        Connection connection = (Connection) DBUtils.getConnection();
        List<Product> pro_list = null;
        ResultSet resultSet = null;
        int number = 0;

        pro_list = productDao.select_by_pro_cat_id(cat_id);
        for (Product product : pro_list){
            String sql = "SELECT pro_number FROM product WHERE pro_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getPro_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                number += resultSet.getInt("pro_number");
            }
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return number;
    }

    // 计算给定类在某月的销售总量
    public float count_category_month_number(int cat_id, String month) throws Exception{
        ProductDao productDao = new ProductDao();
        PreparedStatement preparedStatement = null;
        Connection connection = (Connection) DBUtils.getConnection();
        List<Product> pro_list = null;
        ResultSet resultSet = null;
        int number = 0;

        pro_list = productDao.select_by_pro_cat_id(cat_id);
        for (Product product : pro_list){
            String sql = "SELECT SUM(prec_number) total_number FROM p_record WHERE prec_pro_id = ? and MONTH(prec_time) = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getPro_id());
            preparedStatement.setString(2, month);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                number += resultSet.getFloat("total_number");
            }
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return number;
    }
}
