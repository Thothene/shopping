package com.wei.dao;

import com.wei.entity.Category;
import com.wei.entity.Salesperson;
import com.wei.utils.DBUtils;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SalespersonDao { // 增删查

    // 增加销售人员一条记录，是否激活由标志位决定
    public int insert(String sal_name, String sal_password, String sal_email, int sal_a_id, int sal_activation) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "insert into salesperson(sal_name, sal_password, sal_email, sal_a_id, sal_activation)" +
                "value(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, sal_name);
        preparedStatement.setString(2, sal_password);
        preparedStatement.setString(3, sal_email);
        preparedStatement.setInt(4, sal_a_id);
        preparedStatement.setInt(5, sal_activation);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改激活位置
    public int update_activation(int sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update salesperson set sal_activation = 0 where sal_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sal_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_activation(String sal_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update salesperson set sal_activation = 0 where sal_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, sal_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 修改口令
    public int update_password(int sal_id, String new_password, String old_password) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update salesperson set sal_password = ? where sal_id = ? and sal_password = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_password);
        preparedStatement.setInt(2, sal_id);
        preparedStatement.setString(3, old_password);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int update_password(String sal_name, String new_password, String old_password) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "update salesperson set sal_password = ? where sal_name = ? and sal_password = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_password);
        preparedStatement.setString(2, sal_name);
        preparedStatement.setString(3, old_password);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 彻底删除用户（不推荐使用），所管理的类别转交给根销售员
    public int delete(int sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from salesperson where sal_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sal_id);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }
    public int delete(String sal_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int result = 0;

        connection = (Connection) DBUtils.getConnection();
        String sql = "delete from salesperson where sal_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, sal_name);
        result = preparedStatement.executeUpdate();

        DBUtils.close(preparedStatement, connection);
        return result;
    }

    // 根据id查找销售员
    public Salesperson select(int sal_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Salesperson salesperson = null;
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from salesperson where sal_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sal_id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            String sal_name = resultSet.getString("sal_name");
            String sal_password = resultSet.getString("sal_password");
            String sal_email = resultSet.getString("sal_email");
            int sal_a_id = resultSet.getInt("sal_a_id");
            int sal_activation = resultSet.getInt("sal_activation");

            salesperson = new Salesperson(sal_id, sal_name, sal_password, sal_email, sal_a_id, sal_activation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return salesperson;
    }

    // 根据名字查找销售员
    public Salesperson select(String sal_name) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Salesperson salesperson = null;
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from salesperson where sal_name = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, sal_name);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            int sal_id = resultSet.getInt("sal_id");
            String sal_password = resultSet.getString("sal_password");
            String sal_email = resultSet.getString("sal_email");
            int sal_a_id = resultSet.getInt("sal_a_id");
            int sal_activation = resultSet.getInt("sal_activation");

            salesperson = new Salesperson(sal_id, sal_name, sal_password, sal_email, sal_a_id, sal_activation);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return salesperson;
    }

    // 根据管理员查询
    public List<Salesperson> select_by_a_id(int sal_a_id) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Salesperson salesperson = null;
        List<Salesperson> list = new ArrayList<>();
        ResultSet resultSet = null;

        connection = (Connection) DBUtils.getConnection();
        String sql = "select * from salesperson where sal_a_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, sal_a_id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int sal_id = resultSet.getInt("sal_id");
            String sal_name = resultSet.getString("sal_name");
            String sal_password = resultSet.getString("sal_password");
            String sal_email = resultSet.getString("sal_email");
            int sal_activation = resultSet.getInt("sal_activation");

            salesperson = new Salesperson(sal_id, sal_name, sal_password, sal_email, sal_a_id, sal_activation);
            list.add(salesperson);
        }

        DBUtils.close(preparedStatement, connection, resultSet);
        return list;
    }

    // 给一个销售员ID返回该销售员每月的销售额
    public List<Map<String, Float>> count_every_month_money(int sal_id) throws Exception {
        CategoryDao categoryDao = new CategoryDao();
        List<Map<String, Float>> month_list = new ArrayList<>();

        // 得到该销售员的销售种类列表
        List<Category> cat_list = categoryDao.select_by_cat_sal_id(sal_id);
        // 1~12月
        for(int month = 1; month <= 12; ++month) {
            Map m =new HashMap();
            float money = 0;
            for (Category category : cat_list) {
                // 所有种类在这个月的销售额
                money += categoryDao.count_category_month_money(category.getCat_id(), String.valueOf(month));
            }
            m.put(String.valueOf(month), money);
            month_list.add(m);
        }

        return month_list;
    }

    // 给一个销售员ID和种类ID返回该销售员每月这个种类的销售额
    public List<Map<String, Float>> count_every_month_category_money(int sal_id, int cat_id) throws Exception {
        CategoryDao categoryDao = new CategoryDao();
        List<Map<String, Float>> month_list = new ArrayList<>();

        // 1~12月
        for(int month = 1; month <= 12; ++month) {
            Map m =new HashMap();
            float money = 0;
            // 所有种类在这个月的销售额
            money += categoryDao.count_category_month_money(cat_id, String.valueOf(month));
            m.put(String.valueOf(month), money);
            month_list.add(m);
        }

        return month_list;
    }

}
