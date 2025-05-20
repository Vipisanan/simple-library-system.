//package com.example.library.controller;
//
//import com.example.library.dto.BookDto;
//import com.example.library.model.Book;
//import com.example.library.service.BookService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private BookService bookService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testAddBook() throws Exception {
//        BookDto dto = new BookDto("123-456", "Test Book", "Author Name");
//
//        Book savedBook = new Book();
//        savedBook.setId(1L);
//        savedBook.setIsbn(dto.getIsbn());
//        savedBook.setTitle(dto.getTitle());
//        savedBook.setAuthor(dto.getAuthor());
//
//        when(bookService.addBook(any(BookDto.class))).thenReturn(savedBook);
//
//        mockMvc.perform(post("/api/books/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(savedBook.getId()))
//                .andExpect(jsonPath("$.isbn").value(savedBook.getIsbn()))
//                .andExpect(jsonPath("$.title").value(savedBook.getTitle()));
//    }
//
//    @Test
//    void testGetAllBooks() throws Exception {
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Spring Boot in Action");
//        book.setAuthor("Craig Walls");
//
//        when(bookService.getAllBooks()).thenReturn(List.of(book));
//
//        mockMvc.perform(get("/api/books"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(1))
//                .andExpect(jsonPath("$[0].title").value("Spring Boot in Action"));
//    }
//
//    @Test
//    void testBorrowBook_Success() throws Exception {
//        Book book = new Book();
//        book.setId(1L);
//        book.setBorrowed(true);
//
//        when(bookService.borrowBook(1L, 1L)).thenReturn(book);
//
//        mockMvc.perform(post("/api/books/1/borrow/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.borrowed").value(true));
//    }
//
//    @Test
//    void testBorrowBook_Failure() throws Exception {
//        when(bookService.borrowBook(1L, 1L)).thenThrow(new RuntimeException("Book is already borrowed"));
//
//        mockMvc.perform(post("/api/books/1/borrow/1"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error").value("Book is already borrowed"));
//    }
//
//    @Test
//    void testReturnBook_Success() throws Exception {
//        Book book = new Book();
//        book.setId(1L);
//        book.setBorrowed(false);
//
//        when(bookService.returnBook(1L)).thenReturn(book);
//
//        mockMvc.perform(post("/api/books/1/return"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.borrowed").value(false));
//    }
//
//    @Test
//    void testReturnBook_Failure() throws Exception {
//        when(bookService.returnBook(1L)).thenThrow(new RuntimeException("Book is not borrowed"));
//
//        mockMvc.perform(post("/api/books/1/return"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error").value("Book is not borrowed"));
//    }
//}
//
