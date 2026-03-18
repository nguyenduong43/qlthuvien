package org.example.qlthuvien.model;

public class Book {
    private int id;
    private String name;
    private String description;
    private String image;
    private String status;
    private String category_name;
    private String publisher_name;
    private int category_id;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    private Book(Book.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.image = builder.image;
        this.status = builder.status;
        this.category_name = builder.category_name;
        this.publisher_name = builder.publisher_name;
        this.category_id=builder.category_id;
    }
    public static class Builder{
          private int id;
          private String name;
          private String description;
          private String image;
          private String status;
          private String category_name;
          private String publisher_name;
          private int category_id;
          public Builder id(int id){
              this.id=id;
              return this;
          }
          public Builder category_id(int category_id){
              this.category_id=category_id;
              return this;
          }
          public Builder name(String name){
              this.name=name;
              return this;
          }
          public Builder description(String description){
              this.description=description;
              return this;
          }
          public Builder image(String image){
              this.image=image;
              return this;
          }
          public Builder status(String status){
              this.status=status;
              return this;
          }
          public Builder category_name(String category_name){
              this.category_name=category_name;
              return this;
          }
          public Builder publisher_name(String publisher_name){
              this.publisher_name=publisher_name;
              return this;
          }
          public Book build(){
              return new Book(this);
          }

    }
}
