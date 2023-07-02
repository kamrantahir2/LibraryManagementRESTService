package com.example.LibraryManagementRESTService.model;

import com.example.LibraryManagementRESTService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Library {
    private List<Book> bookList;
    @Autowired
    private BookService service;

    public void update() {
        this.bookList = (List<Book>) service.getAll();
    }

    public boolean addBook(Book book) {
        Optional<Book> optBook = Optional.of(book);
        if (optBook.isPresent()) {
            Book newBook = optBook.get();
            service.save(book);
            update();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteBook(Book book) {
        Optional<Book> opt = Optional.of(book);
        if (opt.isPresent()) {
            Book optBook = opt.get();
            service.delete(optBook);
            return true;
        } else {
            return false;
        }
    }

    public boolean rentOutBook(Book paramBook) {
        Optional<Book> opt = Optional.of(paramBook);

        if (opt.isPresent()) {
            Book book = opt.get();
            int result = book.decrementQuantity();
            if (result == -1) {
                return false;
            }

            service.save(book);
            update();
            return true;
        } else {
            return false;
        }
    }

    public boolean returnBook(Book paramBook) {
        Optional<Book> opt = Optional.of(paramBook);

        if (opt.isPresent()) {
            Book book = opt.get();
            book.incrementQuantity();
            service.save(book);
            update();
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

    public Library() {

    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}