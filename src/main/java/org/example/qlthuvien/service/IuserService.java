package org.example.qlthuvien.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IuserService {
    public void Showchange_pass(HttpServletRequest request, HttpServletResponse response);
    public void change_pass(HttpServletRequest request, HttpServletResponse response);
    public void showupdate_user(HttpServletRequest request, HttpServletResponse response);
    public void update_user(HttpServletRequest request, HttpServletResponse response);
    public void login_admin(HttpServletRequest request, HttpServletResponse response);
    public void showselect_user(HttpServletRequest request, HttpServletResponse response);
    public void select_user(HttpServletRequest request, HttpServletResponse response);
    public void delete_user(HttpServletRequest request, HttpServletResponse response);
    public void restore(HttpServletRequest request, HttpServletResponse response);
}
