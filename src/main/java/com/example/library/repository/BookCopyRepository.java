package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    // Get all copies of a given book (by ISBN)
    List<BookCopy> findByBook(Book book);

    // Get all available (not borrowed) copies of a given book
    List<BookCopy> findByBookAndIsBorrowedFalse(Book book);

    // Count how many copies exist for a given book
    long countByBook(Book book);

    // Count how many copies are currently borrowed
    long countByBookAndIsBorrowedTrue(Book book);

    List<BookCopy> findByBookIsbnAndIsBorrowedFalse(String isbn);
}

