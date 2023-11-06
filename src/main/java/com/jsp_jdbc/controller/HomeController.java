package com.jsp_jdbc.controller;

import com.jsp_jdbc.dao.TodoDao;
import com.jsp_jdbc.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
    private final TodoDao todoDao;
    private RequestDispatcher dispatcher;
    public HomeController() {
        todoDao = new TodoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        System.out.println(req.getContextPath()+"/home");
        int userId = (int) req.getSession().getAttribute("id");
        todoDao.deleteTodo(userId, id);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher = req.getRequestDispatcher("home.jsp");
        int userId = (int) req.getSession().getAttribute("id");
//        String userName= req.getSession().getAttribute("userName").toString();
        String todo = req.getParameter("todo");
//        System.out.println(userId);
//        System.out.println(req.getParameter("todo"));
        if (todo != null && !todo.trim().isEmpty()) {
            todoDao.addTodo(userId, todo);
        } else if (todo == null || todo.isEmpty()) {
            req.setAttribute("error", true);
        }
        List<Todo> todos = todoDao.getUserTodos(userId);
        req.setAttribute("todos", todos);
        dispatcher.forward(req, resp);
    }
}
