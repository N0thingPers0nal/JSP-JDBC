package com.jsp_jdbc.dao;

import com.jsp_jdbc.db.DatabaseConnects;
import com.jsp_jdbc.model.Todo;
import com.jsp_jdbc.queries.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final Connection connection;

    public TodoDao() {
        connection = DatabaseConnects.getConnection();
    }

    public List<Todo> getUserTodos(int userId) {
//        System.out.println("123456789");
        List<Todo> todos = new ArrayList<Todo>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.userAllTodos);
//            System.out.println(2);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Todo todo = new Todo();
                todo.setId(resultSet.getInt("id"));
                todo.setUserId(resultSet.getInt("userId"));
                todo.setTodo(resultSet.getString("item"));
                todos.add(todo);
//                System.out.println(todo.getTodo()+" "+todo.getUserId()+" "+todo.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(todos);
        return todos;
    }

    public void addTodo(int userId, String todo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.insertToTodo);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, todo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTodo(int userId, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.deleteTodo);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
