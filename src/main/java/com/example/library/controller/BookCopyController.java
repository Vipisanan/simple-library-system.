package com.example.library.controller;

import com.example.library.model.BookCopy;
import com.example.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopyController {

    @Autowired
    private BookCopyService bookCopyService;

    // Add a new copy of a book using ISBN
    @PostMapping("/add/{isbn}")
    public ResponseEntity<BookCopy> addCopy(@PathVariable String isbn) {
        BookCopy copy = bookCopyService.addBookCopy(isbn);
        return ResponseEntity.ok(copy);
    }

    // Borrow a book copy by copyId and borrowerId
    @PostMapping("/{copyId}/borrow/{borrowerId}")
    public ResponseEntity<?> borrowCopy(@PathVariable Long copyId, @PathVariable Long borrowerId) {
        try {
            BookCopy borrowedCopy = bookCopyService.borrowBookCopy(copyId, borrowerId);
            return ResponseEntity.ok(borrowedCopy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Return a borrowed copy
    @PostMapping("/{copyId}/return")
    public ResponseEntity<?> returnCopy(@PathVariable Long copyId) {
        try {
            BookCopy returnedCopy = bookCopyService.returnBookCopy(copyId);
            return ResponseEntity.ok(returnedCopy);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all available copies for a given ISBN
    @GetMapping("/available/{isbn}")
    public ResponseEntity<List<BookCopy>> getAvailableCopies(@PathVariable String isbn) {
        List<BookCopy> copies = bookCopyService.getAvailableCopies(isbn);
        return ResponseEntity.ok(copies);
    }

    // Get all copies of a book by ISBN (borrowed or not)
    @GetMapping("/all/{isbn}")
    public ResponseEntity<List<BookCopy>> getAllCopies(@PathVariable String isbn) {
        List<BookCopy> copies = bookCopyService.getAvailableCopies(isbn);
        return ResponseEntity.ok(copies);
    }

    // Get specific copy by ID
    @GetMapping("/{copyId}")
    public ResponseEntity<BookCopy> getCopyById(@PathVariable Long copyId) {
        BookCopy copy = bookCopyService.returnBookCopy(copyId);
        return ResponseEntity.ok(copy);
    }
}
