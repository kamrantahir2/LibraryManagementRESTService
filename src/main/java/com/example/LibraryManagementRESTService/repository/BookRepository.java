package com.example.LibraryManagementRESTService.repository;

import com.example.LibraryManagementRESTService.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
