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
        Book book = service.findById(id);
        boolean success = library.rentOutBook(book);

        if (success) {
            return new ResponseEntity<>(objectMapper.writeValueAsString(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR: BOOK NOT AVAILABLE", HttpStatus.BAD_REQUEST);
        }

    }

}