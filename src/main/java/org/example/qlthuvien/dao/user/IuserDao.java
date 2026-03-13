package org.example.qlthuvien.dao.user;

import org.example.qlthuvien.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IuserDao {
    public void registerUser(User user) throws SQLException;
    public User selectUser(int id);
    public List<User> selectAllUsers();
    public boolean deleteUser(int id) throws SQLException;
    public boolean updateUser(User user) throws SQLException;
    public List<User> searchUser(String country) throws SQLException;
    public List<User> sortUser() throws SQLException;
    public User getUserById(int id);
    public User checkUser(String username, String password);
    public boolean changePassword(int id, String password);
    public void soft_delete(int id) throws SQLException;
    public void restore(int id) throws SQLException;
    public void search_role(int role_id) throws SQLException;
    public List<User> selectAllUser1(int limit, int offset) throws SQLException;
    public int count() throws SQLException;
}
