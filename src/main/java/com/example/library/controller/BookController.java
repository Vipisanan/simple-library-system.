package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add a new book
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDto bookDto) {
        Book createdBook = bookService.addBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Get list of all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Borrow a book by bookId and borrowerId
    @PostMapping("/{bookId}/borrow/{borrowerId}")
    public ResponseEntity<?> borrowBook(
            @PathVariable Long bookId,
            @PathVariable Long borrowerId
    ) {
        try {
            Book borrowedBook = bookService.borrowBook(bookId, borrowerId);
            return ResponseEntity.ok(borrowedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Return a borrowed book by bookId
    @PostMapping("/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        try {
            Book returnedBook = bookService.returnBook(bookId);
            return ResponseEntity.ok(returnedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
