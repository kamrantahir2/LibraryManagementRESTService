package com.example.LibraryManagementRESTService.repository;

import com.example.LibraryManagementRESTService.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookRepository extends CrudRepository<Book, String> {

    List<Book> findByAvailableTrue();

    Book findByName(String name);

    Book findById(int id);

    List<Book> findByCategory(String category);
}
