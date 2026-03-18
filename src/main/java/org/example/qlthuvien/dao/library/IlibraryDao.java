package org.example.qlthuvien.dao.library;

import org.example.qlthuvien.model.Book;

import java.util.*;

public interface IlibraryDao {
    public List<Book> selectAllLibraries(String search,int limit,int offset);
    public void add_book(Book book);
    public List<String> select_category();
    public List<String> select_publisher();
    public void delete_book(int id);
    public void update_book(Book book);
    public Book selectBookById(int id);
    public int count();
    public int count1(String keyword);
    public List<Book> select_category1();
    public void add_category(String name);
    public void update_category(int id,String name);
    public void delete_category(int id);


}
