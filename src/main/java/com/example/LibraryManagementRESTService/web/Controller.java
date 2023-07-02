package com.example.LibraryManagementRESTService.web;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.model.Library;
import com.example.LibraryManagementRESTService.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class Controller {
    @Autowired
    Library library;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private final BookService service;

    public Controller(Library library, BookService service) {
        this.library = library;
        this.service = service;
    }

    @GetMapping("/")
    public String getBooks() throws JsonProcessingException {
        library.update();
        return objectMapper.writeValueAsString(library);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable int id) throws JsonProcessingException {
        Optional<Book> opt = Optional.of(service.findById(id));
        if (opt.isPresent()) {
            Book book = opt.get();
            return new ResponseEntity<>(objectMapper.writeValueAsString(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR: BOOK NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{category}")
    public ResponseEntity<String> findByCategory(@PathVariable String category) throws JsonProcessingException {
        Optional<List<Book>> opt = Optional.of(service.findByCategory(category));

        if (opt.isPresent()) {
            List<Book> list = opt.get();
            return new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR: NO BOOKS FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addbook")
    public ResponseEntity<String> addBook(@RequestBody Book book) throws JsonProcessingException {
        boolean added = library.addBook(book);
        if (added) {
            String returnedJson = objectMapper.writeValueAsString(book);
            return new ResponseEntity<>(returnedJson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR : BAD REQUEST. ERROR ADDING BOOK", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) throws JsonProcessingException {
        Book book = service.findById(id);
        boolean bool = library.deleteBook(book);
        if (bool) {
            library.update();
            return new ResponseEntity<>(objectMapper.writeValueAsString(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR: BOOK NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("rent/{id}")
    public ResponseEntity<String> rentOutBook(@PathVariable int id) throws JsonProcessingException {
        Optional<Book> opt = Optional.of(service.findById(id));

        if (opt.isPresent()) {
            Book book = opt.get();
            boolean success = library.rentOutBook(book);
            if (success) {
                return new ResponseEntity<>(objectMapper.writeValueAsString(book), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("ERROR: BOOK NOT AVAILABLE", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("ERROR: BOOK NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("return/{id}")
    public ResponseEntity<String> returnBook(@PathVariable int id) throws JsonProcessingException {
        Optional<Book> opt = Optional.of(service.findById(id));

        if (opt.isPresent()) {
            Book book = opt.get();
            library.returnBook(book);
            return new ResponseEntity<>(objectMapper.writeValueAsString(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR: BOOK NOT FOUND", HttpStatus.OK);
        }
    }

}