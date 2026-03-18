package org.example.qlthuvien.controller;

import org.example.qlthuvien.service.libservice.Libservice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet(name="LibServlet",urlPatterns = "/lib")
public class LibServlet extends HttpServlet {
    Libservice libservice=new Libservice();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add-book":
                libservice.showadd_book(request,response);
                break;
            case "delete-book":
                    libservice.delete_book(request,response);
                    break;
            case "update-book":
                     libservice.showupdate_book(request,response);
                     break;
            case "view-category":
                         libservice.showview_category(request,response);
                         break;
            case "delete-category":
                libservice.delete_category(request,response);
                break;
            case "search-book":
            default:
                libservice.show_lib(request,response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "add-book":
                libservice.add_book(request,response);
                break;
            case "delete-book":
                libservice.delete_book(request,response);
                break;
            case "update-book":
                libservice.update_book(request,response);
                break;
            case "search-book":
                libservice.show_lib(request,response);
                break;
                case "view-category":
                    libservice.showview_category(request,response);
                    break;
            case "add-category":
                libservice.add_category(request,response);
                break;
                case "update-category":
                    libservice.update_category(request,response);
                    break;
            case "delete-category":
                libservice.delete_category(request,response);
                break;
        }
    }
}
