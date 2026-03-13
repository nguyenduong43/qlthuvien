package org.example.qlthuvien.service.libservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILibservice {
    public void show_lib(HttpServletRequest request, HttpServletResponse response);
    public void showadd_book(HttpServletRequest request, HttpServletResponse response);
    public void add_book(HttpServletRequest request, HttpServletResponse response);
    public void delete_book(HttpServletRequest request, HttpServletResponse response);
    public void showupdate_book(HttpServletRequest request, HttpServletResponse response);
    public void update_book(HttpServletRequest request, HttpServletResponse response);
}
