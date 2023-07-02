package com.example.LibraryManagementRESTService;

import com.example.LibraryManagementRESTService.model.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BookTests {

    public Book createBook() {
        return new Book("test_name", "test_author", "test_cat", 5);
    }

    @Test
    public void testDecrementQuantityMethod() {
//        Starting number: 5
        Book book = createBook();
        int temp = book.decrementQuantity();
        assertEquals(4, temp);
    }

    @Test
    public void testAvailableSetToFalseWhenQuantityZero() {
        Book book = new Book("n", "a", "c", 1);
        book.decrementQuantity();
        assertFalse(book.isAvailable());
    }


}