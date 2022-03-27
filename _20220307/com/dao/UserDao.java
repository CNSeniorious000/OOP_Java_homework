package com.dao;

import com.JdbcUtil;
import com.bnuz.Dao;
import com.bnuz.User;

import java.sql.SQLException;

public class UserDao extends Dao {
    User user = null;

    public User login(String name, String password) {
        try {
            connection = JdbcUtil.getJdbcUtil().getConnection();
            state = connection.prepareStatement("select name, password from t_users where name = ? and password = ?");
            state.setString(1, name);
            state.setString(2, password);
            result = state.executeQuery();
            if (result.next()) user = new User(result.getString("name"), result.getString("password"));
        } catch (SQLException ignored) {
        } finally {
            JdbcUtil.getJdbcUtil().closeConnection(result, state, connection);
        }
        return user;
    }
}
