package com.wei.utils;

import java.sql.*;

public class DBUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private static final String JDBC_URL="jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=utf8&";
//        private static final String JDBC_URL="jdbc:mysql://120.25.87.166:3306/shopping?useUnicode=true&characterEncoding=utf8&";
    private static final String DB_User="wei";
//    private static final String DB_User="root";
    private static final String DB_Password="1234";
    //    private static final String DB_Password="e9fcc054c27860d0";
    static Connection conn=null;

    public static Connection getConnection() throws Exception
    {
        conn= DriverManager.getConnection(JDBC_URL, DB_User, DB_Password);
//        conn = DriverManager.getConnection("jdbc:mysql://120.25.87.166:3306/onlineshop", "wei", "1234");
        return conn;
    }
    public static void close(Statement statement, Connection connection){
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet result) {
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(result != null){
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
