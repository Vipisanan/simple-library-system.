package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    Book addOrUpdateBook(BookDto bookDto); // Ensures title and author consistency for ISBN
    List<Book> getAllBooks();

    Book getBookByIsbn(String isbn);
}
