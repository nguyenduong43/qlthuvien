package org.example.qlthuvien.dao.user;

import org.example.qlthuvien.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IuserDao {
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
   public void registerUser(User user)  {
        String query="call register(?,?,?,?,?,?,?,?)";
        try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);)
        {
            callableStatement.setString(1,user.getUsername());
            callableStatement.setString(2,user.getPassword());
            callableStatement.setString(3,user.getName());
            callableStatement.setDate(4, Date.valueOf(user.getBirthday()));
            callableStatement.setString(5,user.getEmail());
            callableStatement.setString(6,user.getPhone());
            callableStatement.setString(7,user.getImage());
            callableStatement.setString(8,user.getPosition());
            callableStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
   }

    @Override
    public User selectUser(int id) {
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        String query="call selectallusers()";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
          ResultSet rs=callableStatement.executeQuery();
           while(rs.next())
           {
               int id = rs.getInt("id");
               String name = rs.getString("name");
               Date birthdaySql = rs.getDate("birthday");
               LocalDate birthday = birthdaySql.toLocalDate();
               String email = rs.getString("email");
               String phone = rs.getString("phone");
               String image = rs.getString("image");
               String position=rs.getString("position");
               String username=rs.getString("username");
               String password=rs.getString("password");
               Timestamp deletedSql = rs.getTimestamp("deleted_at");
               LocalDateTime deleted_at ;
               String status;
               if(deletedSql != null){
                   deleted_at = deletedSql.toLocalDateTime();
                   status = "deleted";
               }else{
                   deleted_at = LocalDateTime.MIN; // giá trị mặc định
                   status = "active";
               }
              // LocalDateTime deleted_at = deletedSql.toLocalDateTime();
              // users.add(new User(id,name,birthday,email,phone,image,position,deleted_at));
               User user=new User.Builder().id(id).name(name).birthday(birthday).email(email).phone(phone).image(image).position(position).delete_at(deleted_at).status(status).username(username).password(password).build();
               users.add(user);
           }


        }catch (SQLException e)
            {
            e.printStackTrace();
            }
       return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user)  {
        String query="call update_user(?,?,?,?,?,?)";
        try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);)
        {
            callableStatement.setInt(1,user.getId());
            callableStatement.setString(2,user.getName());
            callableStatement.setDate(3, Date.valueOf(user.getBirthday()));
            callableStatement.setString(4,user.getEmail());
            callableStatement.setString(5,user.getPhone());
            callableStatement.setString(6,user.getImage());
            callableStatement.execute();
            return true;
        }catch (SQLException e)
            {
            e.printStackTrace();
            return false;
            }
    }

    @Override
    public List<User> searchUser(String country) throws SQLException {
        List<User> users = null;
        return users;
    }

    @Override
    public List<User> sortUser() throws SQLException {
        List<User> users = null;
        return users;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User checkUser(String username, String password) {
            String query="call login(?,?)";
            try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);)
            {
                callableStatement.setString(1,username);
                callableStatement.setString(2,password);
                ResultSet rs=callableStatement.executeQuery();
                if(rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Date birthdaySql = rs.getDate("birthday");
                    LocalDate birthday = birthdaySql.toLocalDate();
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String image = rs.getString("image");
                    String position = rs.getString("position");
                    //return new User(id, name, birthday, email, phone, image, username, password, position);
                    return new User.Builder().id(id).name(name).birthday(birthday).email(email).phone(phone).image(image).username(username).password(password).position(position).build();
                }

            }catch (SQLException e)
                {
                e.printStackTrace();
                }
            return null;
    }
    public boolean changePassword(int id, String password) {
       String query="call change_pass(?,?)";
       try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);)
       {
           callableStatement.setInt(1,id);
           callableStatement.setString(2,password);
           callableStatement.execute();
           return true;
       }catch (SQLException e)
       {
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public void soft_delete(int id) throws SQLException {
        String query="call soft_delete(?)";
        try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setInt(1,id);
            callableStatement.execute();
        }catch (SQLException e)
            {
            e.printStackTrace();
            }
    }

    @Override
    public void restore(int id) throws SQLException {
        String query="call restore(?)";
        try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setInt(1,id);
            callableStatement.execute();
        }catch (SQLException e)
            {
            e.printStackTrace();
            }
    }

    @Override
    public void search_role(int role_id)  {
         String query="call search_role(?)";
         try(Connection connection=getConnection(); CallableStatement callableStatement=connection.prepareCall(query);){
             callableStatement.setInt(1,role_id);
             callableStatement.execute();
         }catch (SQLException e)
             {
             e.printStackTrace();
             }
    }

    @Override
    public List<User> selectAllUser1(int limit, int offset)  {
        List<User> users = new ArrayList<>();
        String query="call selectalluser1(?,?)";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            callableStatement.setInt(1,limit);
            callableStatement.setInt(2,offset);
            ResultSet rs=callableStatement.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date birthdaySql = rs.getDate("birthday");
                LocalDate birthday = birthdaySql.toLocalDate();
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String image = rs.getString("image");
                String position=rs.getString("position");
                String username=rs.getString("username");
                String password=rs.getString("password");
                Timestamp deletedSql = rs.getTimestamp("deleted_at");
                LocalDateTime deleted_at ;
                String status;
                if(deletedSql != null){
                    deleted_at = deletedSql.toLocalDateTime();
                    status = "deleted";
                }else{
                    deleted_at = LocalDateTime.MIN; // giá trị mặc định
                    status = "active";
                }
                // LocalDateTime deleted_at = deletedSql.toLocalDateTime();
                // users.add(new User(id,name,birthday,email,phone,image,position,deleted_at));
                User user=new User.Builder().id(id).name(name).birthday(birthday).email(email).phone(phone).image(image).position(position).delete_at(deleted_at).status(status).username(username).password(password).build();
                users.add(user);
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int count()  {
        String query="call count1()";
        try(Connection connection=getConnection();CallableStatement callableStatement=connection.prepareCall(query);){
            ResultSet rs=callableStatement.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

}
