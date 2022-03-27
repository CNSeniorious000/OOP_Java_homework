
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {
    JdbcUtil instance = null;

    private JdbcUtil() {
    }

    boolean closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        return false;
    }

    JdbcUtil getConnection() {
        if (instance == null) instance = new JdbcUtil();
        return instance;
    }

}
