package org.example.qlthuvien.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customers {
    private int id;
    private String customer_code;
    private String name;
    private String customer_class;
    private String address;
    private LocalDate birthday;
    private boolean is_deleted;
    private String book_name;
    private LocalDate borrow_date;
    private String status;
    private Customers(Customers.Builder builder)
    {
        this.id=builder.id;
        this.customer_code=builder.customer_code;
        this.name=builder.name;
        this.customer_class=builder.customer_class;
        this.address=builder.address;
        this.birthday=builder.birthday;
        this.is_deleted=builder.is_deleted;
        this.book_name=builder.book_name;
        this.borrow_date=builder.borrow_date;
        this.status=builder.status;
    }
    public static class Builder{
        private int id;
        private String customer_code;
        private String name;
        private String customer_class;
        private String address;
        private LocalDate birthday;
        private boolean is_deleted;
        private String book_name;
        private LocalDate borrow_date;
        private String status;
        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder customer_code(String customer_code){
            this.customer_code=customer_code;
            return this;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder customer_class(String customer_class){
            this.customer_class=customer_class;
            return this;
        }
        public Builder address(String address){
            this.address=address;
            return this;
        }
        public Builder birthday(LocalDate birthday){
            this.birthday=birthday;
            return this;
        }
        public Builder is_deleted(boolean is_deleted){
            this.is_deleted=is_deleted;
            return this;
        }
        public Builder book_name(String book_name){
            this.book_name=book_name;
            return this;
        }
        public Builder borrow_date(LocalDate borrow_date){
            this.borrow_date=borrow_date;
            return this;
        }
        public Builder status(String status){
            this.status=status;
            return this;
        }
        public Customers build(){
            return new Customers(this);
        }

    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public LocalDate getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(LocalDate borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer_class() {
        return customer_class;
    }

    public void setCustomer_class(String customer_class) {
        this.customer_class = customer_class;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
