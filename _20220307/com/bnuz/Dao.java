package com.bnuz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
    public Connection connection = null;
    public PreparedStatement state = null;
    public ResultSet result = null;
}
