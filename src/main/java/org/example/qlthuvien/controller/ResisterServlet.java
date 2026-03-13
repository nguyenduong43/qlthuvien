package org.example.qlthuvien.controller;

import org.example.qlthuvien.dao.user.UserDao;
import org.example.qlthuvien.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name="ResisterServlet",urlPatterns = "/register")
public class ResisterServlet extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/register.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<User> users = userDao.selectAllUsers();
        HttpSession session = request.getSession(false);
        String username = request.getParameter("username");
        for (User user1 : users) {
            if (username.equals(user1.getUsername())) {
                session.setAttribute("message","username da ton tai");
                response.sendRedirect("/register");
                return;
            }
        }

        String email = request.getParameter("email");
        for (User user1 : users) {
            if (email.equals(user1.getEmail())) {
                session.setAttribute("message","email da ton tai");
                response.sendRedirect("/register");
                return;
            }
        }

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dateStr = request.getParameter("date");
        LocalDate birthday = LocalDate.parse(dateStr);
        String phone = request.getParameter("phone");
        String image = request.getParameter("image");
        String position = request.getParameter("position");
        //User user = new User(0,name,birthday,email,phone,image,username,password,position);
        User user=new User.Builder().name(name).birthday(birthday).email(email).phone(phone).image(image).username(username).password(password).position(position).build();
        userDao.registerUser(user);
        //HttpSession session = request.getSession(false);
         session.setAttribute("message","dang ky thanh cong");
        //request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,response);
        response.sendRedirect("/register");
    }
}
