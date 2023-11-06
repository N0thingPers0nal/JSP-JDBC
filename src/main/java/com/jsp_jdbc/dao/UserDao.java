package com.jsp_jdbc.dao;

import com.jsp_jdbc.db.DatabaseConnects;
import com.jsp_jdbc.model.User;
import com.jsp_jdbc.queries.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        connection = DatabaseConnects.getConnection();
    }

    public User isLoggedIn(String name, String password) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.selectUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean register(String name, String email, String password, String cPassword) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(Queries.getAllUsers);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("email"), email)) {
                    return true;
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.addUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
