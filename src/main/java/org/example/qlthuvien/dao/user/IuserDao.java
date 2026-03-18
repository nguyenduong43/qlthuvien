package org.example.qlthuvien.dao.user;

import org.example.qlthuvien.model.Customers;
import org.example.qlthuvien.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IuserDao {
    public void registerUser(User user) throws SQLException;
    public List<User> selectAllUsers();
    public User getUserById(int id);
    public boolean updateUser(User user) throws SQLException;
    public User checkUser(String username, String password);
    public boolean changePassword(int id, String password);
    public void soft_delete(int id) throws SQLException;
    public void restore(int id) throws SQLException;
    public List<User> selectAllUser1(int limit, int offset) throws SQLException;
    public int count() throws SQLException;
    public void add_customer(Customers customers);
    public List<Customers> view_customers(String keyword,int limit,int offset) ;
    public int count_customers(String keyword);
    public void add_borrow(String customer_id, String name_book, LocalDate date_borrow);
    public List<Customers> select_customerid();
    public List<String> select_namebook();
    public void returned(int id );
    public void delete_borrow(int id);
    public String get_pass(String name);

}
