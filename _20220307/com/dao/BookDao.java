package com.dao;

import com.bnuz.Book;
import com.bnuz.Dao;
import com.JdbcUtil;

import java.sql.SQLException;
import java.util.LinkedList;

public class BookDao extends Dao {
    Book book = null;

    public LinkedList<Book> searchByTitle(String title) {
        var ans = new LinkedList<Book>();
        try {
            connection = JdbcUtil.getJdbcUtil().getConnection();
            state = connection.prepareStatement("select * from library where title like %?%");  // TODO
            state.setString(1, title);
            result = state.executeQuery();
            while (result.next()) {
                ans.add(new Book(result.getString("ISBN"), result.getString("title"), result.getString("author")));
            }
        } catch (SQLException ignored) {
        } finally {
            JdbcUtil.getJdbcUtil().closeConnection(result, state, connection);
        }
        return ans;
    }

}
