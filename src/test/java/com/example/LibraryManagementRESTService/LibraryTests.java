package com.example.LibraryManagementRESTService;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.model.Library;
import com.example.LibraryManagementRESTService.service.BookService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryTests {
    @Autowired
    BookService service;

    @Autowired
    Library library;

    public LibraryTests() {
        this.library = new Library(service);
    }

    @Test
    @DisplayName("Test Add Book Method")
    public void testAddBookMethod() {
        assertEquals(true, library.addBook(new Book(UUID.randomUUID().toString(), "test_name", "test_author", "test_cat")));
    }

}