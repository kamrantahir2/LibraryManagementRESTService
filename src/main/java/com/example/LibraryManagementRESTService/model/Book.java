package com.example.LibraryManagementRESTService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity(name="book")
@Table(name="books")
public class Book {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="author")
    private String author;

    @Column(name="category")
    private String category;

    @JsonIgnore
    private boolean available;

    @Column(name="quantity")
    private int quantity;


//    FIELDS END HERE
//    ======================================================================






//    ======================================================================
//    CONSTRUCTORS, GETTERS & SETTERS

    public Book(String name, String author, String category, boolean available) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    public Book(String name, String author, String category, int quantity) {
        this.name = name;
        this.author = author;
        this.category = category;
        available = true;
    }

    public Book() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}