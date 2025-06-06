package com.example.library.serviceImpl;


import com.example.library.dto.BookCopyResponseDto;
import com.example.library.mapper.BookCopyMapper;
import com.example.library.model.Book;
import com.example.library.model.BookCopy;
import com.example.library.model.Borrower;
import com.example.library.repository.BookCopyRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Override
    public BookCopyResponseDto addBookCopy(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book not found for ISBN: " + isbn));

        BookCopy copy = new BookCopy();
        copy.setBook(book);
        copy.setBorrowed(false);
        BookCopy bookCopy = bookCopyRepository.save(copy);

        return bookCopyMapper.convertToResponseDto(bookCopy);
    }

    @Override
    public BookCopy borrowBookCopy(Long copyId, Long borrowerId) {
        BookCopy copy = bookCopyRepository.findById(copyId).orElseThrow(() -> new RuntimeException("Book copy not found"));

        if (copy.isBorrowed()) {
            throw new RuntimeException("Book copy is already borrowed");
        }

        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(() -> new RuntimeException("Borrower not found"));

        copy.setBorrowed(true);
        copy.setBorrower(borrower);
        return bookCopyRepository.save(copy);
    }

    @Override
    public BookCopy returnBookCopy(Long copyId) {
        BookCopy copy = bookCopyRepository.findById(copyId).orElseThrow(() -> new RuntimeException("Book copy not found"));

        if (!copy.isBorrowed()) {
            throw new RuntimeException("Book copy is not currently borrowed");
        }

        copy.setBorrowed(false);
        copy.setBorrower(null);
        return bookCopyRepository.save(copy);
    }

    @Override
    public List<BookCopyResponseDto> getAvailableCopies(String isbn) {
        List<BookCopy> bookCopy = bookCopyRepository.findByBookIsbnAndIsBorrowedFalse(isbn);
        return bookCopyMapper.convertToResponseDtoList(bookCopy);
    }

    @Override
    public List<BookCopyResponseDto> getAllCopies() {
        List<BookCopy> bookCopy = bookCopyRepository.findAll();
        return bookCopyMapper.convertToResponseDtoList(bookCopy);
    }
}
