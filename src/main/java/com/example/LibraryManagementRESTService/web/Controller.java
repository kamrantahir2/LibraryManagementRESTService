package com.example.LibraryManagementRESTService.web;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.model.Library;
import com.example.LibraryManagementRESTService.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    public String displayJSON() throws JsonProcessingException {
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

}