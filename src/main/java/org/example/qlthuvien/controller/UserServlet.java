package org.example.qlthuvien.controller;

import org.example.qlthuvien.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="HomeServlet",urlPatterns = "/home")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "change-password":
                try {
                    response.sendRedirect("/login");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "logout":
                try{
                    response.sendRedirect("/login");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "change-pass":
                  userService.Showchange_pass(request, response);
                  break;
            case "update-user":
                  userService.showupdate_user(request, response);
                      break;
             case "select-user":
                 userService.showselect_user(request, response);
                 break;
             case "delete-user":
                     userService.delete_user(request, response);
                     break;
             case "restore-user":
                     userService.restore(request, response);
                     break;
            default:
                request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }
        switch (action) {
            case "logout":
                try{
                    response.sendRedirect("/login");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
                case "change-pass":
                    userService.change_pass(request, response);
                    break;
                case "update-user":
                    userService.update_user(request, response);
                    break;
                case "delete-user":
                    userService.delete_user(request, response);
                    break;
//                case "Change password":
//                    Changepassword(request, response);
//                    break;

        }
    }

}
