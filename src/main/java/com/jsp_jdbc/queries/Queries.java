package com.jsp_jdbc.queries;

public class Queries {
    public static String getAllUsers = "SELECT id,username,email,password FROM auth;";
    public static String selectUser = "SELECT id,username,email,password FROM auth WHERE email=? and password=?;";
    public static String userAllTodos = "SELECT id,userId,item FROM todo WHERE userid=? and delete_flag=false;";
    public static String insertToTodo = "INSERT INTO todo(userId,item) VALUES(?,?)";
    public static String deleteTodo = "UPDATE todo SET delete_flag=1 where userId=? and id=?;";
    public static String addUser = "INSERT INTO auth(username,email,password) VALUES(?,?,?);";
}
