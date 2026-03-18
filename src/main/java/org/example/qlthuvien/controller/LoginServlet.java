package org.example.qlthuvien.controller;

import org.example.qlthuvien.dao.user.UserDao;
import org.example.qlthuvien.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pass=userDao.get_pass(username);
        if(pass!=null && BCrypt.checkpw(password, pass)){
            User user=userDao.checkUser(username,pass);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/lib");
        }
        else
        {request.setAttribute("message", "Sai ten dang nhap hoac mat khau");
            RequestDispatcher dispatcher1 = request.getRequestDispatcher("WEB-INF/login.jsp");
            dispatcher1.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
        dispatcher.forward(request,response);
    }
}
