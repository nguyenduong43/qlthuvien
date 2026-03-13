package org.example.qlthuvien.service.libservice;

import org.example.qlthuvien.dao.library.LibraryDao;
import org.example.qlthuvien.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
public class Libservice implements ILibservice{
    LibraryDao libraryDao=new LibraryDao();
    @Override
    public void show_lib(HttpServletRequest request, HttpServletResponse response) {
        String search=request.getParameter("search");
        List<Book> libraries=libraryDao.selectAllLibraries(search);
        request.setAttribute("libraries",libraries);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/home.jsp");
        try{
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showadd_book(HttpServletRequest request, HttpServletResponse response) {
        List<Book> libraries=libraryDao.selectAllLibraries("");
        request.setAttribute("libraries",libraries);
        List<String> category=libraryDao.select_category();
        request.setAttribute("category",category);
        List<String> publisher=libraryDao.select_publisher();
        request.setAttribute("publisher",publisher);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/add_book.jsp");
        try{
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void add_book(HttpServletRequest request, HttpServletResponse response) {
            String name=request.getParameter("name");
            String description=request.getParameter("description");
            String image=request.getParameter("image");
            String status=request.getParameter("status");
            String category_name=request.getParameter("category_name");
            String publisher_name=request.getParameter("publisher_name");
            Book book=new Book.Builder().name(name).description(description).image(image).status(status).category_name(category_name).publisher_name(publisher_name).build();
            libraryDao.add_book(book);
            //List<Book> libraries=libraryDao.selectAllLibraries();
            //request.setAttribute("libraries",libraries);
          //  request.setAttribute("book",book);

            try{
                response.sendRedirect("/lib");
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    @Override
    public void delete_book(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        libraryDao.delete_book(id);
        try{
            response.sendRedirect("/lib");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showupdate_book(HttpServletRequest request, HttpServletResponse response) {
          int id=Integer.parseInt(request.getParameter("id"));
          Book book=libraryDao.selectBookById(id);
          request.setAttribute("book",book);
          List<String> category=libraryDao.select_category();
          request.setAttribute("category",category);
          List<String> publisher=libraryDao.select_publisher();
          request.setAttribute("publisher",publisher);
          RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/update_book.jsp");
          try{
              dispatcher.forward(request,response);
          }catch (Exception e){}
    }

    @Override
    public void update_book(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String description=request.getParameter("description");
        String image=request.getParameter("image");
        String status=request.getParameter("status");
        String category_name=request.getParameter("category_name");
        String publisher_name=request.getParameter("publisher_name");
        Book book=new Book.Builder().id(id).name(name).description(description).image(image).status(status).category_name(category_name).publisher_name(publisher_name).build();
        libraryDao.update_book(book);
        try{
            response.sendRedirect("lib");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
