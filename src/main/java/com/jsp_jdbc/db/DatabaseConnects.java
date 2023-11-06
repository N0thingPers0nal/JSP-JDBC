package com.jsp_jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnects {

    private final static String connectionUrl = "jdbc:mysql://localhost:3306/demo";
    private final static String username = "root";
    private final static String password = "root";

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
//            System.out.println("Connection "+connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }

}
