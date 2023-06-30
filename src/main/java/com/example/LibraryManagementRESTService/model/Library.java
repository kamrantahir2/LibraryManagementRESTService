package com.example.LibraryManagementRESTService.model;

import com.example.LibraryManagementRESTService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Library {
    private List<Book> bookList;
    private BookService service;

    public void update() {
        this.bookList = (List<Book>) service.getAll();
    }

    public boolean addBook(Book book) {
        Optional<Book> optBook = Optional.of(book);

        if (optBook.isPresent()) {
            Book newBook = optBook.get();
            service.save(book);
//            updateBookList(service);
            return true;
        } else {
            return false;
        }

    }



//    ====================================================================
//    CONSTRUCTORS, GETTERS & SETTERS
    public Library(BookService service) {
        this.service = service;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}