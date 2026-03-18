package org.example.qlthuvien.service.userservice;

import org.example.qlthuvien.dao.user.UserDao;
import org.example.qlthuvien.model.Customers;
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

    @Override
    public void showadd_customer(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/add_customer.jsp");
        try{
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void add_customer(HttpServletRequest request, HttpServletResponse response) {
           List<Customers> listCustomer=userDao.select_customerid();
           String customer_code=request.getParameter("customer_code");
           for(Customers c:listCustomer){
               if (c.getCustomer_code().equals(customer_code)) {
                   request.setAttribute("message", "Customer Id da ton tai");
                   RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/add_customer.jsp");
                   try {
                       dispatcher.forward(request, response);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                   return;
               }
           }
           String name=request.getParameter("name");
           String customer_class=request.getParameter("customer_class");
           String address=request.getParameter("address");
           LocalDate birthday=LocalDate.parse(request.getParameter("birthday"));
        Customers customer=new Customers.Builder().customer_code(customer_code).name(name).customer_class(customer_class).address(address).birthday(birthday).build();
        userDao.add_customer(customer);
        try{
            response.sendRedirect("/lib");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Showview_customer(HttpServletRequest request, HttpServletResponse response) {
        String search="";
        int limit=10;
        int page=1;
        String pageStr = request.getParameter("page");
        if(pageStr != null){
            page = Integer.parseInt(pageStr);
        }
        int offset = (page - 1) * limit;
        int i=userDao.count_customers(search);
        request.setAttribute("totalPages", i / limit + 1);
        List<Customers> customers=userDao.view_customers(search,limit,offset);
        request.setAttribute("customers",customers);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view_customer.jsp");
        try{
            dispatcher.forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void view_customer(HttpServletRequest request, HttpServletResponse response) {

             String search=request.getParameter("search");
             if (search==null){search="";}
        int limit=10;
        int page=1;
        String pageStr = request.getParameter("page");
        if(pageStr != null){
            page = Integer.parseInt(pageStr);
        }
        int offset = (page - 1) * limit;
        int i=userDao.count_customers(search);
        request.setAttribute("search1", search);
        request.setAttribute("totalPages", i / limit + 1);
        System.out.println(search);
             List<Customers> customers=userDao.view_customers(search,limit,offset);
             for(Customers c:customers){
                 System.out.println(c);
             }
             request.setAttribute("customers",customers);
             RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view_customer.jsp");
             try{
                 requestDispatcher.forward(request,response);
             }catch (Exception e){
                 e.printStackTrace();
             }

    }

    @Override
    public void showcreate_borrow(HttpServletRequest request, HttpServletResponse response) {
                List<Customers> listCustomer=userDao.select_customerid();
                request.setAttribute("listCustomer",listCustomer);
                List<String> listBook=userDao.select_namebook();
                request.setAttribute("listBook",listBook);
                RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/create_borrow.jsp");
                try{
                    dispatcher.forward(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }

    }

    @Override
    public void create_borrow(HttpServletRequest request, HttpServletResponse response) {
              String customer_code=request.getParameter("customer_code");
              String book_name=request.getParameter("book_name");
        LocalDate borrow_date=LocalDate.parse(request.getParameter("borrow_date"));
        userDao.add_borrow(customer_code,book_name,borrow_date);
       // RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/create_borrow.jsp");
        try{
            //dispatcher.forward(request,response);

            //request.setAttribute("message","Borrow slips added successfully!");
            HttpSession session = request.getSession(false);
            session.setAttribute("message","Borrow slips added successfully!");
            response.sendRedirect("/home?action=create-borrow");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void returned(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        userDao.returned(id);
        try{
            response.sendRedirect("/home?action=view-customer");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete_borrow(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        userDao.delete_borrow(id);
        try{
            response.sendRedirect("/home?action=view-customer");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showupdate_listuser(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        User user=userDao.getUserById(id);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/update_listuser.jsp");
        try{
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update_listuser(HttpServletRequest request, HttpServletResponse response) {
          int id=Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String dateStr = request.getParameter("date");
        LocalDate birthday = LocalDate.parse(dateStr);
        String email = request.getParameter("email");
        String phone=request.getParameter("phone");
        String image=request.getParameter("image");
          User user=new User.Builder().id(id).name(name).birthday(birthday).email(email).phone(phone).image(image).build();
         // User user=new User.Builder().id(id).name(name).email(email).phone(phone).position(position).build();
          userDao.updateUser(user);
          try{
              response.sendRedirect("/home?action=select-user");
          }catch (Exception e){
              e.printStackTrace();
          }
    }


}
