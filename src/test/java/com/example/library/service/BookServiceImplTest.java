//package com.example.library.service;
//
//import com.example.library.dto.BookDto;
//import com.example.library.model.Book;
//import com.example.library.model.Borrower;
//import com.example.library.repository.BookRepository;
//import com.example.library.repository.BorrowerRepository;
//import com.example.library.serviceImpl.BookServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceImplTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Mock
//    private BorrowerRepository borrowerRepository;
//
//    @InjectMocks
//    private BookServiceImpl bookService;
//
//    @Test
//    void testAddBook() {
//        BookDto bookDto = new BookDto();
//        bookDto.setIsbn("1234567890");
//        bookDto.setTitle("Test Book");
//        bookDto.setAuthor("Author Name");
//
//        Book savedBook = new Book();
//        savedBook.setId(1L);
//        savedBook.setIsbn(bookDto.getIsbn());
//        savedBook.setTitle(bookDto.getTitle());
//        savedBook.setAuthor(bookDto.getAuthor());
//        savedBook.setBorrowed(false);
//        savedBook.setBorrower(null);
//
//        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);
//
//        Book result = bookService.addBook(bookDto);
//
//        assertNotNull(result);
//        assertEquals(bookDto.getTitle(), result.getTitle());
//        assertFalse(result.isBorrowed());
//        verify(bookRepository).save(any(Book.class));
//    }
//
//    @Test
//    void testBorrowBookSuccess() {
//        Long bookId = 1L;
//        Long borrowerId = 2L;
//
//        Book book = new Book();
//        book.setId(bookId);
//        book.setBorrowed(false);
//
//        Borrower borrower = new Borrower();
//        borrower.setId(borrowerId);
//
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
//        when(borrowerRepository.findById(borrowerId)).thenReturn(Optional.of(borrower));
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//
//        Book result = bookService.borrowBook(bookId, borrowerId);
//
//        assertTrue(result.isBorrowed());
//        assertEquals(borrower, result.getBorrower());
//        verify(bookRepository).save(book);
//    }
//
//    @Test
//    void testReturnBookSuccess() {
//        Long bookId = 1L;
//
//        Book book = new Book();
//        book.setId(bookId);
//        book.setBorrowed(true);
//        book.setBorrower(new Borrower());
//
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//
//        Book result = bookService.returnBook(bookId);
//
//        assertFalse(result.isBorrowed());
//        assertNull(result.getBorrower());
//        verify(bookRepository).save(book);
//    }
//
//    @Test
//    void testBorrowBook_AlreadyBorrowed_ShouldThrow() {
//        Long bookId = 1L;
//        Long borrowerId = 2L;
//
//        Book book = new Book();
//        book.setId(bookId);
//        book.setBorrowed(true);
//
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            bookService.borrowBook(bookId, borrowerId);
//        });
//
//        assertEquals("Book is already borrowed", exception.getMessage());
//    }
//
//    @Test
//    void testReturnBook_NotBorrowed_ShouldThrow() {
//        Long bookId = 1L;
//
//        Book book = new Book();
//        book.setId(bookId);
//        book.setBorrowed(false);
//
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            bookService.returnBook(bookId);
//        });
//
//        assertEquals("Book is not currently borrowed", exception.getMessage());
//    }
//}
