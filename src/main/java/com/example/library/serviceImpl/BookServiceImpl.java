package com.example.library.serviceImpl;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addOrUpdateBook(BookDto dto) {
        return bookRepository.findByIsbn(dto.getIsbn())
                .map(existingBook -> {
                    if (!existingBook.getTitle().equals(dto.getTitle()) ||
                            !existingBook.getAuthor().equals(dto.getAuthor())) {
                        throw new RuntimeException("ISBN already exists with different title/author");
                    }
                    return existingBook;
                })
                .orElseGet(() -> {
                    Book book = new Book();
                    book.setIsbn(dto.getIsbn());
                    book.setTitle(dto.getTitle());
                    book.setAuthor(dto.getAuthor());
                    return bookRepository.save(book);
                });
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found for ISBN: " + isbn));
    }
}
