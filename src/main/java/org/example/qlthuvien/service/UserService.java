package org.example.qlthuvien.service;

import org.example.qlthuvien.dao.user.UserDao;
import org.example.qlthuvien.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class UserService implements IuserService {
    private final UserDao userDao = new UserDao();

    @Override
    public void Showchange_pass(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/change_pass.jsp");
        try{
            dispatcher.forward(request,response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void change_pass(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String pass=user.getPassword();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        if(pass.equals(oldPassword)) {
            userDao.changePassword(userId, newPassword);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
            try {
                response.sendRedirect("/login");
//                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } {request.setAttribute("message","Sai mat khau");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/change_pass.jsp");
        try {
           dispatcher.forward(request, response);
           // response.sendRedirect("/home?action=change-pass");
        } catch (Exception e) {e.printStackTrace();}
        }

    }

    @Override
    public void showupdate_user(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/update_user.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update_user(HttpServletRequest request, HttpServletResponse response) {
         String name = request.getParameter("name");
        String dateStr = request.getParameter("date");
        LocalDate birthday = LocalDate.parse(dateStr);
        String email = request.getParameter("email");
        String phone=request.getParameter("phone");
        String image=request.getParameter("image");
        HttpSession session = request.getSession(false);
        User user1 = (User) session.getAttribute("user");
        int id=user1.getId();
        String position=user1.getPosition();
       // User user=new User(id,name,birthday,email,phone,image,position);
        User user=new User.Builder().id(id).name(name).birthday(birthday).email(email).phone(phone).image(image).position(position).build();
        userDao.updateUser(user);
        session.setAttribute("user",user);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
        try {
            response.sendRedirect("/lib");
           // dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login_admin(HttpServletRequest request, HttpServletResponse response) {

    }
    @Override
    public void showselect_user(HttpServletRequest request, HttpServletResponse response) {
        int page=1;
        int limit=5;
        String pageStr = request.getParameter("page");
        if(pageStr != null){
            page = Integer.parseInt(pageStr);
        }
        int offset = (page - 1) * limit;
       // List<User> users1=userDao.selectAllUsers();
        int i=userDao.count();
        List<User> users = userDao.selectAllUser1(limit,offset);
        request.setAttribute("users", users);
        request.setAttribute("totalPages", i / limit + 1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view_user.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void select_user(HttpServletRequest request, HttpServletResponse response) {

    }
    @Override
    public void delete_user(HttpServletRequest request, HttpServletResponse response) {
       int id=Integer.parseInt(request.getParameter("id"));
       try{
           userDao.soft_delete(id);
           response.sendRedirect("/home?action=select-user");
       }catch (Exception e){
           e.printStackTrace();
       }


    }

    @Override
    public void restore(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        try{
            userDao.restore(id);
            response.sendRedirect("/home?action=select-user");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
