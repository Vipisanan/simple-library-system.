package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Prevent Spring from replacing your DB
@ActiveProfiles("test")
class BookCopyRepositoryTest {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setup() {
        bookCopyRepository.deleteAll();
        bookRepository.deleteAll();

        book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        bookRepository.save(book);

        BookCopy copy1 = new BookCopy();
        copy1.setBook(book);
        copy1.setBorrowed(false);

        BookCopy copy2 = new BookCopy();
        copy2.setBook(book);
        copy2.setBorrowed(true);

        bookCopyRepository.save(copy1);
        bookCopyRepository.save(copy2);
    }

    @Test
    void testFindByBook() {
        List<BookCopy> copies = bookCopyRepository.findByBook(book);
        assertThat(copies).hasSize(2);
    }

    @Test
    void testFindByBookAndIsBorrowedFalse() {
        List<BookCopy> availableCopies = bookCopyRepository.findByBookAndIsBorrowedFalse(book);
        assertThat(availableCopies).hasSize(1);
        assertThat(availableCopies.get(0).isBorrowed()).isFalse();
    }

    @Test
    void testCountByBook() {
        long count = bookCopyRepository.countByBook(book);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void testCountByBookAndIsBorrowedTrue() {
        long borrowedCount = bookCopyRepository.countByBookAndIsBorrowedTrue(book);
        assertThat(borrowedCount).isEqualTo(1);
    }

    @Test
    void testFindByBookIsbnAndIsBorrowedFalse() {
        List<BookCopy> copies = bookCopyRepository.findByBookIsbnAndIsBorrowedFalse("1234567890");
        assertThat(copies).hasSize(1);
        assertThat(copies.get(0).isBorrowed()).isFalse();
    }
}
