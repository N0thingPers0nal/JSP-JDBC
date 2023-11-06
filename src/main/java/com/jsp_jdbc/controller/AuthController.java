package com.jsp_jdbc.controller;

import com.jsp_jdbc.dao.TodoDao;
import com.jsp_jdbc.dao.UserDao;
import com.jsp_jdbc.model.Todo;
import com.jsp_jdbc.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AuthController extends HttpServlet {
    private final UserDao userDao;
    private final TodoDao todoDao;


    public AuthController() {
        userDao = new UserDao();
        todoDao = new TodoDao();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") != null) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = userDao.isLoggedIn(email, password);
//        System.out.println(user.getId());
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("id", user.getId());
                session.setAttribute("userName", user.getUsername());
                List<Todo> todos = todoDao.getUserTodos(user.getId());
                req.setAttribute("todos", todos);
                RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("error", true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String c_password = req.getParameter("c_password");
            if (Objects.equals(password, c_password)) {
                boolean newUser = userDao.register(name, email, password, c_password);
                if (!newUser) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                    req.setAttribute("newUser", true);
                    dispatcher.forward(req, resp);
                }
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                req.setAttribute("passwordMissMatch", true);
                dispatcher.forward(req, resp);
            }
        }
    }
}
