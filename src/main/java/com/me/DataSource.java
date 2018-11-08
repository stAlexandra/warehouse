package com.me;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String url = "jdbc:mysql://localhost:3306/warehouse";
    private static final String login = "root";
    private static final String password = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    public void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
