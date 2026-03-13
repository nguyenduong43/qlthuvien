package org.example.qlthuvien.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
      private int id;
      private String name;
      private LocalDate birthday;
      private String email;
      private String phone;
      private String image;
      private String username;
      private String password;
      private String position;
      private LocalDate create;
      private LocalDateTime delete_at;
      private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.birthday = builder.birthday;
        this.email = builder.email;
        this.phone = builder.phone;
        this.image = builder.image;
        this.username = builder.username;
        this.password = builder.password;
        this.position = builder.position;
        this.delete_at = builder.delete_at;
        this.status = builder.status;
    }

    public static class Builder{
          private int id;
          private String name;
          private LocalDate birthday;
          private String email;
          private String phone;
          private String image;
          private String username;
          private String password;
          private String position;
          private LocalDateTime delete_at;
          private String status;
          public Builder status(String status){
              this.status= status;
              return this;
          }
          public Builder id(int id){
              this.id=id;
              return this;
          }
          public Builder name(String name){
              this.name=name;
              return this;
          }
          public Builder birthday(LocalDate birthday){
              this.birthday=birthday;
              return this;
          }
          public Builder email(String email){
              this.email=email;
              return this;
          }
          public Builder phone(String phone){
              this.phone=phone;
              return this;
          }
          public Builder image(String image ){
              this.image=image;
              return this;
          }
          public Builder username(String username){
              this.username=username;
              return this;
          }
          public Builder password(String password){
              this.password=password;
              return this;
          }
          public Builder position(String position){
              this.position=position;
              return this;
          }
          public Builder delete_at(LocalDateTime delete_at){
              this.delete_at=delete_at;
              return this;
          }
          public User build() {
              return new User(this);
          }
      }
//    public User(int id, String name, LocalDate birthday, String email, String phone, String image, String position) {
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone = phone;
//        this.image = image;
//        this.position = position;
//    }
//
//    public User(int id, String name, LocalDate birthday, String email, String phone, String image, String position, LocalDateTime delete_at) {
//        this.delete_at = delete_at;
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone = phone;
//        this.image = image;
//        this.position = position;
//    }

    public LocalDateTime getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(LocalDateTime delete_at) {
        this.delete_at = delete_at;
    }

//    public User(int id, String name, LocalDate birthday, String email, String phone, String image, String username, String password, String roles_id) {
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone = phone;
//        this.image = image;
//        this.username = username;
//        this.password = password;
//        this.position = roles_id;
//    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//    public User() {
//    }
//    public User(int id, String name, LocalDate birthday, String email, String phone, String image, String username, String password) {
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone = phone;
//        this.image = image;
//        this.username = username;
//        this.password = password;
//    }
//    public User(int id, String name, LocalDate birthday, String email, String phone, String image) {
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone = phone;
//        this.image = image;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", create=" + create +
                ", delete_at=" + delete_at +
                ", status='" + status + '\'' +
                '}';
    }
}
