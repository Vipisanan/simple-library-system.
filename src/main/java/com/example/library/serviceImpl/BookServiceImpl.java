package com.example.library.serviceImpl;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Book addBook(BookDto dto) {
        //TODO: Validate ISBN logic can be added here or in controller
        Book book = new Book();
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setBorrowed(false); // Newly added book is not borrowed
        book.setBorrower(null);

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (book.isBorrowed()) {
            throw new RuntimeException("Book is already borrowed");
        }

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("Borrower not found with id: " + borrowerId));

        book.setBorrowed(true);
        book.setBorrower(borrower);

        return bookRepository.save(book);
    }

    @Override
    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (!book.isBorrowed()) {
            throw new RuntimeException("Book is not currently borrowed");
        }

        book.setBorrowed(false);
        book.setBorrower(null);

        return bookRepository.save(book);
    }
}

