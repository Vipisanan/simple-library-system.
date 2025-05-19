package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(BookDto dto);
    List<Book> getAllBooks();
    Book borrowBook(Long bookId, Long borrowerId);
    Book returnBook(Long bookId);
}