package org.example.qlthuvien.service.libservice;

import org.example.qlthuvien.dao.library.LibraryDao;
import org.example.qlthuvien.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
public class Libservice implements ILibservice{
    LibraryDao libraryDao=new LibraryDao();
    @Override
    public void show_lib(HttpServletRequest request, HttpServletResponse response) {
        int page=1;
        int limit=10;
        String pageStr = request.getParameter("page");
        if(pageStr != null){
            page = Integer.parseInt(pageStr);
        }
        int offset = (page - 1) * limit;
        //int i=libraryDao.count();
        String search=request.getParameter("search");
       int i=libraryDao.count1(search);
        List<Book> libraries=libraryDao.selectAllLibraries(search,limit,offset);
        request.setAttribute("totalPages", i / limit + 1);
        request.setAttribute("libraries",libraries);
        request.setAttribute("search1",search);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/home.jsp");
        try{
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showadd_book(HttpServletRequest request, HttpServletResponse response) {
//        List<Book> libraries=libraryDao.selectAllLibraries("", libraryDao.count(), 0);
//        request.setAttribute("libraries",libraries);
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

    @Override
    public void showview_category(HttpServletRequest request, HttpServletResponse response) {
      List<Book> category=libraryDao.select_category1();
      request.setAttribute("category",category);
      RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view_category.jsp");
      try{
          dispatcher.forward(request,response);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public void showadd_category(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/add_category.jsp");
        try{
            dispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void add_category(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        List<String> category=libraryDao.select_category();
        for(String c:category){
            if (c.equals(name)) {
                HttpSession session = request.getSession(false);
                session.setAttribute("message", "Category da ton tai");
                try{
                    response.sendRedirect("/lib?action=view-category");
                }catch (Exception e){
                    e.printStackTrace();
                }
                return;
            }
        }
        libraryDao.add_category(name);
        try{
            response.sendRedirect("/lib?action=view-category");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void update_category(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        List<String> category=libraryDao.select_category();
        for(String c:category){
            if (c.equals(name)) {
                HttpSession session = request.getSession(false);
                session.setAttribute("message", "Category da ton tai");
                try{
                    response.sendRedirect("/lib?action=view-category");
                }catch (Exception e){
                    e.printStackTrace();
                }
                return;
            }
        }
        libraryDao.update_category(id,name);
        try{
            response.sendRedirect("/lib?action=view-category");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete_category(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        libraryDao.delete_category(id);
        try{
            response.sendRedirect("/lib?action=view-category");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
