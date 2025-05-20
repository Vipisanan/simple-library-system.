package com.example.library.controller;

import com.example.library.dto.BookCopyResponseDto;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.service.BookCopyService;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookCopyService bookCopyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddBook() throws Exception {
        BookDto bookDto = new BookDto("1234567890", "Title", "Author");
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());

        when(bookService.addOrUpdateBook(any(BookDto.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()));

        verify(bookService).addOrUpdateBook(any(BookDto.class));
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Title");
        book.setAuthor("Author");

        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value(book.getIsbn()));

        verify(bookService).getAllBooks();
    }

    @Test
    void testGetBookByIsbn() throws Exception {
        String isbn = "1234567890";
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle("Title");
        book.setAuthor("Author");

        when(bookService.getBookByIsbn(isbn)).thenReturn(book);

        mockMvc.perform(get("/api/books/{isbn}", isbn))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(isbn));

        verify(bookService).getBookByIsbn(isbn);
    }

    @Test
    void testAddBookCopy() throws Exception {
        String isbn = "1234567890";
        BookCopyResponseDto copyDto = new BookCopyResponseDto();

        when(bookCopyService.addBookCopy(isbn)).thenReturn(copyDto);

        mockMvc.perform(post("/api/books/{isbn}/copies", isbn))
                .andExpect(status().isOk());

        verify(bookCopyService).addBookCopy(isbn);
    }

    @Test
    void testBorrowBookCopySuccess() throws Exception {
        Long copyId = 1L;
        Long borrowerId = 2L;
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(copyId);

        when(bookCopyService.borrowBookCopy(copyId, borrowerId)).thenReturn(bookCopy);

        mockMvc.perform(post("/api/books/copies/{copyId}/borrow/{borrowerId}", copyId, borrowerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(copyId));

        verify(bookCopyService).borrowBookCopy(copyId, borrowerId);
    }

    @Test
    void testBorrowBookCopyFailure() throws Exception {
        Long copyId = 1L;
        Long borrowerId = 2L;

        when(bookCopyService.borrowBookCopy(copyId, borrowerId))
                .thenThrow(new RuntimeException("Copy already borrowed"));

        mockMvc.perform(post("/api/books/copies/{copyId}/borrow/{borrowerId}", copyId, borrowerId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Copy already borrowed"));

        verify(bookCopyService).borrowBookCopy(copyId, borrowerId);
    }

    @Test
    void testReturnBookCopySuccess() throws Exception {
        Long copyId = 1L;
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(copyId);

        when(bookCopyService.returnBookCopy(copyId)).thenReturn(bookCopy);

        mockMvc.perform(post("/api/books/copies/{copyId}/return", copyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(copyId));

        verify(bookCopyService).returnBookCopy(copyId);
    }

    @Test
    void testReturnBookCopyFailure() throws Exception {
        Long copyId = 1L;

        when(bookCopyService.returnBookCopy(copyId))
                .thenThrow(new RuntimeException("Copy not borrowed"));

        mockMvc.perform(post("/api/books/copies/{copyId}/return", copyId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Copy not borrowed"));

        verify(bookCopyService).returnBookCopy(copyId);
    }

    @Test
    void testGetAvailableCopies() throws Exception {
        String isbn = "1234567890";
        BookCopyResponseDto copyDto = new BookCopyResponseDto();

        when(bookCopyService.getAvailableCopies(isbn)).thenReturn(Collections.singletonList(copyDto));

        mockMvc.perform(get("/api/books/{isbn}/copies/available", isbn))
                .andExpect(status().isOk());

        verify(bookCopyService).getAvailableCopies(isbn);
    }
}
