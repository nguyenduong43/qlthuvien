package org.example.qlthuvien.dao.library;

import org.example.qlthuvien.model.Book;

import java.util.*;

public interface IlibraryDao {
    public List<Book> selectAllLibraries(String search);
    public void add_book(Book book);
    public List<String> select_category();
    public List<String> select_publisher();
    public void delete_book(int id);
    public void update_book(Book book);
    public Book selectBookById(int id);


}
