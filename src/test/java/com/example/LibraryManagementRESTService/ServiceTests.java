package com.example.LibraryManagementRESTService;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.model.Library;
import com.example.LibraryManagementRESTService.repository.BookRepository;
import com.example.LibraryManagementRESTService.service.BookService;
import com.example.LibraryManagementRESTService.web.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// This class uses Mockito to test BookService

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Controller controller;

    @Autowired
    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;

    @MockBean
    private Library library;

    @MockBean
    private Book book;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        bookService =  mock(BookService.class);
    }

    @Test
    public void contextLoads() {

    }

//    Tests GetAllAvailable method from BookService
    @Test
    public void testGetAllAvailable() {
        when(bookService.getAllAvailable()).thenReturn(new ArrayList<Book>());
         List<Book> bookList = new ArrayList<Book>();
         assertEquals(bookList, bookService.getAllAvailable());
    }

    

}