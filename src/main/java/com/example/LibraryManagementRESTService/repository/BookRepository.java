package com.example.LibraryManagementRESTService.repository;

import com.example.LibraryManagementRESTService.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

    List<Book> findByAvailableTrue();
}
