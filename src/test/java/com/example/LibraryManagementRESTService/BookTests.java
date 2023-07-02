package com.example.LibraryManagementRESTService;

import com.example.LibraryManagementRESTService.model.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookTests {

    public Book createBook(int quantity) {
        return new Book("test_name", "test_author", "test_cat", quantity);
    }

    @Test
    public void testDecrementQuantityMethod() {
//        Starting number: 5
        Book book = createBook(5);
        int temp = book.decrementQuantity();
        assertEquals(4, temp);
    }

    @Test
    public void testAvailableSetToFalseWhenQuantityZero() {
        Book book = createBook(1);
        book.decrementQuantity();
        assertFalse(book.isAvailable());
    }

    @Test
    public void testIncrementQuantityMethod() {
        Book book = createBook(0);
        int result = book.incrementQuantity();
        assertEquals(1, result);
        assertTrue(book.isAvailable());
    }

}