package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        bookDto = BookDto.builder()
                .isbn("1234567890")
                .title("Clean Code")
                .author("Robert C. Martin")
                .build();
    }

    @Test
    void testAddOrUpdateBook_addsNewBookIfNotExists() {
        when(bookRepository.findByIsbn("1234567890")).thenReturn(Optional.empty());

        Book savedBook = new Book();
        savedBook.setIsbn(bookDto.getIsbn());
        savedBook.setTitle(bookDto.getTitle());
        savedBook.setAuthor(bookDto.getAuthor());

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        Book result = bookService.addOrUpdateBook(bookDto);

        assertNotNull(result);
        assertEquals("1234567890", result.getIsbn());
        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());
    }

    @Test
    void testAddOrUpdateBook_returnsExistingBookIfMatches() {
        Book existingBook = new Book();
        existingBook.setIsbn("1234567890");
        existingBook.setTitle("Clean Code");
        existingBook.setAuthor("Robert C. Martin");

        when(bookRepository.findByIsbn("1234567890")).thenReturn(Optional.of(existingBook));

        Book result = bookService.addOrUpdateBook(bookDto);

        assertNotNull(result);
        assertSame(existingBook, result);
    }

    @Test
    void testAddOrUpdateBook_throwsIfTitleOrAuthorMismatch() {
        Book existingBook = new Book();
        existingBook.setIsbn("1234567890");
        existingBook.setTitle("Different Title");
        existingBook.setAuthor("Different Author");

        when(bookRepository.findByIsbn("1234567890")).thenReturn(Optional.of(existingBook));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> bookService.addOrUpdateBook(bookDto));

        assertEquals("ISBN already exists with different title/author", exception.getMessage());
    }

    @Test
    void testGetAllBooks_returnsAllBooks() {
        List<Book> books = List.of(
                new Book("1234567890", "Clean Code", "Robert C. Martin"),
                new Book("0987654321", "Effective Java", "Joshua Bloch")
        );

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
    }

    @Test
    void testGetBookByIsbn_found() {
        Book book = new Book("1234567890", "Clean Code", "Robert C. Martin");

        when(bookRepository.findByIsbn("1234567890")).thenReturn(Optional.of(book));

        Book result = bookService.getBookByIsbn("1234567890");

        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
    }

    @Test
    void testGetBookByIsbn_notFound() {
        when(bookRepository.findByIsbn("1234567890")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> bookService.getBookByIsbn("1234567890"));

        assertEquals("Book not found for ISBN: 1234567890", exception.getMessage());
    }
}
