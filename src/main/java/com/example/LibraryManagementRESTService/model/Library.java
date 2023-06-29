package com.example.LibraryManagementRESTService.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Library {
    private List<Book> bookList;

    public void addTempBooks() {
        this.bookList.add(new Book(1, "book1", "author1", "cat1"));
        this.bookList.add(new Book(2, "book2", "author2", "cat2"));
        this.bookList.add(new Book(3, "book3", "author3", "cat3"));
    }










//    ====================================================================
//    CONSTRUCTORS, GETTERS & SETTERS
    public Library(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Library() {
        this.bookList = new ArrayList<>();
        addTempBooks();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}