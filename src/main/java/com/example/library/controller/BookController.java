package com.example.library.controller;

import com.example.library.dto.BookCopyResponseDto;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.service.BookCopyService;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCopyService bookCopyService;

    // Add or register a unique book by ISBN (creates entry if not already exists)
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookDto bookDto) {
        Book book = bookService.addOrUpdateBook(bookDto);
        return ResponseEntity.ok(book);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get book by ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    // Add a new copy of a book (physical copy)
    @PostMapping("/{isbn}/copies")
    public ResponseEntity<BookCopyResponseDto> addBookCopy(@PathVariable String isbn) {
        BookCopyResponseDto copy = bookCopyService.addBookCopy(isbn);
        return ResponseEntity.ok(copy);
    }

    // Borrow a copy of a book by copy ID and borrower ID
    @PostMapping("/copies/{copyId}/borrow/{borrowerId}")
    public ResponseEntity<?> borrowBookCopy(@PathVariable Long copyId, @PathVariable Long borrowerId) {
        try {
            BookCopy copy = bookCopyService.borrowBookCopy(copyId, borrowerId);
            return ResponseEntity.ok(copy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Return a borrowed book copy
    @PostMapping("/copies/{copyId}/return")
    public ResponseEntity<?> returnBookCopy(@PathVariable Long copyId) {
        try {
            BookCopy copy = bookCopyService.returnBookCopy(copyId);
            return ResponseEntity.ok(copy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all available (not borrowed) copies for a book by ISBN
    @GetMapping("/{isbn}/copies/available")
    public ResponseEntity<List<BookCopyResponseDto>> getAvailableCopies(@PathVariable String isbn) {
        List<BookCopyResponseDto> copiesResponseDto = bookCopyService.getAvailableCopies(isbn);
        return ResponseEntity.ok(copiesResponseDto);
    }
}
