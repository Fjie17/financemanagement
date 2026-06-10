package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // 加载驱动并建立连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 加载 MySQL JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/linyi?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8", "root", "123456");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 驱动未找到: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        }
        return connection;
    }

    // 关闭连接
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败: " + e.getMessage());
            }
        }
    }
}
