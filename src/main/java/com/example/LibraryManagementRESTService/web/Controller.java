package com.example.LibraryManagementRESTService.web;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.model.Library;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Library library;
    ObjectMapper objectMapper = new ObjectMapper();

    public Controller(Library library) {
        this.library = library;
    }

    @GetMapping("/")
    public String displayJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(library);
    }

}