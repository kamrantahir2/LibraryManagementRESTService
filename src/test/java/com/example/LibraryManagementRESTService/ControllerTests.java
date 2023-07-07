package com.example.LibraryManagementRESTService;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.LibraryManagementRESTService.model.Book;
import com.example.LibraryManagementRESTService.repository.BookRepository;
import com.example.LibraryManagementRESTService.service.BookService;
import com.example.LibraryManagementRESTService.web.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.assertj.core.api.Assert;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = LibraryManagementRestServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository mockRepository;

    private int id = 1;

    public Book createBook(int quantity) {
        Book book = new Book("name", "author", "category", quantity);
        book.setId(id);;
        id ++;
        return book;
    }

    public Book createBook() {
        Book book = new Book("name", "author", "category", 5);
        book.setId(id);;
        id ++;
        return book;
    }


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void init() {
        Book book = createBook();
        when(mockRepository.findById(1)).thenReturn(book);
    }

    @Test
    public void contextLoads() {

    }

//  Test GET Endpoint
    @Test
    public void testGetEndpoint() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveToDatabase() throws Exception {
        Book book = createBook();

        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/addbook").contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchByCategory() throws Exception {
        mockMvc.perform(get("/search/{category}", "cat").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBookWithIdOne_thenReturn200() throws Exception {
        mockRepository.save(createBook());
        mockMvc.perform(delete("/delete/{id}", 1))
                .andExpect(status().isOk());
    }


    @Test
    public void whenRentBook_thenStatus200() throws Exception {
        Book book = createBook();
        mockRepository.save(book);
        System.out.println(book.getId());
        mockMvc.perform(put("/rent/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/1")
                        .content(objectMapper.writeValueAsString(book))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity", Matchers.equalTo(book.getQuantity() - 1)));
    }

}