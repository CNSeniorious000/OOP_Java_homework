package com;

import java.sql.*;

public class JdbcUtil {
    static JdbcUtil instance = null;

    private JdbcUtil() {
    }

    public static JdbcUtil getJdbcUtil() {
        if (JdbcUtil.instance == null) JdbcUtil.instance = new JdbcUtil();
        return JdbcUtil.instance;
    }

    public Connection getConnection() {
        String url = "", sql_user = "", sql_password = "";
        try {
            return DriverManager.getConnection(url, sql_user, sql_password);
        } catch (SQLException ignored) {
            return null;
        }
    }

    public void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
    }

}
