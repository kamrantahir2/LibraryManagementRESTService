package com.example.LibraryManagementRESTService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity(name="book")
@Table(name="books")
public class Book {

    @Id
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="author")
    private String author;

    @Column(name="category")
    private String category;

    private boolean available;

//    FIELDS END HERE
//    ======================================================================






//    ======================================================================
//    CONSTRUCTORS, GETTERS & SETTERS

    public Book(int id, String name, String author, String category, boolean available) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    public Book(int id, String name, String author, String category) {
        this.id = id;
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
}