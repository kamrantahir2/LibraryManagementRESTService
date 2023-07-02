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
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testAddBookMethod() {
        assertTrue(library.addBook(new Book("test_name", "test_author", "test_cat", 5)));
    }

    @Test
    public void testDeleteBookMethod() {
        Book book = new Book("name", "author", "cat", 5);
        assertTrue(library.deleteBook(book));
    }

}