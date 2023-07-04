package com.example.LibraryManagementRESTService.service;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public Iterable<Book> getAllAvailable() {
        return bookRepository.findByAvailableTrue();
    }

    public Book get(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        if (book.getQuantity() > 0) {
            book.setAvailable(true);
        } else {
            book.setAvailable(false);
        }

        bookRepository.save(book);
        return book;
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public Book findById(int id) {
        return bookRepository.findById(id);
    }
}