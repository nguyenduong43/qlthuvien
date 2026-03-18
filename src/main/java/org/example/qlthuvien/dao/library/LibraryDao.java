package org.example.qlthuvien.dao.library;

import org.example.qlthuvien.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDao implements IlibraryDao{
    private String jdbcURL = "jdbc:mysql://localhost:3306/qlthuvien?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Duong34.23";
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Book> selectAllLibraries(String keyword, int limit, int offset) {
        List<Book> libraries = new ArrayList<>();
        String query="call selectall_book(?,?,?)";
        try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);){
           callableStatement.setString(1,keyword);
           callableStatement.setInt(2,limit);
           callableStatement.setInt(3,offset);
            ResultSet rs=callableStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name=rs.getString("name");
                String description=rs.getString("description");
                String image=rs.getString("image");
                String status=rs.getString("status");
                String category_name=rs.getString("category_name");
                String publisher_name=rs.getString("publisher_name");
                Book book =new Book.Builder().id(id).name(name).description(description).image(image).status(status).category_name(category_name).publisher_name(publisher_name).build();
                libraries.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return libraries;
    }

    @Override
    public void add_book(Book book) {
        String query="call add_book(?,?,?,?,?,?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setString(1,book.getName());
            callableStatement.setString(2, book.getDescription());
            callableStatement.setString(3, book.getImage());
            callableStatement.setString(4, book.getStatus());
            callableStatement.setString(5, book.getCategory_name());
            callableStatement.setString(6, book.getPublisher_name());
            callableStatement.execute();
        }catch (SQLException e)
            {
            e.printStackTrace();
            }


    }

    @Override
    public List<String> select_category() {
        List<String> categories = new ArrayList<>();
        String query="call select_name_c()";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            ResultSet rs=callableStatement.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                categories.add(name);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<String> select_publisher() {
        List<String> publishers = new ArrayList<>();
        String query="call select_name_p()";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            ResultSet rs=callableStatement.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                publishers.add(name);
            }
            System.out.println(publishers);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public void delete_book(int id) {
        String query="call delete_book(?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setInt(1,id);
            callableStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update_book(Book book) {
        String query="call update_book(?,?,?,?,?,?,?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setInt(1,book.getId());
            callableStatement.setString(2,book.getName());
            callableStatement.setString(3,book.getDescription());
            callableStatement.setString(4,book.getImage());
            callableStatement.setString(5,book.getStatus());
            callableStatement.setString(6,book.getCategory_name());
            callableStatement.setString(7,book.getPublisher_name());
            callableStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Book selectBookById(int id) {
        String query="call select_book_byid(?)";
        Book book=null;
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);)
        {
            callableStatement.setInt(1,id);
            ResultSet rs=callableStatement.executeQuery();
            if(rs.next())
            {
                String name=rs.getString("name");
                String description=rs.getString("description");
                String image=rs.getString("image");
                String status=rs.getString("status");
                String category_name=rs.getString("category_name");
                String publisher_name=rs.getString("publisher_name");
                book=new Book.Builder().name(name).description(description).image(image).status(status).category_name(category_name).publisher_name(publisher_name).build();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public int count() {
        String query="call count_book()";
        int count=0;
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            ResultSet rs=callableStatement.executeQuery();
            if(rs.next())
            {
                count=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int count1(String keyword) {
        String query="call count_book1(?)";
        int count=0;
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
           callableStatement.setString(1,keyword);
            ResultSet rs=callableStatement.executeQuery();
            if(rs.next())
            {
                count=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Book> select_category1() {
        String query="call select_name_c()";
        List<Book> books=new ArrayList<>();
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            ResultSet rs=callableStatement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                Book book=new Book.Builder().category_id(id).category_name(name).build();
                books.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void add_category(String name) {
        String query="call add_categories(?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setString(1,name);
            callableStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update_category(int id, String name) {
        String query="call update_category(?,?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);)
        {
            callableStatement.setInt(1,id);
            callableStatement.setString(2,name);
            callableStatement.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete_category(int id) {
        String query="call delete_category(?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);)
        {
            callableStatement.setInt(1,id);
            callableStatement.execute();
        }catch (SQLException e)
            {
            e.printStackTrace();
            }
    }
}
